package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public interface IStepFunction {

    public Solution run(Solution s, INeighborhood n);

    public boolean breakup();
}
