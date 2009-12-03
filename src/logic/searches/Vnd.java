package logic.searches;

import data.Fixtures;
import data.Solution;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.neighborhoods.BlockSwitch;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.stepfunctions.IStepFunction;

import java.util.ArrayList;

public class Vnd {

    /*
    * VND::search conducts a variable neighborhood descent
    *
     */

    public Solution search(Solution s, IStepFunction step, ArrayList<INeighborhood> hoods, Fixtures fixtures) {

        int l = 0;

        int iterations = 0;
        Logger log = LoggerFactory.get();

        log.message("Start solution is: "+s);
        log.message("Start cost is: "+s.calculate_costs());
        int done = 0;

        do {
            Solution tmp = step.select(s, hoods.get(l));

            if (tmp != null && s.calculate_costs() > tmp.calculate_costs()) {
                s = tmp;
                l = 0;
                done = 0;
                log.message("Better Solution: "+s);
                log.message("Solution cost: "+s.calculate_costs());
            } else {
                l++;
                if(l == hoods.size()){
                    l = 0;
                    System.out.println("NICE");
                    BlockSwitch bs = new BlockSwitch(10, fixtures);
                    bs.init(s);
                    s = bs.next();
                    done++;
                }
                if(done > 25)
                    break;
            }
            iterations++;
        } while (l < hoods.size());

        log.message("Best solution: "+s);
        log.message("Lowest cost: "+s.calculate_costs());
        log.message("Total iterations: "+iterations);

        return s;
    }

}
