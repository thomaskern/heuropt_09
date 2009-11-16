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

    private ArrayList<Job> jobsequence;
    private int i;
    private int j;
    private int n;

    public PairSwitch() {
        super();
    }

    public ArrayList<Solution> getNeighborhood(Solution solution){
        ArrayList<Solution> neighborhood = new ArrayList<Solution>();
        ArrayList<Job> jobseq = solution.jobs;
        Job lJob;
        Job kJob;

        for (int k = 0; k < jobseq.size(); k++) {
            for (int l = k; l < jobseq.size(); l++) {
                if(k != l){
                    ArrayList<Job> newJobSequence = new ArrayList<Job>();
                    for (Job job : jobsequence){
                        newJobSequence.add(job);
                    }
                    lJob = newJobSequence.get(l);
                    kJob = newJobSequence.get(k);
                    newJobSequence.set(l, kJob);
                    newJobSequence.set(k, lJob);
                    neighborhood.add(new Solution(newJobSequence));
                }
            }
        }

        return neighborhood;
    }

    public void init(Solution solution){
       this.i = 0;
       this.j = i;
       this.jobsequence = solution.jobsequence;
       this.n = this.jobsequence.size();
    }



    public Solution next() {
        Job iJob;
        Job jJob;

        if (!((i == (n-1)) && (j == n))){
            ArrayList<Job> newJobSequence = new ArrayList<Job>();
            for (Job job : jobsequence){
                newJobSequence.add(job);
            }

            jJob = newJobSequence.get(j);
            iJob = newJobSequence.get(i);
            newJobSequence.set(j, iJob);
            newJobSequence.set(i, jJob);
            if(j == (n-1)){
                i++;
                j = 0;
            }else{
                j++;
            }
             return new Solution(newJobSequence);
        }else{
            return null;
        }
    }
}
