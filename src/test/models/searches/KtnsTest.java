package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.ToolSequences.Ktns;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class KtnsTest extends TestHelper {


    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }

    @Test
    public void should_create_valid_sequence() {
        Ktns k = new Ktns(f.get_jobs_as_arraylist(), f);
        Solution s = k.run();
        assertTrue(s.is_valid());
    }

    @Test
    public void should_create_best_sequence() {
        Ktns k = new Ktns(f.get_jobs_as_arraylist(), f);
        Solution s = k.run();
        assertEquals((Object) s.calculate_costs(), 7);
        assertEquals(s.tool_sequence.toString(), "[ 5,3,9,6,  6,3,0,7,  0,6,7,3,  2,0,7,3,  0,4,1,6,  4,6,8,1]");
    }

    @Test
    public void should_create_full_sequence_when_last_job_has_less_tools_than_cap() {
        f.get_jobs().get(5).getTools().remove(3);
        Ktns k = new Ktns(f.get_jobs_as_arraylist(), f);
        Solution s = k.run();
        assertEquals((Object) s.calculate_costs(), 7);
        assertEquals(s.tool_sequence.toString(), "[ 5,3,9,6,  6,3,0,7,  0,6,7,3,  2,0,7,3,  0,4,1,6,  4,6,8,0]");
    }


    @Test
    public void should_create_full_sequence_for_medium_dataset() {

        this.f = new Fixtures("matrices/matrix_40j_60to_NSS_0.txt", "matrices/capacities.txt", "40_60");
        f.parse_file();

        Ktns k = new Ktns(f.get_jobs_as_arraylist(), f);
        Solution s = k.run();
        assertEquals((Object) s.calculate_costs(), 299);
    }

}
