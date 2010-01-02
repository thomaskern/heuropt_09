package logic.search;

import data.Graph;
import data.tree.Trie;
import data.tree.TrieNode;
import data.tree.TrieNodeList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import logic.TrieNodeCostSorter;

public class RShrink implements INeighborhood {

    private int r; /* the r-shrink r value */

    private Graph g;

    public RShrink(Graph g, int r) {
        this.r = r;
        this.g = g;
    }

    RShrink() {
    }

    private void rshrink(Trie best, TrieNode node){
        sort_kids(node);
        int size = node.getChildren().size();
        int count = 0;
        TrieNode tdc = null; /* temporarily disconnected child*/
        TrieNodeList fosterParents = null;
        TrieNodeList children = node.getChildren();

        while((size > 0) && count < r ){
            tdc =  children.get(count);
            fosterParents = getFosterParents(best, tdc);
            TrieNode bestFP = null;
            for(TrieNode fp : fosterParents){
                if(incrementalCostAtK(fp,tdc) < decrementalCostAtJ(node,tdc)){
                    if((bestFP == null) || tdc.distance_to(bestFP) > tdc.distance_to(fp)){
                        bestFP = fp;
                    }
                }
            }
            if( bestFP != null){
                best.swap_node(tdc, bestFP);
            }
        }
    }

    private void sort_kids(TrieNode node) {
        Collections.sort(node.getChildren(), new TrieNodeCostSorter(node.getDataNode()));
    }

    private double incrementalCostAtK(TrieNode nodei, TrieNode nodek) {

        if (nodei.hasChildren()) {
            return nodei.distance_to(nodek) - nodei.costliest_edge(); /* result = Pik - Pij */
        } else {
            return nodei.distance_to(nodek); /* result = Pik */
        }
  
    }

    private double decrementalCostAtJ(TrieNode nodei, TrieNode nodek) {
   
        if(nodei.getChildren().size() > 1){
            return nodei.costliest_edge() - nodei.distance_to(nodek); /* Pij - Pik */
        } else{
            return nodei.costliest_edge(); /* Pij */
        }

    }

    /* returns the nondescendants of node, excluding its parent */
    private TrieNodeList getFosterParents(Trie best, TrieNode node) {
        Iterator<TrieNode> it = best.getTreeNodes().iterator();
        TrieNodeList fpa = getNonDescendants(best, node);
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
