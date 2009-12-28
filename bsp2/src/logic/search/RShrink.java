package logic.search;

import data.Graph;
import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;

public class RShrink implements INeighborhood {
    private int r; /* the r-shrink r value */
    private Graph g;
    
    public RShrink(Graph g, int r) {
        this.r = r;
        this.g = g;
    }

    RShrink() {
        
    }

public TrieNodeList getNd(Trie best, TrieNode node){
best.displayTree();
return null;
}

   
    public Trie run(Trie best) {
   
        return best;
    }
}
