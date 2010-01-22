package test.data;

import data.Fixtures;
import data.Graph;
import data.Node;
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
        graph = Fixtures.parse("mebp/mebp-10.dat");

        Node root = graph.getNodes().get(0);
        double d = 10000;

        for (Node n : graph.getNodes()) {
            if (n == root)
                continue;

            if (d > root.distance_to(n))
                d = root.distance_to(n);

            System.out.println(root.distance_to(n));
        }

        System.out.println(Math.cbrt(67371));
        System.out.println(d);


        run_instance_avg("10");
    }


    private void run_instance_avg(String file) {
        double[] results = new double[5];
//        System.out.println(Math.cbrt(68440173));


        for (int i = 0; i < 1; i++) {
            graph = Fixtures.parse("mebp/mebp-" + file + ".dat");
//            graph.setBeta(0.8);
            Logger li = LoggerFactory.create("file_" + file + "_it_" + i, "file_" + file + "_it_" + i + ".txt");
            Aco aco = new AcoHbf(0.1);

            long start_time = System.currentTimeMillis();
            Trie t = aco.run(graph, 4);
            long end_time = System.currentTimeMillis();


            results[i] = t.cost();
            t.displayTree();
            Utility.print_time(start_time, end_time);
        }


        Logger li = LoggerFactory.create("file_06_analysis", "file_06_analysis.txt");
        for (Double d : results) {
            li.message("Cost for Trie: " + Math.cbrt(d));
        }
    }

    @Test
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-12.dat");
        Aco aco = new Aco();
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

}
