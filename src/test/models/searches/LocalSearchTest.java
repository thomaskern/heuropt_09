/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.ToolSequences.Ktns;
import logic.searches.LocalSearch;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.neighborhoods.Rotation;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.models.TestHelper;

/**
 *
 * @author Christian
 */
public class LocalSearchTest extends TestHelper {

    @BeforeClass
    public void setUp() {
        // code that will be invoked before this test starts
    }

    @Test
    public void TestPairSwitchNeighborhood() {
        Fixtures f = get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        LocalSearch ls = new LocalSearch();
        Ktns k = new Ktns(f.get_jobs_as_arraylist(),f);
        Solution s = k.run();

        System.out.println(s);
        System.out.println(s.calculate_costs());


        IStepFunction step = new BestImprovement();
        INeighborhood hood = new PairSwitch(get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60"));

        Solution s1 = ls.search(s,step, hood,100);
        System.out.println(s1);
        System.out.println(s1.calculate_costs());
    }

     public void TestRotationNeighborhood() {
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

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
