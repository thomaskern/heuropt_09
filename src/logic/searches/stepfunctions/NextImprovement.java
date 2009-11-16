package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public class NextImprovement implements IStepFunction {
    public Solution select(Solution solution, INeighborhood n) {
//        Integer oldCost = solution.calculate_costs();
//        Solution nextImprovement = solution;
//
//        for (ArrayList<Job> sequence : jobsequences) {
//            nextImprovement = new Solution(sequence);
//            if (nextImprovement.calculate_costs() < oldCost) {
//                return nextImprovement;
//            }
//        }
//
//        return solution;

        Solution tmp;
        n.start(solution);
        while((tmp = n.next()) != null){
            if(solution.calculate_costs() > tmp.calculate_costs())
                return tmp;
        }

        return null;
    }

    public boolean breakup() {
        return false;
    }
}
