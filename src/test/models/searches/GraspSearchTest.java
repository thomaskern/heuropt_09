package test.models.searches;

import data.Fixtures;
import logic.searches.GraspSearch;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.AssertJUnit.assertTrue;

public class GraspSearchTest extends TestHelper {

    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }


    @Test
    public void should_return_valid_solution_for_big_instances(){
        this.f = new Fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        f.parse_file();


        GraspSearch gs = new GraspSearch(f);
        assertTrue(gs.run().is_valid());
    }

    @Test
    public void should_return_valid_solution() {
        GraspSearch gs = new GraspSearch(f);
        assertTrue(gs.run().is_valid());
    }

}
