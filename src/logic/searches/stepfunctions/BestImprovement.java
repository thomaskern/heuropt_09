package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

import java.util.ArrayList;

public class BestImprovement implements IStepFunction {

    public String toString(){
        return "BestImprovement";
    }

    public Solution select(Solution solution, INeighborhood n) {
        Solution neighbor;

        n.init(solution);

        Solution bestSolution = n.next();
        Integer bestCost = bestSolution.calculate_costs();

        Integer tempCost;

        while((neighbor = n.next()) != null){
            tempCost = neighbor.calculate_costs();
            if (tempCost < bestCost) {
                bestCost = tempCost;
                bestSolution = neighbor;
            }
        }

        return bestSolution;


    }


}
