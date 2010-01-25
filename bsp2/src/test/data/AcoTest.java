package test.data;

import data.Fixtures;
import data.Graph;
import data.tree.Trie;
import logic.Aco;
import logic.AcoHbf;
import logic.Utility;
import logic.logger.Logger;
import logic.logger.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AcoTest {

    private Graph graph;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-01.dat");
    }

    @Test
    public void should_initiate_multiple_ants() {
        Aco aco = new Aco();
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

    @Test
    public void should_run_test_instance_6() {
        run_instance_avg("06");
    }

    @Test
    public void should_configure_alpha_beta() {

        ArrayList<String> as = new ArrayList<String>();
        String file = "06";

        for (int i = 4; i < 6; i++) {
            for (int b = 0; b < 10; b++) {
                graph = Fixtures.parse("mebp/mebp-" + file + ".dat");
                Aco aco = new AcoHbf(0.1);
                graph.setAlpha(0.1 * i);
                graph.setBeta(1 - 0.1 * b);
                Trie t = aco.run(graph, 4);
//                as.add("Alpha: " + (0.1 * i) + ", Beta: " + (1 - 0.1 * b) + ", Cost for Trie: " + Math.cbrt(t.cost()));
                System.out.println("Alpha: " + (0.1 * i) + ", Beta: " + (1 - 0.1 * b) + ", Cost for Trie: " + Math.cbrt(t.cost()));
            }
        }

        for (String s : as)
            System.out.println(s);
    }

    @Test
    public void should_run_test_instance_08() {
        run_instance_avg("08");
    }

    @Test
    public void should_run_test_instance_all() {
        run_instance_avg("06");
        run_instance_avg("08");
        run_instance_avg("10");
    }

    @Test
    public void should_run_test_instance_10() {


        run_instance_avg("10");
    }


    private void run_instance_avg(String file) {
        double[] results = new double[1];
        long[] time_results = new long[results.length];

        for (int i = 0; i < results.length; i++) {
            graph = Fixtures.parse("mebp/mebp-" + file + ".dat");
//            graph.setBeta(0.8);
            Logger li = LoggerFactory.create("file_" + file + "_it_" + i, "file_" + file + "_it_" + i + ".txt");
            Aco aco = new AcoHbf(0.1);

            long start_time = System.currentTimeMillis();
            Trie t = aco.run(graph, 2);
            long end_time = System.currentTimeMillis();


            results[i] = t.cost();
            t.displayTree();
            time_results[i] = end_time - start_time;
            Utility.print_time(start_time, end_time);
        }


        Logger li = LoggerFactory.create("file_" + file + "_analysis", "file_" + file + "_analysis.txt");
        for (int i = 0; i < results.length; i++) {
            li.message("Time for result: " + time_results[i] / 1000);
            li.message("Cost for Trie: " + Math.cbrt(results[i]));
        }

        double avg = avg(results);

        double var = 0;
        for (double r : results) {
            var += Math.pow((Math.cbrt(r) - avg), 2);
        }
        System.out.println("VAR: " + var + ", avg: " + avg);
        li.message("Std. " + (int) Math.sqrt(var / (results.length - 1)));
        li.message("Avg. Cost: " + avg(results));

    }

    private double avg(double[] results) {
        double sum = 0;
        for (double res : results)
            sum += Math.cbrt(res);
        return sum / results.length;
    }

    @Test
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-12.dat");
        Aco aco = new Aco();
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

}
