package logic;

import data.Node;
import data.tree.TrieNode;

import java.util.Comparator;

public class TrieNodeCostSorter implements Comparator<TrieNode> {
    private Node parent;

    public TrieNodeCostSorter(Node parent){
        this.parent = parent;
    }

    public int compare(TrieNode o1, TrieNode o2) {
        return o1.getDataNode().distance_to(parent) < o2.getDataNode().distance_to(parent) ? 1 : -1;
    }
}
