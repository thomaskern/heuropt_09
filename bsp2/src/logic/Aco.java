package logic;

import data.Graph;
import data.TreeList;
import data.tree.Tree;

import java.util.ArrayList;

public class Aco {
    Tree best;

    public void run(Graph graph, int ants) {
        best = new Tree();

        for (int i = 0; i < 100; i++) {
            TreeList trees = run_ants(graph, ants);
            update_pheromone(trees);
            evaporate_pheromones();
        }

    }

    private void evaporate_pheromones() {

    }

    private void update_pheromone(TreeList trees) {

    }

    private TreeList run_ants(Graph graph, int ants) {
        TreeList trees = new TreeList();
        ArrayList<Ant> threads = new ArrayList<Ant>();

        for (int i = 0; i < ants; i++) {
            Ant a = new Ant(this, graph);
            a.start();
            threads.add(a);
        }

        wait_or_start_ants(threads);

        for (Ant t : threads)
            trees.add(t.getTree());

        return trees;
    }

    private void wait_or_start_ants(ArrayList<Ant> threads) {
        boolean b = true;

        while (b) {
            b = false;
            for (Thread t : threads) {
                if (t.isAlive()) {
                    b = true;
                    break;
                }

            }
        }
    }

}