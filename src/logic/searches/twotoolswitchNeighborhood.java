/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.searches;

import data.Job;
import data.Solution;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class twotoolswitchNeighborhood {

    public ArrayList<Solution> nhFunction(Solution solution) {
        ArrayList<Solution> neighbors = new ArrayList<Solution>();
        ArrayList<Job> jobsequence = solution.jobs;
        Job jJob;
        Job iJob;

        for (int i = 0; i < jobsequence.size(); i++) {
            for (int j = i; j < jobsequence.size(); j++) {
                if(i != j){
                    ArrayList<Job> newJobSequence = jobsequence;
                    jJob = newJobSequence.get(j);
                    iJob = newJobSequence.get(i);
                    newJobSequence.set(j, iJob);
                    newJobSequence.set(i, jJob);
                    neighbors.add(new Solution(newJobSequence));
                }
            }
        }
        return neighbors;
    }
    
}
