package logic;

import data.Job;
import data.Solution;

import java.util.ArrayList;
import java.util.HashMap;

public class SolutionCostCache {
    private volatile static SolutionCostCache scc;

    private volatile HashMap<String, Integer> hm;

    private SolutionCostCache() {
        hm = new HashMap<String, Integer>();
    }

    public synchronized Integer get_cost(Solution solution) {

        String key = get_key(solution.jobsequence);

        if (!hm.containsKey(key)) {
//            System.out.println(get_key(solution.jobsequence));
            hm.put(key, calculate_cost(solution));
        }

        return hm.get(key);
    }

    private String get_key(ArrayList<Job> jobsequence) {
        String tmp = "";

        for (Job j : jobsequence)
            tmp += j.id;

        return tmp;
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
