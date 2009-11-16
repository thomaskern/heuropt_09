package logic;

import data.Solution;

import java.util.HashMap;

public class SolutionCostCache {
    private volatile static SolutionCostCache scc;

    private volatile HashMap<String, Integer> hm;


    private SolutionCostCache() {
        hm = new HashMap<String, Integer>();
    }

    public synchronized Integer get_cost(Solution solution) {
        if (!hm.containsKey(solution.jobsequence.toString())) {
            hm.put(solution.jobsequence.toString(), calculate_cost(solution));
        }

        return hm.get(solution.jobsequence.toString());
    }

    private Integer calculate_cost(Solution solution) {
        int cost = 0;

        for (int i = 0; i < solution.tool_sequence.size() - 1; i++) {
            cost += solution.tool_sequence.get(i).dissimilarity(solution.tool_sequence.get(i + 1));
        }

        return cost * Solution.COST_FACTOR;
    }

    public synchronized static SolutionCostCache getInstance() {
        if (scc == null)
            scc = new SolutionCostCache();
        return scc;
    }
}
