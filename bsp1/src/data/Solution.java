package data;

import java.util.ArrayList;

public class Solution implements Comparable<Solution> {


    public ArrayList<ToolConfiguration> tool_sequence;
    public ArrayList<Job> jobsequence;
    private int total_cost;
    public static final int COST_FACTOR = 1;

    public Solution() {
        this.tool_sequence = new ArrayList<ToolConfiguration>();
        this.jobsequence = new ArrayList<Job>();
    }

    public void tool_switch(ToolList from, ToolList to) {
        ToolConfiguration tc = new ToolConfiguration();

        add_to_tc(from, tc, current_config());
        add_to_tc(tc, tc, to);

        tool_sequence.add(tool_sequence.size(), tc);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("job sequence: ").append(jobsequence);
        return sb.toString();
    }

    public ToolConfiguration current_config() {
        if (tool_sequence.size() == 0)
            return new ToolConfiguration();
        else
            return tool_sequence.get(tool_sequence.size() - 1);
    }

    public boolean is_valid() {
        if (tool_sequence.size() != jobsequence.size())
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

        if (total_cost == 0) {
            for (int i = 1; i < this.tool_sequence.size() - 1; i++) {
                total_cost += this.tool_sequence.get(i).dissimilarity(this.tool_sequence.get(i + 1));
            }


        }

        return total_cost * Solution.COST_FACTOR;

//        SolutionCostCache scc = SolutionCostCache.getInstance();
//        return scc.get_cost(this);
    }

    public void clear_sequences() {
        this.tool_sequence.clear();
    }

    public void add_sequence(ToolConfiguration tc) {
        this.tool_sequence.add(tc);
    }

    public int compareTo(Solution o) {
        if (o.jobsequence == this.jobsequence) {
            return 0;
        }
        return o.jobsequence == this.jobsequence ? 1 : -1;
    }
}


