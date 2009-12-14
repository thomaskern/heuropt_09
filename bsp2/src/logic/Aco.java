package logic;

import data.Graph;
import data.tree.TreeList;
import data.tree.Trie;

import java.util.ArrayList;

public class Aco {
    private Trie best;
    private Graph graph;
    private int ant_totals;
    private ArrayList<Ant> threads;
    private TreeList trees;

    public void run(Graph graph, int ants) {
        this.ant_totals = ants;
        this.graph = graph;

        for (int i = 0; i < 1; i++) {
            run_ants();
            update_pheromone();
            evaporate_pheromones();
        }
    }

    private void evaporate_pheromones() {

    }

    private void update_pheromone() {
        
    }

    private TreeList run_ants() {
        this.trees = new TreeList();
        threads = new ArrayList<Ant>();

        start_ants();
        wait_or_start_ants(threads);

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

    private void wait_or_start_ants(ArrayList<Ant> threads) {
        boolean b = true;

        while (b) {
            if (this.ant_count() < this.ant_totals) // creates less t.isAlive() checks/loops
                continue;

            b = false;
            for (Thread t : threads) {
                if (t.isAlive()) {
                    b = true;
                    break;
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