package test.models.searches;

import data.Fixtures;
import data.Solution;
import logic.ToolSequences.Ktns;
import logic.construction.Grasp;
import logic.searches.LocalSearch;
import logic.searches.Vnd;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.neighborhoods.Rotation;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import logic.searches.stepfunctions.NextImprovement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import java.util.ArrayList;

public class VndTest extends TestHelper {


    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }

    @Test
    public void should() {

        Fixtures f = get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60");
        Vnd vnd = new Vnd();
//        Ktns k = new Ktns(f.get_jobs_as_arraylist(), f);
//        Solution s = k.run();

        Grasp g = new Grasp(f);
        g.run();
        Solution s = g.get_best_solution();

        System.out.println(s);
        System.out.println(s.calculate_costs());


        IStepFunction step = new BestImprovement();
        ArrayList<INeighborhood> hoods = new ArrayList<INeighborhood>();
        INeighborhood hood1 = new Rotation(get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60"));
        INeighborhood hood2 = new PairSwitch(get_fixtures("matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60"));
        hoods.add(hood1);
        hoods.add(hood2);        

        Solution s1 = vnd.search(s, step, hoods);
        System.out.println(s1);
        System.out.println(s1.calculate_costs());
        System.out.println("DONE");

    }


}
