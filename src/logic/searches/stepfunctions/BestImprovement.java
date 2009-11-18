package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

import java.util.ArrayList;

public class BestImprovement implements IStepFunction {


    public Solution select(Solution solution, INeighborhood n) {
        Solution neighbor;
        ArrayList<Solution> neighbors = n.getNeighborhood(solution);
        Solution bestSolution = neighbors.get(0);
        Integer bestCost = bestSolution.calculate_costs();

        Integer tempCost = null;

        for (int i = 1; i < neighbors.size(); i++) {
            neighbor = neighbors.get(i);

            tempCost = neighbor.calculate_costs();
            if (tempCost < bestCost) {
                bestCost = tempCost;
                bestSolution = neighbor;
            }
        }

        return bestSolution;


    }


}
