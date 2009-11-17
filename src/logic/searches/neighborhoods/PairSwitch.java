/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.searches.neighborhoods;

import data.Job;
import data.Solution;
import java.util.ArrayList;
import java.util.Random;

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
       this.j = 1;
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
                j = i+1;
            }else{
                j++;
            }
           
            return new Solution(newJobSequence);

        }else{
            return null;
        }
    }

    public Solution random(){
        int a = 0;
        int b = 0;
        Job aJob;
        Job bJob;

        while(a == b){
            a = (new Random()).nextInt(n-1);
            b = (new Random()).nextInt(n-1);
        }

        ArrayList<Job> newJobSequence = new ArrayList<Job>();
        for (Job job : jobsequence){
            newJobSequence.add(job);
        }

        bJob = newJobSequence.get(b);
        aJob = newJobSequence.get(a);
        newJobSequence.set(j, aJob);
        newJobSequence.set(i, bJob);
        return new Solution(newJobSequence);
    }
}
