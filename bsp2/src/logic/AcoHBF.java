package logic;

import data.Edge;
import data.Graph;
import data.tree.Trie;
import data.tree.TrieList;
import data.tree.TrieNode;
import data.tree.TrieNodeList;
import logic.search.Vnd;
import views.TrieVisualizer;

import java.util.ArrayList;

public class AcoHbf extends Aco {

    private Trie Tib;
    private Trie Trb;
    private Trie Tbs;
    /*weight of trees*/
    private double Kib;
    private double Krb;
    private double Kbs;

    /*convergence factor*/
    private double cf;

    private boolean bs_update;
    private Graph graph;
    private int ant_totals;
    private ArrayList<Ant> threads;
    private TrieList trees;
    private double phero_max;
    private double phero_min;
    private TrieVisualizer trieVisualizer;
    private double p = 0.1; /* Evaporation rate between 0 and 1*/


    /*
     constructs a new ACO according to the
        "Ant Colony Optimization for Energy-Efficient
        Broadcasting in Ad-Hoc Networks"
     paper
     p is the learning rate
     */
    public AcoHbf(double p) {
        this.phero_max = 0.99;
        this.phero_min = 0.01;
        this.p = p;

        trieVisualizer = new TrieVisualizer(-1000, 0);
        this.trees = new TrieList();
        threads = new ArrayList<Ant>();
    }

    public Trie run(Graph graph, int ants) {
        this.ant_totals = ants;
        this.graph = graph;
        this.cf = 0;
        bs_update = false;
        Tbs = null;
        Trb = null;
        this.Kib = 1;
        this.Krb = 1;
        this.Kbs = 1;

        /*initializes pheromones on edges with 0.5*/
        for (Edge e : graph.getEdges()) {
            graph.update_pheromone_value(e.getStart().getId(), e.getEnd().getId(), 0.5);
        }

        double last_result = 0;
        int count_same_last_results = 0;
        int acceptable_number_of_last_results = 15;

        for (int i = 0; i < 1500; i++) {
            /*Construct trees */
            run_ants();

            /*run localsearch on them*/
            run_local_search();

//            if (Tib == null || Tib.cost() > find_best_tree().cost()) {
            Tib = find_best_tree();
//            }

            Update();

            ApplyPheromoneUpdate();

            cf = computeConvergenceFactor();
            System.out.println("Convergence Factor:" + Double.toString(cf));

            if (last_result == Trb.cost())
                count_same_last_results++;
            else
                last_result = Trb.cost();

            if (cf >= 0.99 || count_same_last_results > acceptable_number_of_last_results) {
                if (bs_update == true || count_same_last_results > acceptable_number_of_last_results) {
                    for (Edge e : graph.getEdges()) {
                        graph.update_pheromone_value(e.getStart().getId(), e.getEnd().getId(), 0.5);
                    }
                    System.out.println("RESET");
                    Trb = null;
                    bs_update = false;
                    count_same_last_results = 0;
                } else {
                    bs_update = true;
                }

            }

            trieVisualizer.draw_trie(Tbs);
            System.out.println("COST: " + Tbs.cost() + " (" + (int) Math.cbrt(Tbs.cost()) + ")");
//            Tbs.displayTree();
        }

//        try {
//            Thread.sleep(50000);

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return Tbs;
    }

    /* returns 1 wenn the given trie contains given edge */
    private double gamma(Trie t, Edge e) {

        TrieNode tn1 = t.find(e.getStart().getId());
        TrieNode tn2 = t.find(e.getEnd().getId());
        if ((tn1.getParent().equals(tn2)) || (tn2.getParent().equals(tn1))) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     updates all the pheromones if edge belongs to one of the trees, ep is higher than 0
     * and as a result there are some more pheromones on the edge. Influence of each tree is given by
     * weights
     */
    private void ApplyPheromoneUpdate() {
        double ep;
        double ph;

        for (Edge e : graph.getEdges()) {
            ep = Kib * gamma(Tib, e) + Krb * gamma(Trb, e) + Kbs * gamma(Tbs, e);

            ph = graph.get_pheromone_for_edge(e);
            ph = Math.min(Math.max(phero_min, ph + p * (ep - ph)), phero_max);

            graph.update_pheromone_value(e.getStart().getId(), e.getEnd().getId(), ph);
        }

        if (bs_update == false) {
            if (cf < 0.7) {
                this.Kib = 2.0 / 3.0;
                this.Krb = 1.0 / 3.0;
                this.Kbs = 0;
            } else if ((0.7 <= cf) && (cf < 0.9)) {
                this.Kib = 1.0 / 3.0;
                this.Krb = 2.0 / 3.0;
                this.Kbs = 0;
            } else if (cf >= 0.9) {
                this.Kib = 0;
                this.Krb = 1;
                this.Kbs = 0;
            }
        } else {
            this.Kib = 0;
            this.Krb = 0;
            this.Kbs = 1;
        }
    }

    /*computes convergence factor (sum of pheromone values on best tree)/(number of edges in this tree-1) * phero_max*/
    private double computeConvergenceFactor() {
        double sumph = 0;
        int edgecount = 0;

        for (TrieNode tn : Tbs.getTreeNodes()) {
            TrieNodeList children = tn.getChildren();
            for (TrieNode child : children) {
                sumph = sumph + graph.get_pheromone_for_edge(tn.getId(), child.getId());
                edgecount++;
            }
        }

        return (sumph / ((edgecount - 1) * phero_max));
    }

    /*if the iteration best Tree is better than the others they are set to this tree*/
    private void Update() {
        if (Trb == null) {
            Trb = Tib;
        }
        if (Tbs == null) {
            Tbs = Tib;
        }

        if (Tib.cost() < Trb.cost() && Tib.cost() < Tbs.cost()) {
            Trb = Tib;
            Tbs = Tib;
        }
    }

    private Trie find_best_tree() {
        Trie t = trees.get(0);

        for (int i = 1; i < trees.size(); i++) {
            if (t.cost() > trees.get(i).cost()) {
                t = trees.get(i);
            }
        }
        return t;
    }

    private TrieList run_local_search() {
        Vnd vnd = new Vnd();

        for (Trie t : this.trees) {
            t = vnd.run(t);
        }

        return trees;
    }

    private TrieList run_ants() {
        this.trees = new TrieList();
        threads = new ArrayList<Ant>();

        start_ants();
        wait_or_start_ants();

        return trees;
    }

    private void start_ants() {
        for (int i = 0; i < Utility.available_processor(); i++) {
            start_ant();
        }
    }

    private boolean start_ant() {
        synchronized (threads) {
            if (should_start_ant()) {
                Ant a = new Ant(threads.size(), this, graph);
                a.start();
                threads.add(a);
                return true;
            } else {
                return false;
            }
        }
    }

    private void wait_or_start_ants() {
        boolean b = true;

        while (b) {
            if (this.ant_count() < this.ant_totals) // creates less t.isAlive() checks/loops
            {
                continue;
            }

            b = false;
            synchronized (threads) {
                for (Thread t : threads) {
                    if (t.isAlive()) {
                        b = true;
                        break;
                    }
                }
            }
        }
    }

    private int ant_count() {
        synchronized (threads) {
            return threads.size();
        }
    }

    // used from within the ant algorithm when the ant is finished
    public synchronized void ant_done(Ant ant) {
        this.trees.add(ant.getTree());
        start_ant();
    }

    private boolean should_start_ant() {
        return ant_count() < this.ant_totals * Utility.available_processor();
    }
}
