package logic.graph;

import data.ToolConfiguration;
import models.Job;

public class Vertex {
    private ToolConfiguration tc;
    private Job job;

    public Vertex(ToolConfiguration tc, Job j) {
        this.tc = tc;
        this.job = j;
    }
}
