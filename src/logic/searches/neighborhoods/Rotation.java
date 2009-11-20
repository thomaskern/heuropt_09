package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;
import java.util.Random;

/*
 *  Rotationsnachbarsschaft - Jobs werden paarweise rotiert
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
            if (k != (jobseq.size() - 1)) {
                neighborhood.add(get_solution(get_sequence(k, k + 1)));
            } else {
                neighborhood.add(get_solution(get_sequence(k, 0)));
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

        if (i < (n - 1)) {
            s = get_solution(get_sequence(i, i + 1));
        } else if (i == (n - 1)) {
            s = get_solution(get_sequence(i, i + 1));
        } else {
            s = null;
        }

        i++;
        return s;
    }

    public Solution random() {
        int a = (new Random()).nextInt(n - 1);

        if (a != (n - 1)) {
            return get_solution(get_sequence(a, a + 1));
        } else {
            return get_solution(get_sequence(a, 0));
        }
    }

    private ArrayList<Job> get_sequence(int j, int i) {
        ArrayList<Job> newJobSequence = new ArrayList<Job>();
        for (Job job : jobsequence) {
            newJobSequence.add(job);
        }

        Job jJob = newJobSequence.get(j);
        Job iJob = newJobSequence.get(i);
        newJobSequence.set(j, iJob);
        newJobSequence.set(i, jJob);
        return newJobSequence;
    }

    private Solution get_solution(ArrayList<Job> sequence) {
        return new Ktns(sequence, fixtures).run();
    }
}
