package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.searches.GraspSearch;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import logic.searches.stepfunctions.RandomImprovement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.AssertJUnit.assertTrue;

public class GraspSearchTest extends TestHelper {

    @BeforeMethod
    public void setUp() {
//        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
//        f.parse_file();
    }


//    @Test
//    public void should_return_valid_solution_for_big_instances() {
//        f = new Fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
//        f.parse_file();
//        GraspSearch gs = new GraspSearch("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
//
////        Greedy g = new Greedy(f);
////        System.out.println("GREEDY: "+g.create_solution().calculate_costs());
//
//        Calendar cal = Calendar.getInstance();
//        long t = cal.getTimeInMillis();
//        Solution s = gs.run();
//        Calendar cal2 = Calendar.getInstance();
//        System.out.println(cal2.getTimeInMillis() - t);
//
//        assertTrue(s.is_valid());
//
//
//    }

    @Test
    public void should_return_valid_multi_search_solution() {
        GraspSearch gs = new GraspSearch("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10");
        IStepFunction step = new BestImprovement();
        INeighborhood hood = new PairSwitch(get_fixtures("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10"));
        Solution s = gs.run(step, hood);


        assertTrue(s.is_valid());
    }

    @Test
    public void should_return_valid_multi_search_solution_for_big_instance() {
        get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        GraspSearch gs = new GraspSearch("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        IStepFunction step = new BestImprovement();
        INeighborhood hood = new PairSwitch(get_fixtures("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10"));

        Solution s = gs.run(step, hood);


        assertTrue(s.is_valid());
    }

    private Fixtures get_fixtures(String s, String s1, String s2) {
        Fixtures f = new Fixtures(s, s1, s2);
        f.parse_file();
        return f;
    }


    @Test
    public void should_return_valid_solution() {
        GraspSearch gs = new GraspSearch("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10");
//        assertTrue(gs.run().is_valid());
    }

}
