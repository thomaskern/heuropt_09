package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;
import java.util.Random;

public class TwoOpt implements INeighborhood {

    private ArrayList<Job> jobsequence;
    private int i;
    private int j;
    private int n;
    private PairSwitch ps;
    private Fixtures fixtures;

    public TwoOpt(Fixtures fixtures) {
        this.fixtures = fixtures;
        this.ps = new PairSwitch(fixtures);
    }

    public ArrayList<Solution> getNeighborhood(Solution solution) {
        /* I protect you from memory harakiri ^^ and therefor return null*/
        return null;
    }

    public void init(Solution solution) {
        this.i = 0;
        this.j = 1;
        this.jobsequence = solution.jobsequence;
        this.n = this.jobsequence.size();
        ps.init(solution);
    }

    public Solution next() {
        if (!((i == (n - 1)) && j == n)) {
            Solution s = ps.next();
            if (s == null) {
                /* check out next solutions neighborhood -> increase counters */
                if (j == (n - 1)) {
                    i++;
                    j = i + 1;
                } else {
                    j++;
                }
                ps.init(get_solution(get_sequence(j, i)));
                s = ps.next();
            }
            return s;

        } else {
            return null;
        }
    }

    public Solution random() {
        int a = 0;
        int b = 0;
        Solution tmpSolution;

        while (a == b) {
            a = (new Random()).nextInt(n - 1);
            b = (new Random()).nextInt(n - 1);
        }

        tmpSolution = get_solution(get_sequence(b, a));

        a = b = 0;

        while (a == b) {
            a = (new Random()).nextInt(n - 1);
            b = (new Random()).nextInt(n - 1);
        }
        
        return get_solution(get_sequence(tmpSolution.jobsequence,a,b));
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

    private ArrayList<Job> get_sequence(ArrayList<Job> jobsequence, int j, int i) {
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
