package test.models;

import data.Fixtures;
import data.Solution;
import models.Tool;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class TestSolution extends TestHelper {

    Solution s;

    @BeforeMethod
    public void load_fixtures() {
        load_file("fixtures/matrix_3j_3to_NSS_0.txt");
        s.tool_switch(create_toollist(new int[]{}), create_toollist(new int[]{1, 2, 0}));
    }

    private void load_file(String filename) {
        this.f = new Fixtures(filename);
        f.parse_file();
        s = new Solution(get_jobs());
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
    public void should_add_new_toolconfig_to_the_back_of_list(){
        s.tool_switch(create_toollist(new int[]{1, 2}), create_toollist(new int[]{4}));
        assertEquals((Object) s.current_config().get(s.current_config().size()-1).id, 4);
    }

    @Test
    public void should_remove_tool_and_add_a_new_one_by_switch_tool_config_with_current() {
        s.tool_switch(create_toollist(new int[]{1, 2}), create_toollist(new int[]{4}));
        assertEquals(s.current_config().size(), 2);
        assertEquals((Object) s.current_config().get(s.current_config().size()-1).id, 4);
    }

    @Test
    public void should_check_validity_of_solution_for_incomplete_solution() {
        load_file("fixtures/matrix_2j_2to_NSS_0.txt");
        s.tool_switch(create_toollist(new int[]{1, 0}), create_toollist(new int[]{0}));
        assertFalse(s.is_valid());
    }

    @Test
    public void should_check_validity_of_solution_for_correct_and_complete_solution() {
        load_file("fixtures/matrix_2j_2to_NSS_0.txt");
        s.tool_switch(create_toollist(new int[]{}), create_toollist(new int[]{0, 1}));
        s.tool_switch(create_toollist(new int[]{0}), create_toollist(new int[]{}));

        assertTrue(s.is_valid());
    }

    @Test
    public void should_calculate_cost(){
        s.tool_switch(create_toollist(new int[]{1}), create_toollist(new int[]{3}));
        s.tool_switch(create_toollist(new int[]{0,2}), create_toollist(new int[]{1,4}));
        s.tool_switch(create_toollist(new int[]{4}), create_toollist(new int[]{2}));
        s.tool_switch(create_toollist(new int[]{1,2}), create_toollist(new int[]{0,3}));
        assertEquals((Object) s.calculate_costs(),6 * Solution.COST_FACTOR);
    }

    @Test
    public void should_check_validity_of_solution_for_incorrect_and_complete_solution() {
        load_file("fixtures/matrix_2j_2to_NSS_0.txt");
        assertFalse(s.is_valid());
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
