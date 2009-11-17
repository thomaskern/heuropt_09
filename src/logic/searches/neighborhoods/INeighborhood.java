package logic.searches.neighborhoods;

import data.Solution;
import java.util.ArrayList;

public interface INeighborhood {
    public void init(Solution solution);
    public ArrayList<Solution> getNeighborhood(Solution solution);
    public Solution next();
}
