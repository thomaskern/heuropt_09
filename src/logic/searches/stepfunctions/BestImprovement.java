package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public class BestImprovement implements IStepFunction {
    public Solution select(Solution solution, INeighborhood n) {
//        Integer bestCost = solution.calculate_costs();
//        Integer tempCost = null;
//        Solution bestSolution = solution;
//
//
//        for (Solution neighbor : neighbors) {
//            tempCost = neighbor.calculate_costs();
//            if (tempCost < bestCost) {
//                bestCost = tempCost;
//                bestSolution = neighbor;
//            }
//        }
//
//        return bestSolution;

        n.start(solution);
        
        Solution best = n.next();
        Solution tmp = null;
          while((tmp = n.next()) != null){
            if(best.calculate_costs() > tmp.calculate_costs())
                best = tmp;
        }

        return best;        
    }

   
}
