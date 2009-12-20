package logic;

import data.Edge;
import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;

import java.util.Collections;

public class Sweep {

    public Trie run(Trie tree) {
        Trie t1 = run_a_sweep(tree);
//        return run_a_sweep(t1);
        return t1;
    }

    private Trie run_a_sweep(Trie tree) {
        double new_parent_cost;
        TrieNodeList nodes = new TrieNodeList();

// TODO: check for descendant nodes, not allowed to swap edges with descend nodes
        for (TrieNode te : tree.getTreeNodes()) {
            if (te.getChildren().size() > 0 && te != tree.getRoot()) {
                nodes.add(te);
            }
        }

        Collections.sort(nodes, new TrieNodeIdSorter());

//        TrieVisualizer vis = new TrieVisualizer();
//        vis.draw_trie(tree);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tree.cost());

        for (TrieNode worked_on_root_node : nodes) {
            sort_kids(worked_on_root_node);

            System.out.println(" ");
            System.out.println("NEW NODE: "+worked_on_root_node.getId());


            int kids = worked_on_root_node.getChildren().size();
            for (int i = 0; i < kids; i++) {
                TrieNode child = worked_on_root_node.getChildren().get(0);

                if (worked_on_root_node.getChildren().size() == 1)
                    new_parent_cost = worked_on_root_node.costliest_edge();
                else
                    new_parent_cost = Math.pow(worked_on_root_node.getChildren().second().distance_to(worked_on_root_node),3);

                boolean found_better_home = false;

                for (TrieNode other_root_node : nodes) {

                    if (other_root_node != worked_on_root_node) {

                        double old_costs = worked_on_root_node.costliest_edge() + other_root_node.costliest_edge();

                        System.out.println("OLD: "+old_costs);

                        double new_other_node_cost;

                        if (other_root_node.distance_to(child) > other_root_node.costliest_edge_in_eucledian())
                            new_other_node_cost = other_root_node.distance_to(child);
                        else
                            new_other_node_cost = other_root_node.costliest_edge_in_eucledian();


                        new_other_node_cost = Math.pow(new_other_node_cost, 3);

                        double new_costs = new_parent_cost + new_other_node_cost;
                        System.out.println("NEW: "+new_costs);

                        if (new_costs < old_costs) {
                            System.out.println("FROM TO: " + child.getId() + "::" + other_root_node.getId());
                            System.out.println("NEW DISTANCE: " + new_other_node_cost);
                            System.out.println("if added: " + new_other_node_cost);
                            System.out.println("distance from node to other origin: " + new_other_node_cost);
                            System.out.println("if new_parent_cost:" + new_parent_cost);


                            System.out.println("DELETE");
                            tree.delete_node(child.getDataNode());
                            tree.insert(new Edge(other_root_node.getDataNode(), child.getDataNode()));

                            found_better_home = true;

//                            System.out.println("SAVED: "+ (new_parent_cost - distance_change_to_new_root_node));

//                            System.out.println("costliest edge from node:" + other_root_node.costliest_edge_in_eucledian());
//                            System.out.println("NEAT: " + other_root_node.getId() + "::" + child.getId() + "::" + original_distance + "::" + new_other_node_cost);
                        }

                        System.out.println(" ");
                    }
                }

                if (!found_better_home)
                    break;
            }
        }

        tree.displayTree();

//        TrieVisualizer vis2 = new TrieVisualizer();
//        vis2.draw_trie(tree);
        System.out.println(tree.cost());
//        System.out.println(nodes);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tree;
    }

    private void sort_kids(TrieNode worked_on_root_node) {
        Collections.sort(worked_on_root_node.getChildren(), new TrieNodeCostSorter(worked_on_root_node.getDataNode()));
    }

}
