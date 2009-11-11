package test.models.ToolSequences;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.graph.Graph;

import java.util.ArrayList;

public class GraphToolSequencer implements IToolSequencer{
    private ArrayList<Job> jobsequence;
    private Fixtures f;
    private Solution solution;

    public GraphToolSequencer(ArrayList<Job> jobsequence, Fixtures f, Solution solution) {

        this.jobsequence = jobsequence;
        this.f = f;
        this.solution = solution;
    }

    public Solution run() {
        Graph g = new Graph(jobsequence, f, solution);
        return g.generate_solution();
    }
}
