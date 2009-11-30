package logic.searches;

import data.Solution;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
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

        int iterations = 0;
        Logger log = LoggerFactory.get();

        log.message("Start solution is: "+s);
        log.message("Start cost is: "+s.calculate_costs());

        do {
            Solution tmp = step.select(s, hoods.get(l));

            if (tmp != null && s.calculate_costs() > tmp.calculate_costs()) {
                s = tmp;
                l = 0;
                log.message("Better Solution: "+s);
                log.message("Solution cost: "+s.calculate_costs());
            } else {
                l++;
            }
            iterations++;
        } while (l < hoods.size());

        log.message("Best solution: "+s);
        log.message("Lowest cost: "+s.calculate_costs());
        log.message("Total iterations: "+iterations);

        return s;
    }

}
