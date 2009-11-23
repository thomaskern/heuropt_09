package logic.searches;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.stepfunctions.IStepFunction;

public class LocalSearch {


    /**
     * public search(IStepFunction fs, INeighborhood n, int iStop), conducts a
     * local Search with the bound iStop which defines the number of solutions
     * generated that do not constitute an improvement.
     * Solution s will be passed from construction heuristic
     *
     * @param s     solution, normally from grasp construction heuristic
     * @param fs    step function
     * @param n     neighborhood function instance
     * @param iStop number of runs
     * @return returns a valid solution
     */

    public Solution search(Solution s, IStepFunction fs, INeighborhood n, int iStop) {
        int iBadCount = 0;

        do {
            Solution tmp = fs.select(s, n);
            if (tmp == null)
                break;

            if (s.calculate_costs() > tmp.calculate_costs()) {
                s = tmp;
                iBadCount = 0;
            } else {
                iBadCount++;
            }
        } while (iBadCount <= iStop);
        
        return s;
    }

}
