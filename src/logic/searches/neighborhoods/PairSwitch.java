/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.searches.neighborhoods;

import data.Job;
import data.Solution;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class PairSwitch implements INeighborhood {

    private ArrayList<Solution> neighborhood;
    private int i;
    private int j;

    public PairSwitch() {
        neighborhood = new ArrayList<Solution>();

    }

    public ArrayList<Solution> getNeighborhood(){
        return this.neighborhood;
    }

    public void init(Solution solution){
       i = 0;
       j = i;
       ArrayList<Job> jobsequence = solution.jobs;
    }
//
//     ArrayList<Job> jobsequence = solution.jobs;
//        Job jJob;
//        Job iJob;
//
//        for (int i = 0; i < jobsequence.size(); i++) {
//            for (int j = i; j < jobsequence.size(); j++) {
//                if(i != j){
//                    ArrayList<Job> newJobSequence = jobsequence;
//                    jJob = newJobSequence.get(j);
//                    iJob = newJobSequence.get(i);
//                    newJobSequence.set(j, iJob);
//                    newJobSequence.set(i, jJob);
//                    neighborhood.add(new Solution(newJobSequence));
//                }
//            }
//        }

    public Solution next() {
        return null;
    }
}
