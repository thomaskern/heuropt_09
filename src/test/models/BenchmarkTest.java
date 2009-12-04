package test.models;

import data.Fixtures;
import data.Solution;
import logic.Utility;
import logic.construction.Grasp;
import logic.construction.Greedy;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.GraspSearch;
import logic.searches.Vnd;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.neighborhoods.PairSwitch;
import logic.searches.neighborhoods.Rotation;
import logic.searches.neighborhoods.TwoOpt;
import logic.searches.stepfunctions.BestImprovement;
import logic.searches.stepfunctions.IStepFunction;
import logic.searches.stepfunctions.NextImprovement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import logic.searches.LocalSearch;
import logic.searches.stepfunctions.RandomImprovement;

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
    public void should_run_vnd_with_grasp() {

        IStepFunction[] steps = {new BestImprovement(), new NextImprovement()};
        for (IStepFunction step : steps) {

            for (String[] current : fix_urls) {
                Fixtures f = get_fixtures(current[1], current[2], current[3]);

//                GraspSearch gs = new GraspSearch(current[1], current[2], current[3]);
//                INeighborhood hood = new PairSwitch(get_fixtures(current[1], current[2], current[3]));
//                Solution s = gs.run(step, hood);

                Grasp g = new Grasp(f);
                g.run();
                Solution s = g.get_best_solution();
//

                run_vnd(step, current, s, "grasp");
                break;
            }
        }
    }

    private void run_vnd(IStepFunction step, String[] current, Solution s, String name) {
        Logger li = LoggerFactory.create("vnd_" + current[0] + "_" + step, "vnd_" + name + "_" + current[0] + "_" + step + "_" + Utility.next_log_file_id() + ".txt");
        li.message("VND " + current[0] + " for " + current[3] + " with " + step + " and " + name.toUpperCase());

        Vnd vnd = new Vnd();

        ArrayList<INeighborhood> hoods = new ArrayList<INeighborhood>();
        INeighborhood hood1 = new Rotation(get_fixtures(current[1], current[2], current[3]));
        INeighborhood hood2 = new PairSwitch(get_fixtures(current[1], current[2], current[3]));
        INeighborhood hood3 = new TwoOpt(get_fixtures(current[1], current[2], current[3]));
//        hoods.add(hood1);
//        hoods.add(hood2);
        hoods.add(hood3);

        vnd.search(s, step, hoods, get_fixtures(current[1], current[2], current[3]));
        System.out.println(" ");
    }

    private void run_ls(INeighborhood hood, IStepFunction step, String[] current, Solution s, String name) {
        Logger li = LoggerFactory.create("ls_" + current[0] + "_" + step, "ls_" + name + "_" + current[0] + "_" + step + "_" + Utility.next_log_file_id() + ".txt");
        li.message("ls " + current[0] + " for " + current[3] + " with " + step + " and " + name.toUpperCase());

        LocalSearch ls = new LocalSearch();

        ls.search(s, step, hood, 5);

        System.out.println(" ");
    }

    @Test
    public void should_run_vnd_without_grasp() {

        IStepFunction[] steps = {new BestImprovement(), new NextImprovement()};
        for (IStepFunction step : steps) {

            for (String[] current : fix_urls) {
                Fixtures f = get_fixtures(current[1], current[2], current[3]);

                Greedy g = new Greedy(f);

                Solution s = g.create_solution();

                run_vnd(step, current, s, "greedy");
            }
        }
    }

    @Test
    public void run_local_search_PairSwitch() {

        IStepFunction[] steps = {new BestImprovement(), new NextImprovement()};
        for (IStepFunction step : steps) {

            for (String[] current : fix_urls) {
                Fixtures f = get_fixtures(current[1], current[2], current[3]);

                Greedy g = new Greedy(f);

                Solution s = g.create_solution();

                INeighborhood hood = new PairSwitch(f);

                run_ls(hood,step, current, s, "PairSwitch");

            }
        }
    }

    @Test
    public void run_local_search_Rotation() {

        IStepFunction[] steps = {new BestImprovement(), new NextImprovement()};
        for (IStepFunction step : steps) {

            for (String[] current : fix_urls) {
                Fixtures f = get_fixtures(current[1], current[2], current[3]);

                Greedy g = new Greedy(f);

                Solution s = g.create_solution();

                INeighborhood hood = new Rotation(f);

                run_ls(hood,step, current, s, "Rotation");

            }
        }
    }
}
