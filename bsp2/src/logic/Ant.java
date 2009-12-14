package logic;

import data.Graph;
import data.tree.Tree;

public class Ant extends Thread {
    private Aco aco;
    private Graph graph;
    private Tree tree;

    public Ant(Aco aco, Graph graph) {
        this.aco = aco;
        this.graph = graph;
        tree = new Tree();
    }

    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.insert(graph.start_node());
        this.aco.ant_done(this);
    }

    public Tree getTree() {
        return tree;
    }
}
