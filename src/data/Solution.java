package data;

import models.Job;
import models.Tool;

import java.util.ArrayList;

public class Solution {

    public ArrayList<ToolConfiguration> tool_sequence;
    public ArrayList<Job> jobs;
    public ArrayList<Job> jobsequence;
    public static final int cost_factor = 5;

    public Solution(ArrayList<Job> jobs) {
        this.tool_sequence = new ArrayList<ToolConfiguration>(jobs.size());
        this.jobs = jobs;
        this.jobsequence = new ArrayList<Job>(jobs.size());
    }

    public void tool_switch(ArrayList<Tool> from, ArrayList<Tool> to) {
        ToolConfiguration tc = new ToolConfiguration();

        add_to_tc(from, tc, current_config());
        add_to_tc(tc, tc, to);

        tool_sequence.add(tool_sequence.size(),tc);
    }


    public ToolConfiguration current_config() {
        if (tool_sequence.size() == 0)
            return new ToolConfiguration();
        else
            return tool_sequence.get(tool_sequence.size() - 1);
    }

    public boolean is_valid() {
        if (tool_sequence.size() != jobs.size())
            return false;

        for (int i = 0; i < jobsequence.size(); i++) {
            Job j = jobsequence.get(i);
            ToolConfiguration tc = tool_sequence.get(i);
            for (Tool t : j.getTools()) {
                if (!tc.contains(t))
                    return false;
            }
        }

        return true;
    }

    private void add_to_tc(ArrayList<Tool> ban, ToolConfiguration tc, ArrayList<Tool> tool_list) {
        for (Tool t : tool_list)
            if (!ban.contains(t))
                tc.add(t);
    }

    public Integer calculate_costs() {
        int cost = 0;

        for (int i = 0; i < tool_sequence.size()-1; i++) {
            cost += tool_sequence.get(i).dissimilarity(tool_sequence.get(i+1));
        }

        return cost * Solution.cost_factor;
    }
}