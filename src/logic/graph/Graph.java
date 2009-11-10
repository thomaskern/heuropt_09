package logic.graph;

import data.*;
import logic.CombinationGenerator;
import data.Job;
import data.Tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private ArrayList<Job> jobs;
    private Solution solution;
    private ArrayList<Vertex> vertices;
    private HashMap<Integer, ArrayList<Vertex>> graph;
    private Fixtures fixtures;
    private Vertex destination;
    private Vertex start;


    public Graph(ArrayList<Job> jobs, Fixtures f) {
        create(jobs, f, null);
    }

    public Graph(ArrayList<Job> jobs, Fixtures f, Solution s) {
        create(jobs, f, s);
    }

    private void create(ArrayList<Job> jobs, Fixtures f, Solution solution) {
        this.jobs = jobs;
        this.fixtures = f;
        this.vertices = new ArrayList<Vertex>();
        this.graph = new HashMap<Integer, ArrayList<Vertex>>();

        if (solution == null)
            this.solution = new Solution(jobs);
        else
            this.solution = solution;
    }

    public Solution generate_solution() {

        generate_graph();
        transfer_path_to_solution(find_shortest_path());

        return solution;
    }

    public String toString() {
        Collections.sort(vertices);
        return vertices.toString();
    }

    private void transfer_path_to_solution(List<Vertex> shortest_path) {
        solution.clear_sequences();

        for (int i = 1; i < shortest_path.size() - 1; i++) {
            solution.add_sequence(shortest_path.get(i).get_toolconfiguration());
        }
    }

    private List<Vertex> find_shortest_path() {
        Collections.sort(vertices);
        Dijkstra d = new Dijkstra();
        d.computePaths(start);
        return d.getShortestPathTo(destination);
    }

    private void generate_graph() {
        for (Job j : jobs) {
            System.out.println(j);
            this.graph.put(j.id, new ArrayList<Vertex>());

            for (ToolConfiguration tc : generate_toolconfigs_for_job(j)) {
                Vertex v = create_vertex(tc);
                this.vertices.add(v);
                this.graph.get(j.id).add(v);
            }
        }

        connect_vertices();
        add_start_destination();
    }

    private void add_start_destination() {

        start = create_vertex(new ToolConfiguration(), -1);

        for (Vertex v : graph.get(jobs.get(0).id)) {
            connect(start, v);
        }

        vertices.add(start);


        destination = create_vertex(new ToolConfiguration());

        for (Vertex v : graph.get(jobs.get(jobs.size() - 1).id)) {
            connect(v, destination);
        }

        vertices.add(destination);
    }

    private Vertex create_vertex(ToolConfiguration tc) {
        return create_vertex(tc, vertices.size());
    }

    private Vertex create_vertex(ToolConfiguration tc, Integer id) {
        return new Vertex(id, tc);
    }

    private void connect_vertices() {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (Vertex from : graph.get(jobs.get(i).id)) {
                for (Vertex to : graph.get(jobs.get(i + 1).id)) {
                    connect(from, to);
                }
            }
        }
    }

    private void connect(Vertex from, Vertex to) {
        Edge e = new Edge(from, to);
        from.addEdge(e);

    }

    private ArrayList<ToolConfiguration> generate_toolconfigs_for_job(Job job) {

        ArrayList<ToolConfiguration> tc = new ArrayList<ToolConfiguration>();

        ToolList toollist = this.fixtures.remaining_tools(job.getTools());
        
        int perm = Math.min(fixtures.capacity() - job.getTools().size(), toollist.size());
        if (perm == 0)
            return default_toolconfig(tc, job);

        System.out.println(toollist.size());
        System.out.println("perm"+perm);
        CombinationGenerator cg = new CombinationGenerator(toollist.size(), perm);

        int i = 0;
        while (cg.hasMore()) {
            System.out.println(i);
            i++;
//            ToolConfiguration tmp_tc = new ToolConfiguration();
//            add_combinations(toollist, tmp_tc, cg.getNext());
//            add_base_config(tmp_tc, job);
//            tc.add(tmp_tc);
        }

        return tc;
    }

    private ArrayList<ToolConfiguration> default_toolconfig(ArrayList<ToolConfiguration> tca, Job job) {
        ToolConfiguration tc = new ToolConfiguration();
        add_base_config(tc, job);
        tca.add(tc);
        return tca;
    }

    private void add_combinations(ToolList toollist, ToolConfiguration tmp_tc, int[] next) {
        for (int aNext : next) tmp_tc.add(toollist.get(aNext));
    }


    private void add_base_config(ToolConfiguration tmp_tc, Job job) {
        for (Tool t : job.getTools())
            tmp_tc.add(t);
    }
}
