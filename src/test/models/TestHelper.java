package test.models;

import data.Fixtures;
import models.Job;

import java.util.ArrayList;

public class TestHelper {

    protected Fixtures f;

    protected ArrayList<Job> get_jobs() {
        ArrayList<Job> j = new ArrayList<Job>();
        for(Job job : f.getJobs().values())
            j.add(job);

        return j;
    }

}
