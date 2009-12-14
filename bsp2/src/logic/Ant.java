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
        graph.start_node();
    }

    public Tree getTree() {
        return tree;
    }
}
