package logic.searches;

import data.Fixtures;
import data.Solution;
import logic.Utility;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.neighborhoods.BlockSwitch;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.XBlockSwitch;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import logic.searches.stepfunctions.NextImprovement;
import logic.searches.stepfunctions.RandomImprovement;

import java.util.ArrayList;

public class Vnd {

    /*
    * VND::search conducts a variable neighborhood descent
    *
     */

    public Solution search(Solution s, IStepFunction step, ArrayList<INeighborhood> hoods, Fixtures fixtures) {

        int l = 0;
        Solution best = s;

        int iterations = 0;
        BestImprovement b;
        XBlockSwitch bs;
        Logger log = LoggerFactory.get();

        log.message("Start solution is: "+s);
        log.message("Start cost is: "+s.calculate_costs());
        int done = 0;

        System.out.println("HOODS: "+hoods.size());

        do {
            System.out.println("L: "+l);
            System.out.println(hoods.get(l));
            Solution tmp = step.select(s, hoods.get(l));


            if (tmp != null && s.calculate_costs() > tmp.calculate_costs()) {
                hoods.remove(l);
                s = tmp;
                l = 0;
                log.message("Better Solution: "+s);
                log.message("Solution cost: "+s.calculate_costs());
                if(s.calculate_costs() < best.calculate_costs())
                    best = s;
            } else {
                l++;

//                LoggerFactory.get().message("neighbor"+l+"::"+hoods.size());
                if(l == hoods.size()){
                    l = 0;
                    System.out.println("NICE");
                    int r = 4 + Utility.get_random_int(10);

                    bs = new XBlockSwitch(fixtures, r);


                    b = new BestImprovement();
                    s = b.select(s, bs);


                    done++;
                }
                if(s.calculate_costs() < best.calculate_costs())
                    best = s;
                if(done > 25)
                    break;


            }
            iterations++;
        } while (l < hoods.size());

        log.message("Best solution: "+best);
        log.message("Lowest cost: "+best.calculate_costs());
        log.message("Total iterations: "+iterations);

        return best;
    }

}
