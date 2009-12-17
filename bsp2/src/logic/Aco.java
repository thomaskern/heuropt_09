package logic;

import data.Graph;
import data.Node;
import data.tree.Trie;
import data.tree.TrieList;
import data.tree.TrieNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Aco {
    private Trie best;
    private Graph graph;
    private int ant_totals;
    private ArrayList<Ant> threads;
    private TrieList trees;
    private double phero_max;
    private double phero_min;

    public Aco() {
        this.phero_max = 100;
        this.phero_min = 0;
    }

    public void run(Graph graph, int ants) {
        this.ant_totals = ants;
        this.graph = graph;

        for (int i = 0; i < 400; i++) {
            run_ants();
            update_pheromone();
            System.out.println(Arrays.deepToString(graph.getPheromoneMatrix()));
            evaporate_pheromones();
            System.out.println(" ");
            if (best == null || best.cost() > find_best_tree().cost())
                best = find_best_tree();
        }
    }

    private void evaporate_pheromones() {
        for (Node n1 : graph.getNodes()) {
            for (Node n2 : graph.getNodes()) {
                graph.update_pheromone_value(
                        n1.getId(),
                        n2.getId(),
                        calculate_pheromone_update_for_evap(n1, n2)

                );

            }
        }
    }

    private double calculate_pheromone_update_for_evap(Node n1, Node n2) {
        double tmp = graph.get_pheromone_for_edge(n1.getId(), n2.getId()) * 0.9;
        return check_for_phero_limits(tmp);
    }

    private double check_for_phero_limits(double tmp) {
        if (tmp > phero_max)
            tmp = phero_max;
        else if (tmp < phero_min)
            tmp = phero_min;

        return tmp;
    }

    private Trie find_best_tree() {
        Trie t = trees.get(0);

        for (int i = 1; i < trees.size(); i++) {
            if (t.cost() > trees.get(i).cost())
                t = trees.get(i);
        }
        return t;
    }

    private void update_pheromone() {
        Trie max_min = find_best_tree();

        System.out.println("COST" + max_min.cost());

        for (TrieNode trieNode : max_min.getTreeNodes()) {
            if (trieNode.getParent().getDataNode() == trieNode.getDataNode())
                continue;

            int from = trieNode.getParent().getDataNode().getId();
            int to = trieNode.getDataNode().getId();
            double ph = graph.get_pheromone_for_edge(from, to);
            graph.update_pheromone_value(from, to, calculate_pheromone_update_for_best_edge(ph, max_min));
        }
    }

    private double calculate_pheromone_update_for_best_edge(double ph, Trie max_min) {
        return check_for_phero_limits(ph + 1.5);
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
                Ant a = new Ant(this, graph);
                a.start();
                threads.add(a);
                return true;
            } else
                return false;
        }
    }

    private void wait_or_start_ants() {
        boolean b = true;

        while (b) {
            if (this.ant_count() < this.ant_totals) // creates less t.isAlive() checks/loops
                continue;

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