package logic;

import data.Edge;
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
//            System.out.println("NEW NODE: "+worked_on_root_node.getId());


            int kids = worked_on_root_node.getChildren().size();
            for(int i = 0; i < kids;i++){
//            for (TrieNode child : ) {
                TrieNode child = worked_on_root_node.getChildren().get(0);
                
                double original_distance = child.distance_to(worked_on_root_node);

                if (worked_on_root_node.getChildren().size() == 1)
                    removed = worked_on_root_node.costliest_edge_in_eucledian();
                else
                    removed = worked_on_root_node.getChildren().second().distance_to(worked_on_root_node);

                boolean found_better_home = false;
//                System.out.println("IF REMOVED: " + removed);

                for (TrieNode other_root_node : nodes) {

                    if (other_root_node != worked_on_root_node) {
                        double new_distance = other_root_node.distance_to(worked_on_root_node);
                        double distance_change_to_new_root_node = new_distance - other_root_node.costliest_edge_in_eucledian();

//                        System.out.println("if added: " + distance_change_to_new_root_node);
//                        System.out.println("distance from node to other origin: " + new_distance);


                        if (removed > distance_change_to_new_root_node) {

                            System.out.println("DELETE");
                            tree.delete_node(child.getDataNode());
                            tree.insert(new Edge(other_root_node.getDataNode(),child.getDataNode()));
//                            worked_on_root_node.getChildren().remove(child);


                            System.out.println((int)tree.getRoot().costliest_edge());
                            System.out.println((int)other_root_node.costliest_edge());
                            System.out.println(worked_on_root_node.costliest_edge());
                            found_better_home = true;

//                            System.out.println("SAVED: "+ (removed - distance_change_to_new_root_node));

//                            System.out.println("costliest edge from node:" + other_root_node.costliest_edge_in_eucledian());
//                            System.out.println("NEAT: " + other_root_node.getId() + "::" + child.getId() + "::" + original_distance + "::" + new_distance);
                        }

                        System.out.println(" ");
                    }
                }

                if(!found_better_home)
                    break;
            }
        }

        System.out.println(tree.cost());
//        System.out.println(nodes);

        return tree;
    }

    private void sort_kids(TrieNode worked_on_root_node) {
        Collections.sort(worked_on_root_node.getChildren(), new TrieNodeCostSorter(worked_on_root_node.getDataNode()));
    }

}
