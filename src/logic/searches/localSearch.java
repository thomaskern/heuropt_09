/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.searches;

import data.Job;
import data.Solution;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Christian
 */
public class localSearch {

    public Solution randomNeighbor(ArrayList<ArrayList<Job>> jobsequences) {
        int selJobSq = new Random().nextInt(jobsequences.size());
        return new Solution(jobsequences.get(selJobSq));
    }

    public Solution nextImprovement(ArrayList<ArrayList<Job>> jobsequences, Solution solution) {
        Integer oldCost = solution.calculate_costs();
        Solution nextImprovement = solution;

        for (ArrayList<Job> sequence : jobsequences){
            nextImprovement = new Solution(sequence);
            if(nextImprovement.calculate_costs() < oldCost ){
                return nextImprovement;
            }
        }

        return solution;
    }

    public Solution bestImprovement(ArrayList<Solution> neighbors, Solution solution) {
        Integer bestCost = solution.calculate_costs();
        Integer tempCost = null;
        Solution bestSolution = solution;
        

         for (Solution neighbor : neighbors){
            tempCost = neighbor.calculate_costs();
            if(tempCost < bestCost){
                bestCost = tempCost;
                bestSolution = neighbor;
            }
        }

        return bestSolution;
    }

}
