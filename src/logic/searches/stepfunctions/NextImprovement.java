package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public class NextImprovement implements IStepFunction {


    public Solution select(Solution solution, INeighborhood n) {

        Solution tmp;
        n.init(solution);
        while ((tmp = n.next()) != null) {
            if (solution.calculate_costs() > tmp.calculate_costs())
                return tmp;
        }

        return null;
    }


}
