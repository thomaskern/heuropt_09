package test.models;

import data.Fixtures;
import data.Solution;
import data.ToolList;

public class TestHelper {

    protected Fixtures f;
    Solution s;

    protected ToolList get_tools() {
        return f.get_tools();
    }

    protected void load_file(String filename) {
        this.f = new Fixtures(filename);
        f.parse_file();
        s = new Solution();
    }

    protected ToolList create_toollist(int[] tool_ids) {
        ToolList ret = new ToolList();
        for (int i : tool_ids) {
            ret.add(get_tools().get(i));
        }
        return ret;
    }

    protected Fixtures get_fixtures(String s, String s1, String s2) {
        Fixtures f = new Fixtures(s, s1, s2);
        f.parse_file();
        return f;
    }
}
