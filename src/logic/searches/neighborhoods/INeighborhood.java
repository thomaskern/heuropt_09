package logic.searches.neighborhoods;

import data.Solution;

public interface INeighborhood {
    public void start(Solution solution);
    public Solution next();
}
