package data;

import models.Job;
import models.Tool;

import java.util.ArrayList;

public class Solution {

    public ArrayList<ToolConfiguration> tool_sequence;
    public ArrayList<Job> jobs;
    public ArrayList<Job> jobsequence;

    public Solution(ArrayList<Job> jobs) {
        tool_sequence = new ArrayList<ToolConfiguration>(jobs.size());
        this.jobs = jobs;
        jobsequence = new ArrayList<Job>(jobs.size());
    }

    public void tool_switch(ArrayList<Tool> from, ArrayList<Tool> to) {
        ToolConfiguration tc = new ToolConfiguration();

        for(Tool t : current_config()){
            if(!from.contains(t))
                tc.add(t);
        }
        for(Tool t : to)
            if(!tc.contains(t))
                tc.add(t);

        tool_sequence.add(tc);
    }

    public ToolConfiguration current_config(){
        if(tool_sequence.size() == 0)
            return new ToolConfiguration();
        else
            return tool_sequence.get(tool_sequence.size() - 1);
    }

    public boolean is_valid() {

        return false;
    }

}