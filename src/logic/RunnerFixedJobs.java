package logic;

import data.Solution;
import data.Job;

import java.util.ArrayList;

public class RunnerFixedJobs extends Runner {
    @Override
    public Solution run() {
        return new Solution(new ArrayList<Job>());
    }
}
