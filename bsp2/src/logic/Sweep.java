package logic;

import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;

import java.util.Collections;

public class Sweep {

    public Trie run(Trie tree) {
        double removed;
        TrieNodeList nodes = new TrieNodeList();

        for (TrieNode te : tree.getTreeNodes()) {
            if (te.getChildren().size() > 0 && te != tree.getRoot()) {
                nodes.add(te);
            }
        }

        Collections.sort(nodes, new TrieNodeIdSorter());

        System.out.println(tree.cost());

        for (TrieNode worked_on_root_node : nodes) {
            sort_kids(worked_on_root_node);

            System.out.println(" ");
            System.out.println("NEW NODE: "+worked_on_root_node.getId());


            for (TrieNode child : worked_on_root_node.getChildren()) {
                double original_distance = child.distance_to(worked_on_root_node);

                if (worked_on_root_node.getChildren().size() == 1)
                    removed = worked_on_root_node.costliest_edge_in_eucledian();
                else
                    removed = worked_on_root_node.getChildren().second().distance_to(worked_on_root_node);

                System.out.println("IF REMOVED: " + removed);

                for (TrieNode other_root_node : nodes) {

                    if (other_root_node != worked_on_root_node) {
                        double latest_distance = other_root_node.distance_to(worked_on_root_node);
                        double distance_change = latest_distance - other_root_node.costliest_edge_in_eucledian();

                        System.out.println("if added: " + distance_change);
                        System.out.println("distance from node to other origin: " + latest_distance);


                        if (removed > distance_change) {
                            System.out.println("SAVED: "+ (removed - distance_change));

                            System.out.println("costliest edge from node:" + other_root_node.costliest_edge_in_eucledian());
                            System.out.println("NEAT: " + other_root_node.getId() + "::" + child.getId() + "::" + original_distance + "::" + latest_distance);
                        }

                        System.out.println(" ");
                    }
                }
            }
        }

//        System.out.println(nodes);

        return tree;
    }

    private void sort_kids(TrieNode worked_on_root_node) {
        Collections.sort(worked_on_root_node.getChildren(), new TrieNodeCostSorter(worked_on_root_node.getDataNode()));
    }

}
