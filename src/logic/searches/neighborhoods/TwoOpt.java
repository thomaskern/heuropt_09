package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;
import logic.searches.stepfunctions.BestImprovement;
import sun.misc.GC;

import java.util.ArrayList;
import java.util.Random;

public class TwoOpt implements INeighborhood {

    private ArrayList<Job> jobsequence;
    private int i;
    private int j;
    private int n;
    private PairSwitch ps;
    private Fixtures fixtures;
    private int count;
    private Solution s;
    private Runtime r;

    public TwoOpt(Fixtures fixtures) {
        this.fixtures = fixtures;
        this.ps = new PairSwitch(fixtures);
        r = Runtime.getRuntime();

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
        this.s = solution;
        this.count = 0;
    }

    public Solution next() {
        if (j != n) {
            BestImprovement best = new BestImprovement();
            INeighborhood nh = new PairSwitch(fixtures);
            nh.init(s);
            j++;
            return best.select(s,nh);
//            System.out.println("BEFORE");
//            s = ps.next();
////            System.out.println("AFTER");
////            System.out.println("k");
////            System.out.println(count);
//            if (s == null) {
//                System.out.println("N: "+n+"::"+j+"::"+i);
//                count = 0;
//                /* check out next solutions neighborhood -> increase counters */
//                if (j == (n - 1)) {
//                    i++;
//                    j = i + 1;
//                } else {
//                    j++;
//                }
////                ps = null;
////                GC.
//                r.gc();
//                ps = new PairSwitch(fixtures);
//                ps.init(get_solution(get_sequence(j, i)));
////                System.out.println("B");
//                s = ps.next();
////                System.out.println("A");
////                s = s;
//            }
//            return s;

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

        return get_solution(get_sequence(tmpSolution.jobsequence, a, b));
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
