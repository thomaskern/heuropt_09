package logic.construction;

import data.Fixtures;
import data.Job;
import data.JobSimilarityMatrix;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;

public class Greedy extends ConstructionHeuristic {
    public Greedy(Fixtures fixtures) {
        super(fixtures);
    }

    public Solution create_solution() {
        JobSimilarityMatrix jsm = new JobSimilarityMatrix(jobs());
        Solution s = new Solution();
        Job job = smallest_job(jobs());
        s.jobsequence.add(job);


        while (true) {
            job = smallest_neighbourhood_job(jobs(), job, jsm, s.jobsequence);
            s.jobsequence.add(job);
            if (s.jobsequence.size() == jobs().size())
                break;
        }

        Ktns k = new Ktns(s.jobsequence, fixtures, s);
        k.run();

        return s;
    }

    private Job smallest_neighbourhood_job(ArrayList<Job> jobs, Job job, JobSimilarityMatrix jsm, ArrayList<Job> jobsequence) {

        int count = -1;
        Integer[] c = jsm.get(job.id);
        for (int i = 0; i < c.length; i++) {
            if (!jobsequence.contains(jobs.get(i)) && job.id != i && c[i] > count) {
                count = i;
            }
        }
        return count == -1 ? null : jobs.get(count);

    }

    private Job smallest_job(ArrayList<Job> jobs) {
        Job job = jobs.get(0);
        for (Job j : jobs) {
            if (j.getTools().size() < job.getTools().size()) {
                job = j;
            }
        }
        return job;
    }
}
