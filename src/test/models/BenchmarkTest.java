package test.models;

import data.Fixtures;
import data.Solution;
import logic.Utility;
import logic.construction.Grasp;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.Vnd;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.neighborhoods.Rotation;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import logic.searches.stepfunctions.NextImprovement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class BenchmarkTest extends TestHelper {

    private String[][] fix_urls = new String[3][];

    @BeforeTest
    public void setUp() {
        Logger.clear_logdir();
        String[] tmp = {"big", "matrices/matrix_40j_60to_NSS_2.txt", "matrices/capacities.txt", "40_60"};
        fix_urls[0] = tmp;

        String[] tmp1 = {"medium", "matrices/matrix_30j_40to_NSS_2.txt", "matrices/capacities.txt", "30_40"};
        fix_urls[1] = tmp1;

        String[] tmp2 = {"small", "matrices/matrix_10j_10to_NSS_2.txt", "matrices/capacities.txt", "10_10"};
        fix_urls[2] = tmp2;
    }

    @Test
    public void should_run_vnd() {


        IStepFunction[] steps = {new BestImprovement(), new NextImprovement()};
        for (IStepFunction step : steps) {

            for (String[] current : fix_urls) {
                Fixtures f = get_fixtures(current[1], current[2], current[3]);

                Grasp g = new Grasp(f);
                g.run();
                Solution s = g.get_best_solution();

                Logger li = LoggerFactory.create("vnd_" + current[0]+"_"+step, "vnd_" + current[0] + "_" + step +"_"+ Utility.next_log_file_id() + ".txt");
                li.message("VND " + current[0]+" for "+current[3] + " with "+step);

                Vnd vnd = new Vnd();


                ArrayList<INeighborhood> hoods = new ArrayList<INeighborhood>();
                INeighborhood hood1 = new Rotation(get_fixtures(current[1], current[2], current[3]));
                INeighborhood hood2 = new PairSwitch(get_fixtures(current[1], current[2], current[3]));
                hoods.add(hood1);
                hoods.add(hood2);

                vnd.search(s, step, hoods);
                System.out.println(" ");
            }
        }


    }

}
