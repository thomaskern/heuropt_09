package data;

import data.ToolList;

import java.util.ArrayList;

public class Job {
    private ToolList tools;
    public int id;

    public Job(int id) {
        this.id = id;
    }

    public String toString() {
        return "job@" + id;
    }

    public void add_tools(ToolList tools) {
        this.tools = tools;
    }

    public ToolList getTools() {
        return tools;
    }
}
