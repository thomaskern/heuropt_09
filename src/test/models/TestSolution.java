package test.models;

import data.Fixtures;
import data.Solution;
import models.Tool;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class TestSolution extends TestHelper {

    Solution s;
    @BeforeMethod
    public void load_fixtures() {
        this.f = new Fixtures("fixtures/matrix_3j_3to_NSS_0.txt");
        f.parse_file();
        s = new Solution(get_jobs());
        s.tool_switch(create_toollist(new int[]{1}), create_toollist(new int[]{1, 2, 0}));
    }

    @Test
    public void should_switch_tool_config_from_scratch() {
        assertEquals(s.tool_sequence.size(), 1);
        assertEquals(s.current_config().size(), 3);
    }

    @Test
    public void should_remove_tool_by_switch_tool_config_with_current() {
        s.tool_switch(create_toollist(new int[]{1}), create_toollist(new int[]{}));

        assertEquals(s.tool_sequence.size(), 2);
        assertEquals(s.current_config().size(), 2);
    }

    @Test
    public void should_remove_tool_and_add_a_new_one_by_switch_tool_config_with_current() {
        s.tool_switch(create_toollist(new int[]{1}), create_toollist(new int[]{4,5}));

        System.out.println(create_toollist(new int[]{4,5}));

        assertEquals(s.tool_sequence.size(), 2);
        System.out.println(s.current_config());
        assertEquals(s.current_config().size(), 4);
    }

    private ArrayList<Tool> create_toollist(int[] tool_ids) {
        ArrayList<Tool> ret = new ArrayList<Tool>();
        for (int i : tool_ids) {
            ret.add(get_tools().get(i));
        }
        return ret;
    }

    private ArrayList<Tool> get_tools() {
        return f.getTools();
    }

}
