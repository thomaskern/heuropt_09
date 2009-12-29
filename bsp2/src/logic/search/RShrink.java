package logic.search;

import data.Graph;
import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;
import java.util.Collection;
import java.util.Iterator;

public class RShrink implements INeighborhood {

    private int r; /* the r-shrink r value */

    private Graph g;

    public RShrink(Graph g, int r) {
        this.r = r;
        this.g = g;
    }

    RShrink() {
    }

    private int incrementalCostAtK(Trie best, TrieNode node){
        return 0;
    }

    private int decrementalCostAtJ(Trie best, TrieNode node){
        return 0;
    }

    /* returns the nondescendants of node, excluding its parent */
    private TrieNodeList getFosterParents(Trie best, TrieNode node){
       Iterator<TrieNode> it = best.getTreeNodes().iterator();
       TrieNodeList fpa = getNonDescendants(best,node);
       fpa.remove(node.getParent());
       return fpa;
    }

   /* returns the nondescendants of node*/
    private TrieNodeList getNonDescendants(Trie best, TrieNode node) {
        TrieNodeList nd = new TrieNodeList();
        Iterator<TrieNode> it = null;
        TrieNode tmp = null;
        TrieNodeList desc = node.getDescendants();

        it = best.getTreeNodes().iterator();

        while (it.hasNext()) {
            tmp = it.next();
            if (!desc.contains(tmp)) {
                nd.add(tmp);
            }
        }

        return nd;
    }

    public Trie run(Trie best) {

        return best;
    }
}
