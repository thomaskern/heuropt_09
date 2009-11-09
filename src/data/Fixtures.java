package data;

import models.Job;
import models.Tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Fixtures {

    private String filename;
    private HashMap<Integer, Job> jobs;
    private Integer capacity;
    private ArrayList<Tool> tools;
    private JobSimilarityMatrix jobmatrix;


    public Fixtures(String filename) {
        prepare(filename, null, null);
    }

    private void prepare(String filename, String capacity_filename, String capacity_id) {
        this.filename = filename;
        this.jobs = new HashMap<Integer, Job>();

        if (capacity_filename != null) {
            Capacity.load(capacity_filename);
            this.capacity = Capacity.get(capacity_id);
        }
    }

    public Fixtures(String filename, String capacity_filename, String capacity_id) {
        prepare(filename, capacity_filename, capacity_id);
    }

    public void parse_file() {
        for (String line : File.read_lines(filename))
            parse_line(line);
    }

    private void parse_line(String strLine) {
        Job j = new Job(Integer.parseInt(get_column(strLine, 0)));
        j.add_tools(get_tools(get_column(strLine, 1)));
        this.jobs.put(j.id, j);
    }

    private String get_column(String strLine, Integer column) {
        return strLine.split(":")[column];
    }

    private ArrayList<Tool> get_tools(String s) {
        ArrayList<Tool> line_tools = new ArrayList<Tool>();
        String tools = remove_control_character(s);
        for (String tool : parse_tool_line(tools)) {
            line_tools.add(Tool.find_or_create(tool));
        }
        return line_tools;
    }

    private String[] parse_tool_line(String tools) {
        return tools.split(",");
    }

    private String remove_control_character(String s) {
        return s.substring(0, s.length() - 1);
    }

    public HashMap<Integer, Job> get_jobs() {
        return jobs;
    }

    public Integer capacity() {
        return capacity;
    }

    public JobSimilarityMatrix get_job_similarity_matrix(){
        if(jobmatrix == null)
        jobmatrix = new JobSimilarityMatrix(get_jobs_as_arraylist());
        return jobmatrix; 
    }

     public ArrayList<Job> get_jobs_as_arraylist() {
        ArrayList<Job> j = new ArrayList<Job>();
        for(Job job : get_jobs().values())
            j.add(job);

        return j;
    }

    public ArrayList<Tool> get_tools() {
        if (tools == null) {
            tools = new ArrayList<Tool>();
            for (Job j : jobs.values()) {
                for (Tool t : j.getTools())
                    if (!tools.contains(t))
                        tools.add(t);
            }
            Collections.sort(tools);
        }
        return tools;
    }

    public ToolList remaining_tools(ToolList list) {
        ToolList tools = new ToolList();
        for(Tool t : this.get_tools()){
            if(!list.contains(t))
                tools.add(t);
        }
        return tools;
    }
}
