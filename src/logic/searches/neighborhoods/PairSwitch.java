package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;
import java.util.Random;

public class PairSwitch implements INeighborhood {

    private ArrayList<Job> jobsequence;
    private int i;
    private int j;
    private int n;
    private Fixtures fixtures;

    public PairSwitch(Fixtures fixtures) {
        this.fixtures = fixtures;
    }


    public ArrayList<Solution> getNeighborhood(Solution solution) {
        init(solution);
        ArrayList<Solution> neighborhood = new ArrayList<Solution>();
        ArrayList<Job> jobseq = solution.jobsequence;

        for (int k = 0; k < jobseq.size(); k++) {
            for (int l = k; l < jobseq.size(); l++) {
                if (k != l) {
                    neighborhood.add(get_solution(get_sequence(l, k)));
                }
            }
        }

//        System.out.println("SIZE: "+neighborhood.size());

        return neighborhood;
    }

    public void init(Solution solution) {
        this.i = 0;
        this.j = 1;
        this.jobsequence = solution.jobsequence;
        this.n = this.jobsequence.size();
    }

    public Solution next() {
        if (!((i == (n - 1)) && j == n)) {
            Solution s = get_solution(get_sequence(j, i));

            if (j == (n - 1)) {
                i++;
                j = i + 1;
            } else {
                j++;
            }

            return s;

        } else {
            return null;
        }
    }

    public Solution random() {
        int a = 0;
        int b = 0;

        while (a == b) {
            a = (new Random()).nextInt(n - 1);
            b = (new Random()).nextInt(n - 1);
        }

        return get_solution(get_sequence(b, a));
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
