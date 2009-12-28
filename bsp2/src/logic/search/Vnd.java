package logic.search;

import data.tree.Trie;

public class Vnd implements ISearch {

    public Trie run(Trie tree) {
        RShrink r = new RShrink();
        Trie best = tree;
        int l_max = 10;
        int l = 0;

        do {
            RShrink shrink = new RShrink();
         /*Trie improvement = shrink.run(best, l);*/
         Trie improvement = best;
         
            if (improvement.cost() < best.cost()) {
                best = improvement;
                l = 0;
            } else {
                l++;
            }
        } while (l < l_max);

        return tree;
    }
}
