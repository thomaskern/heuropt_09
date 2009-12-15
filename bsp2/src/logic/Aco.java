package logic;

import data.Graph;
import data.Node;
import data.tree.TreeList;
import data.tree.TreeNode;
import data.tree.Trie;

import java.util.ArrayList;
import java.util.Arrays;

public class Aco {
    private Trie best;
    private Graph graph;
    private int ant_totals;
    private ArrayList<Ant> threads;
    private TreeList trees;

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
                        graph.get_pheromone_for_edge(n1.getId(),n2.getId()) * 0.9
                );

            }
        }
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

        max_min.displayTree();
        System.out.println("COST" + max_min.cost());

        for (TreeNode treeNode : max_min.getTreeNodes()) {
            if (treeNode.getParent().getDataNode() == treeNode.getDataNode())
                continue;

            int from = treeNode.getParent().getDataNode().getId();
            int to = treeNode.getDataNode().getId();
            double ph = graph.get_pheromone_for_edge(from, to);
            graph.update_pheromone_value(from, to, calculate_pheromone_update_for_best_edge(ph, max_min));
        }
    }

    private double calculate_pheromone_update_for_best_edge(double ph, Trie max_min) {
        return ph + 1.5;
    }

    private TreeList run_ants() {
        this.trees = new TreeList();

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