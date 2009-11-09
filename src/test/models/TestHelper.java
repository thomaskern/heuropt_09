package test.models;

import data.Fixtures;
import data.Solution;
import models.Tool;

import java.util.ArrayList;

public class TestHelper {

    protected Fixtures f;
    Solution s;

    protected ArrayList<Tool> get_tools() {
        return f.get_tools();
    }

    protected void load_file(String filename) {
        this.f = new Fixtures(filename);
        f.parse_file();
        s = new Solution(f.get_jobs_as_arraylist());
    }

    protected ArrayList<Tool> create_toollist(int[] tool_ids) {
        ArrayList<Tool> ret = new ArrayList<Tool>();
        for (int i : tool_ids) {
            ret.add(get_tools().get(i));
        }
        return ret;
    }
}
