package logic.construction;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;
import logic.Utility;
import logic.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;

public class Grasp extends ConstructionHeuristic implements Runnable, Comparable<Grasp> {
    private int id;
    private Solution best_solution;

    public Grasp(Fixtures f, int id) {
        super(f);
        this.id = id;
    }

    public Grasp(Fixtures f) {
        super(f);
        this.id = 0;
    }

    @Override
    public Solution create_solution() {
        Solution s = new Solution(fixtures.get_jobs_as_arraylist());

        while (is_invalid(s)) {
            s.jobsequence.add(select_random(create_rcl(create_cl(s), s)));
        }

        Ktns k = new Ktns(s.jobsequence, fixtures, s);
        return k.run();
    }

    private Job select_random(ArrayList<Job> rcl) {
        return rcl.get(Utility.get_random_int(rcl.size()));
    }

    private ArrayList<Job> create_rcl(ArrayList<Job> cl, Solution solution) {
        ArrayList<JobCost> jc = calculate_costs(cl, solution);
        return filter_jobs(jc);
    }

    private ArrayList<Job> create_cl(Solution solution) {
        ArrayList<Job> a = (ArrayList<Job>) fixtures.get_jobs_as_arraylist().clone();
        a.removeAll(solution.jobsequence);
        return a;
    }

    private ArrayList<Job> filter_jobs(ArrayList<JobCost> jc) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        int limit = calculate_limit(jc);

        for (JobCost j : jc) {
            if (j.get_cost() <= limit)
                jobs.add(j.get_job());
        }

        return jobs;
    }

    private int calculate_limit(ArrayList<JobCost> jc) {
        int min = jc.get(0).get_cost();
        int max = 0;

        for (JobCost j : jc) {
            if (min > j.get_cost())
                min = j.get_cost();

            if (max < j.get_cost())
                max = j.get_cost();
        }

        return min;
    }

    private ArrayList<JobCost> calculate_costs(ArrayList<Job> cl, Solution solution) {
        ArrayList<JobCost> jcs = new ArrayList<JobCost>();
        for (Job j : cl) {
            Integer cost = create_costs_for_partial_solution(j, solution);
            jcs.add(new JobCost(j, cost));
        }

        Collections.sort(jcs);
        return jcs;
    }

    private Integer create_costs_for_partial_solution(Job j, Solution solution) {
        return new Ktns(merge_jobs(j, solution), fixtures).run().calculate_costs();
    }

    private ArrayList<Job> merge_jobs(Job j, Solution solution) {
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.addAll(solution.jobsequence);
        jobs.add(j);
        return jobs;
    }

    private boolean is_invalid(Solution s) {
        return s.jobsequence.size() < s.jobs.size();
    }

    public void run() {
        best_solution = create_solution();
        for (int i = 0; i < 200; i++) {
            Solution tmp = create_solution();
            if (tmp.calculate_costs() < best_solution.calculate_costs()) {
                best_solution = tmp;
            }
        }

        LoggerFactory.get().message("BEST: " + best_solution.calculate_costs() + ", id: " + id);
    }

    public int compareTo(Grasp o) {
        return best_solution.calculate_costs() - o.get_best_solution().calculate_costs();
    }

    public Solution get_best_solution() {
        return best_solution;
    }
}
