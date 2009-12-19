package logic;

import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;

import java.util.Collections;

public class Sweep {

    public Trie run(Trie tree) {
        Trie trie = new Trie();

        TrieNodeList nodes = new TrieNodeList();

        for(TrieNode te : tree.getTreeNodes()){
            if(te.getChildren().size() > 0 && te != tree.getRoot()){
                nodes.add(te);
            }
        }

        Collections.sort(nodes, new TrieNodeIdSorter());

        System.out.println(nodes);

        return tree;
    }

}
