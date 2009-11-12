package logic.ToolSequences;

import data.*;

import java.util.ArrayList;

public class Ktns implements IToolSequencer {
    private ArrayList<Job> jobs;
    private Fixtures fixtures;
    private Solution solution;

    public Ktns(ArrayList<Job> jobs, Fixtures fixtures) {
        create(jobs, fixtures, null);
    }

    public Ktns(ArrayList<Job> jobs, Fixtures fixtures, Solution solution) {
        create(jobs, fixtures, solution);
    }

    private void create(ArrayList<Job> jobs, Fixtures fixtures, Solution solution) {
        this.jobs = jobs;
        this.fixtures = fixtures;
        if (solution == null) {
            this.solution = new Solution(jobs);
            this.solution.jobs.addAll(jobs);
        } else
            this.solution = solution;
    }


    public Solution run() {

        for (int i = 0; i < jobs.size(); i++) {
            Job j = jobs.get(i);

            ToolConfiguration tc = new ToolConfiguration();
            add_base_tools(tc, j);

            if (free_space_left(tc))
                fill_up_that_free_space(tc, i);

            solution.add_sequence(tc);
        }

        System.out.println(solution.tool_sequence);
        System.out.println("NICE: "+solution.calculate_costs());
        return solution;
    }

    private void fill_up_that_free_space(ToolConfiguration tc, int i) {

        int z = i;
        while(tc.size() < fixtures.capacity()){
            for(Tool t : next_job(z).getTools()){
                
            }
        }

    }

    private Job next_job(int i) {
        return jobs.get(i+1);
    }

    private boolean free_space_left(ToolConfiguration tc) {
        return tc.size() < fixtures.capacity();
    }

    private void add_base_tools(ToolConfiguration tc, Job j) {
        tc.addAll(j.getTools());
    }

}
