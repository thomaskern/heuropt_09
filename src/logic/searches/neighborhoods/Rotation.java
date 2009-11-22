package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;
import java.util.Random;

/*
 *  Rotation Neighborhood - Jobs are rotated triplet wise. The last job of the
 *  triplet is moved to its beginning and the other two jobs are shifted right.
 *
 **/

public class Rotation implements INeighborhood {

    private ArrayList<Job> jobsequence;
    private int i;
    private int n;
    private Fixtures fixtures;

    public Rotation(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public ArrayList<Solution> getNeighborhood(Solution solution) {
        init(solution);
        ArrayList<Solution> neighborhood = new ArrayList<Solution>();
        ArrayList<Job> jobseq = solution.jobsequence;

        for (int k = 0; k < jobseq.size(); k++) {
            if (k <= (jobseq.size()-3)) {
                neighborhood.add(get_solution(get_rotation(k)));
            }
        }

        return neighborhood;
    }

    public void init(Solution solution) {
        this.i = 0;
        this.jobsequence = solution.jobsequence;
        this.n = this.jobsequence.size();
    }

    public Solution next() {
        Solution s;

        if (i <= (n - 3)) {
            s = get_solution(get_rotation(i));
        }else{
            s = null;
        }

        i++;
        return s;
    }

    public Solution random() {
        if(n >= 3){
            int a = (new Random()).nextInt(n - 3);
            return get_solution(get_rotation(a));
        }else{
            return null;
        }
     
    }

    private ArrayList<Job> get_rotation(int i) {
        ArrayList<Job> newJobSequence = new ArrayList<Job>();
        for (Job job : jobsequence) {
            newJobSequence.add(job);
        }

        Job i0Job = newJobSequence.get(i);
        Job i1Job = newJobSequence.get(i+1);
        Job i2Job = newJobSequence.get(i+2);
        newJobSequence.set(i, i2Job);
        newJobSequence.set(i+1, i0Job);
        newJobSequence.set(i+2, i1Job);
        
        return newJobSequence;
    }

    private Solution get_solution(ArrayList<Job> sequence) {
        return new Ktns(sequence, fixtures).run();
    }
}
