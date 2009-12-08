package test.models.construction;

import data.Fixtures;
import data.Solution;
import logic.construction.Grasp;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.AssertJUnit.assertTrue;

public class GraspTest extends TestHelper {
    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }


    @Test
    public void should_create_valid_solution() {
        Grasp g = new Grasp(f);
        assertTrue(g.create_solution().is_valid());
    }


    @Test
    public void should_create_big_solution(){
        this.f = new Fixtures("matrices/matrix_40j_60to_NSS_0.txt", "matrices/capacities.txt", "40_60");
        f.parse_file();

        Grasp g = new Grasp(f);
        Solution s = g.create_solution();

        System.out.println(s.calculate_costs());
    }

    @Test
    public void should_create_random_solutions() {
        Grasp g = new Grasp(f);
        Solution s = g.create_solution();
        Solution s1 = g.create_solution();

        System.out.println(s1.calculate_costs());
        System.out.println(s.calculate_costs());

        assertTrue(s.jobsequence != s1.jobsequence);

    }

}
