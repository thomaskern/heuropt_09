package logic.graph;

import com.sun.jndi.dns.DnsName;
import data.*;
import logic.Permutation;
import models.Job;
import models.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<Job> jobs;
    private Solution solution;
    private ArrayList<Vertex> vertices;
    private HashMap<Integer, ArrayList<Vertex>> graph;
    private Fixtures fixtures;


    public Graph(ArrayList<Job> jobs, Fixtures f) {
        create(jobs, f, null);
    }

    private void create(ArrayList<Job> jobs, Fixtures f, Solution solution) {
        this.jobs = jobs;
        this.fixtures = f;
        this.vertices = new ArrayList<Vertex>();
        this.graph = new HashMap<Integer, ArrayList<Vertex>>();

        if(solution == null)
            this.solution = new Solution(jobs);
        else
            this.solution = solution;
    }

    public Solution generate_solution() {

        generate_graph();
        find_shortest_path();
        transfer_path_to_solution();

        return solution;
    }

    private void transfer_path_to_solution() {

    }

    private void find_shortest_path() {



    }

    private void generate_graph() {
        for(Job j : jobs){

            this.graph.put(j.id, new ArrayList<Vertex>());

            for(ToolConfiguration tc : generate_toolconfigs_for_job(j)){

                Vertex v = new Vertex(tc,j);
                this.vertices.add(v);

                this.graph.get(j.id).add(v);
            }

        }
    }

    private ArrayList<ToolConfiguration> generate_toolconfigs_for_job(Job job) {

        ArrayList<ToolConfiguration> tc = new ArrayList<ToolConfiguration>();



        ToolList tools = this.fixtures.remaining_tools(job.getTools());


        return tc;
    }
}
