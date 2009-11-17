/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.searches;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.stepfunctions.IStepFunction;

/**
 * @author Christian
 */


public class LocalSearch {


    /**
     * public search(IStepFunction fs, INeighborhood n, int iStop), conducts a
     * local Search with the bound iStop which defines the number of solutions
     * generated that do not constitute an improvement.
     */

    public Solution search(IStepFunction fs, INeighborhood n, int iStop) {
        GraspSearch gs = new GraspSearch("", "", "");
        Solution s = gs.run();
        int iBadCount = 0;

        do {
            Solution tmp = fs.select(s, n);

            if (s.calculate_costs() > tmp.calculate_costs()){
                s = tmp;
                iBadCount = 0;
            }else{
                iBadCount++;
            }
        } while (iBadCount <= iStop);
        return s;
    }

}
