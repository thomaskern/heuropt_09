package logic.construction;

import data.Job;

public class JobCost implements Comparable<JobCost> {
    private Job job;
    private Integer cost;

    public JobCost(Job j, Integer cost) {
        this.job = j;
        this.cost = cost;
    }

    public Job get_job() {
        return job;
    }

    public Integer get_cost() {
        return cost;
    }

    public int compareTo(JobCost o) {
        return cost - o.get_cost();
    }
}
