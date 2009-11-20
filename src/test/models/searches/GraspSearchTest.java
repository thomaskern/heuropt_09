package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.ToolSequences.Ktns;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.GraspSearch;
import logic.searches.LocalSearch;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.neighborhoods.Rotation;
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
    public void should(){

        Fixtures f = get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        LocalSearch ls = new LocalSearch();
        Ktns k = new Ktns(f.get_jobs_as_arraylist(),f);
        Solution s = k.run();

        System.out.println(s);
        System.out.println(s.calculate_costs());


        IStepFunction step = new BestImprovement();
        INeighborhood hood = new Rotation(get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60"));

        Solution s1 = ls.search(s,step, hood,100);
        System.out.println(s1);
        System.out.println(s1.calculate_costs());

    }

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
         Logger li = LoggerFactory.create_or_get("1", "graspsearch.txt");
        Logger li2 = LoggerFactory.create_or_get("12", "graspsearch2.txt");
        get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        GraspSearch gs = new GraspSearch("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        IStepFunction step = new BestImprovement();
        INeighborhood hood = new Rotation(get_fixtures("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10"));

        Solution s = gs.run(step, hood);


        assertTrue(s.is_valid());
    }


    @Test
    public void should_return_valid_solution() {
        GraspSearch gs = new GraspSearch("matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10");
//        assertTrue(gs.run().is_valid());
    }

}
