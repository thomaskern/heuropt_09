package logic.searches;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

import java.util.ArrayList;

public class Vnd {

    /*
    * VND::search conducts a variable neighborhood descent
    *
     */

    public Solution search(Solution s, ArrayList<INeighborhood> hoods) {

        int miss = 0;

        do {
            Solution tmp = s;
            if (s.calculate_costs() > tmp.calculate_costs()) {
                s = tmp;
                miss = 0;
            } else {
                miss++;
            }

            miss++;
        } while (miss < hoods.size());

        return s;
    }

}
