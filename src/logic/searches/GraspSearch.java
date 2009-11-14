package logic.searches;

import data.Fixtures;
import data.Solution;
import logic.construction.Grasp;

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

    public Solution run() {

        int c = Runtime.getRuntime().availableProcessors();

        if (c > 8)
            c = 8;
        ArrayList<Thread> threads = new ArrayList<Thread>(c);
        ArrayList<Grasp> grasps = new ArrayList<Grasp>(c);

        for (int i = 0; i < c; i++) {
            Fixtures f = new Fixtures(filename, cap, cap_id);
            f.parse_file();
            Grasp g = new Grasp(f, i);
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

        System.out.println("BEST SOLUTION: " + grasps.get(0).get_best_solution().calculate_costs());


        return grasps.get(0).get_best_solution();


    }
}
