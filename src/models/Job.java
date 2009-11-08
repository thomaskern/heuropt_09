package models;

import java.util.ArrayList;

public class Job {
    private ArrayList<Tool> tools;
    public int id;

    public Job(int id) {
        this.id = id;
    }

    public void add_tools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }
}
