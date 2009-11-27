package logic.searches;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.stepfunctions.IStepFunction;

import java.util.ArrayList;

public class Vnd {

    /*
    * VND::search conducts a variable neighborhood descent
    *
     */

    public Solution search(Solution s, IStepFunction step, ArrayList<INeighborhood> hoods) {

        int l = 0;

        do {
            Solution tmp = step.select(s, hoods.get(l));


            if (tmp != null && s.calculate_costs() > tmp.calculate_costs()) {
                s = tmp;
                l = 0;
            } else {
                l++;
            }
        } while (l < hoods.size());

        return s;
    }

}
