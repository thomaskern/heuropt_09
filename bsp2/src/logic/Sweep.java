package logic;

import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;
import views.TrieVisualizer;

import java.util.Collections;

public class Sweep {

    public Trie run(Trie tree, int number_sweeps) {
        Trie tmp = tree;
        for (int i = 0; i < number_sweeps; i++) {
            tmp = run_a_sweep(tmp);
        }

        return tmp;
    }

    private TrieNodeList get_parents(Trie tree) {
        TrieNodeList nodes = new TrieNodeList();
        for (TrieNode te : tree.getTreeNodes()) {
            if (te.getChildren().size() > 0) {
                nodes.add(te);
            }
        }

        Collections.sort(nodes, new TrieNodeIdSorter());
        return nodes;
    }

    private Trie run_a_sweep(Trie tree) {
        double new_parent_cost;
        TrieNodeList nodes = get_parents(tree);

//        visualize_tree(tree, 0, 0, 100);

        for (TrieNode worked_on_root_node : nodes) {
            if(worked_on_root_node == tree.getRoot())
                continue;
            
            sort_kids(worked_on_root_node);
            int kids = worked_on_root_node.getChildren().size(); //have to do it like this; otherwise can't remove child from getChildren()

            for (int i = 0; i < kids; i++) {
                boolean found_better_home = false;
                TrieNode child = worked_on_root_node.getChildren().get(0);
                new_parent_cost = new_parent_cost(worked_on_root_node);

                for (TrieNode other_root_node : nodes) {

                    if (other_root_node != worked_on_root_node && !other_root_node.is_descendend_of(child, tree.getRoot()) && child != other_root_node) {

                        double old_costs = worked_on_root_node.costliest_edge() + other_root_node.costliest_edge();
                        double new_costs = new_parent_cost + new_other_node_cost(child, other_root_node);

                        if (new_costs < old_costs) {


//                            System.out.println("NEW DISTANCE: " + new_other_node_cost);
//                            System.out.println("if added: " + new_other_node_cost);
//                            System.out.println("distance from node to other origin: " + new_other_node_cost);
//                            System.out.println("if new_parent_cost:" + new_parent_cost);
//                            int before_size = tree.displayTree(false);
                            tree.swap_node(child, other_root_node);
                            found_better_home = true;


//                            if (before_size != tree.displayTree(false)) {
//                                System.out.println("child: " + child.getId() + ", to:" + other_root_node.getId() + ", from: " + worked_on_root_node.getId());
//                                tree.displayTree();
//                            }

                        }

                    }
                }

                if (!found_better_home)
                    break;
            }
        }

//        tree.displayTree();

//        visualize_tree(tree, 1, 0, 300);

        return tree;
    }

    private double new_parent_cost(TrieNode worked_on_root_node) {
        double new_parent_cost;
        if (worked_on_root_node.getChildren().size() == 1)
            new_parent_cost = worked_on_root_node.costliest_edge();
        else
            new_parent_cost = Math.pow(worked_on_root_node.getChildren().second().distance_to(worked_on_root_node), 3);
        return new_parent_cost;
    }

    private double new_other_node_cost(TrieNode child, TrieNode other_root_node) {
        double new_other_node_cost;

        if (other_root_node.distance_to(child) > other_root_node.costliest_edge_in_eucledian())
            new_other_node_cost = other_root_node.distance_to(child);
        else
            new_other_node_cost = other_root_node.costliest_edge_in_eucledian();


        new_other_node_cost = Math.pow(new_other_node_cost, 3);
        return new_other_node_cost;
    }

    private void visualize_tree(Trie tree, int i, int i1, int i2) {

        TrieVisualizer vis = i == 0 ? new TrieVisualizer() : new TrieVisualizer(i, i1);
        vis.draw_trie(tree);

        try {
            Thread.sleep(i2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tree.displayTree();
        System.out.println("SIZE: " + tree.size() + ", COST: " + tree.cost());

    }

    private void sort_kids(TrieNode worked_on_root_node) {
        Collections.sort(worked_on_root_node.getChildren(), new TrieNodeCostSorter(worked_on_root_node.getDataNode()));
    }

}
