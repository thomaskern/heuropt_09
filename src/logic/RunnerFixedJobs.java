package logic;

import data.Job;
import data.Solution;

import java.util.ArrayList;

public class RunnerFixedJobs extends Runner {
    @Override
    public Solution run() {
        return new Solution(new ArrayList<Job>());
    }
}
