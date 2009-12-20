package data.tree;

import data.Node;

import java.util.ArrayList;

public class TrieNodeList extends ArrayList<TrieNode> {
    public double max_to_node(Node data_node) {
        double cost = 0;
        for (TrieNode trieNode : this) {
            if (trieNode.getDataNode().distance_to(data_node) > cost)
                cost = trieNode.getDataNode().distance_to(data_node);
        }

        return Math.pow(cost, 3);
    }

    public TrieNode second() {
        return get(1);
    }
}
