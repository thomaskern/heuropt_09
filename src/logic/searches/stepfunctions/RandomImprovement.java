package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public class RandomImprovement implements IStepFunction {
    public Solution select(Solution solution, INeighborhood n) {
        n.init(solution);
        return n.random();
    }

}
