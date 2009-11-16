package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public interface IStepFunction {

    public Solution select(Solution s, INeighborhood n);

}
