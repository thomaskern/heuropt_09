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

    public TrieNodeList getFosterParents(Trie best, TrieNode node){
        Iterator<TrieNode> it = best.getTreeNodes().iterator();
        TrieNode tmp = null;
        TrieNodeList nd = getNonDescendants(best,node);
        TrieNode pa = node.getParent();
        TrieNodeList fpa = new TrieNodeList();

        while(it.hasNext()){
            tmp = it.next();
            if(!(pa.equals(tmp) || nd.contains(tmp))){
                fpa.add(tmp);
            }
        }

        return fpa;
    }

    public TrieNodeList getNonDescendants(Trie best, TrieNode node) {
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
