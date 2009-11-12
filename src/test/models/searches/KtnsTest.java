package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.ToolSequences.Ktns;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class KtnsTest extends TestHelper {

    @BeforeTest
    public void setUp() {
        this.f = new Fixtures("fixtures/graph_matrix_3_3.txt", "fixtures/capacities.txt", "3_3");
        f.parse_file();
    }

    @Test
    public void should_create_valid_sequence() {
        Ktns k = new Ktns(f.get_jobs_as_arraylist(),f);
        Solution s = k.run();
        assertTrue(s.is_valid());
    }

    @Test
    public void should_create_best_sequence() {
        Ktns k = new Ktns(f.get_jobs_as_arraylist(),f);
        System.out.println(f.get_jobs_as_arraylist());
        Solution s = k.run();
        assertEquals((Object) s.calculate_costs(),5);
    }

}
