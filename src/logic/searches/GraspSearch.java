package logic.searches;

import data.Fixtures;
import data.Solution;
import logic.construction.Grasp;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import logic.searches.neighborhoods.INeighborhood;
import logic.searches.stepfunctions.IStepFunction;

import java.util.ArrayList;
import java.util.Collections;

public class GraspSearch {
    private String filename;
    private String cap;
    private String cap_id;

    public GraspSearch(String filename, String cap, String cap_id) {
        this.filename = filename;
        this.cap = cap;
        this.cap_id = cap_id;
    }

    public Solution run(IStepFunction step, INeighborhood hood) {

        Logger li = LoggerFactory.create_or_get("1", "graspsearch.txt");

//        ArrayList<Grasp> grasps = multi_start();
        ArrayList<Grasp> grasps  = single_start();

        LocalSearch ls = new LocalSearch();

        System.out.println("best grasp:" + grasps.get(0).get_best_solution());
        System.out.println("best grasp cost: "+grasps.get(0).get_best_solution().calculate_costs());

        Solution best = ls.search(grasps.get(0).get_best_solution(), step, hood, 1000);

//        write_results_to_log(li, best, grasps);

        System.out.println(best);
        System.out.println(best.calculate_costs());

        return grasps.get(0).get_best_solution();


    }

    private ArrayList<Grasp> single_start() {
        ArrayList<Grasp> grasps = new ArrayList<Grasp>(1);
        Grasp g = new Grasp(get_fixtures(), 1);
        g.run();
        grasps.add(g);

        return grasps;
    }

    private ArrayList<Grasp> multi_start() {
        int c = Runtime.getRuntime().availableProcessors();

        if (c > 8)
            c = 8;

        ArrayList<Thread> threads = new ArrayList<Thread>(c);
        ArrayList<Grasp> grasps = new ArrayList<Grasp>(c);

        for (int i = 0; i < c; i++) {
            Grasp g = new Grasp(get_fixtures(), i);
            Thread t = new Thread(g);
            t.start();
            threads.add(t);
            grasps.add(g);
        }

        int b = c;
        while (b > 0) {
            b = 0;
            for (Thread t : threads) {
                if (t.isAlive())
                    b++;
            }
        }
        Collections.sort(grasps);
        return grasps;
    }

    private Fixtures get_fixtures() {
        Fixtures f = new Fixtures(filename, cap, cap_id);
        f.parse_file();
        return f;
    }

    private void write_results_to_log(Logger li, Solution best, ArrayList<Grasp> grasps) {
        li.message("Best solution after Grasp: " + grasps.get(0).get_best_solution().calculate_costs());

        LoggerFactory.get().message("START LOCAL SEARCH");

        System.out.println(best);
        System.out.println(best.calculate_costs());

        LoggerFactory.get().message("END LOCAL SEARCH");

        LoggerFactory.get().message("Best Solution: " + grasps.get(0).get_best_solution());
    }
}
