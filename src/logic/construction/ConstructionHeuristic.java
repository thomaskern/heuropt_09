package logic.construction;

import data.Fixtures;
import data.Job;
import data.Solution;

import java.util.ArrayList;

public abstract class ConstructionHeuristic {
    protected Fixtures fixtures;

    public ConstructionHeuristic(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    protected ArrayList<Job> jobs() {
        return fixtures.get_jobs_as_arraylist();
    }

    public abstract Solution create_solution();
}
