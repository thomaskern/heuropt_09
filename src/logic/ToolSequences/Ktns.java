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

    public Solution run() {
        solution.clear_sequences();

        for (int i = 0; i < jobs.size(); i++) {
            ToolConfiguration tc = new ToolConfiguration();
            add_base_tools(tc, jobs.get(i));
            fill_up_that_free_space(tc, i);

            solution.add_sequence(tc);
        }

        return solution;
    }

    private void create(ArrayList<Job> jobs, Fixtures fixtures, Solution solution) {
        this.jobs = jobs;
        this.fixtures = fixtures;
        if (solution == null) {
            this.solution = new Solution(jobs);
            this.solution.jobsequence = jobs;
        } else
            this.solution = solution;
    }

    private void fill_up_that_free_space(ToolConfiguration tc, int i) {
        int z = i;

        while (free_space_left(tc) && z < jobs.size()) {
            add_tool_from_toollist(calculate_toollist_from_job(next_job(z), tc), tc, i);
            z++;
        }

        add_from_previous_job(tc, i);
    }

    private ToolList calculate_toollist_from_job(Job job, ToolConfiguration tc) {
        return calculate_unused_toollist_from_tools(tc, job.getTools());

    }

    private ToolList calculate_unused_toollist_from_tools(ToolConfiguration tc, ArrayList<Tool> tools) {
        ToolList toollist = new ToolList();
        for (Tool t : tools) {
            if (!tc.includes_tool(t))
                toollist.add(t);
        }
        return toollist;
    }

    private void add_from_previous_job(ToolConfiguration tc, int i) {
        add_unused_tool_to_tc(calculate_unused_toollist_from_tools(tc, previous_tool_sequence(i)), tc, tc, false);
    }

    private void add_tool_from_toollist(ToolList next_tools_job, ToolConfiguration tc, int i) {
        add_unused_tool_to_tc(next_tools_job, tc, previous_tool_sequence(i), true);
        add_unused_tool_to_tc(next_tools_job, tc, tc, false);
    }

    private void add_unused_tool_to_tc(ToolList next_tools_job, ToolConfiguration tc, ToolConfiguration banned_tools, boolean add_banned_tools) {
        for (Tool t : next_tools_job) {
            if (!free_space_left(tc))
                break;

            if (banned_tools.includes_tool(t) == add_banned_tools) {
                tc.add(t);
            }
        }
    }

    private boolean free_space_left(ToolConfiguration tc) {
        return tc.size() < fixtures.capacity();
    }

    private ToolConfiguration previous_tool_sequence(int i) {
        if (solution.tool_sequence.size() < i - 1 || i - 1 < 0)
            return new ToolConfiguration();
        else
            return solution.tool_sequence.get(i - 1);
    }

    private Job next_job(int i) {
        if (jobs.size() <= i + 1)
            return new Job(-1);
        else
            return jobs.get(i + 1);
    }

    private void add_base_tools(ToolConfiguration tc, Job j) {
        tc.addAll(j.getTools());
    }

}
