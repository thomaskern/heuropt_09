package logic;

import data.tree.TrieNode;

import java.util.Comparator;

public class TrieNodeIdSorter implements Comparator<TrieNode> {
    public int compare(TrieNode o1, TrieNode o2) {
        return o1.getId() - o2.getId();
    }
}
