package test.data;

import data.Fixtures;
import data.Graph;
import data.Node;
import data.tree.Trie;
import logic.Aco;
import logic.AcoHBF;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        for(Node n : graph.getNodes()){
            if(n == root)
                continue;

            if(d > root.distance_to(n))
            d =root.distance_to(n);

            System.out.println(root.distance_to(n));
        }

        System.out.println("SHIT");

        System.out.println(Math.cbrt(67371) );
        System.out.println(d);


        run_instance_avg("10");
    }

    private void run_instance_avg(String file) {
        double[] results = new double[5];

        for (int i = 0; i < 5; i++) {
            graph = Fixtures.parse("mebp/mebp-" + file + ".dat");
            Aco aco = new AcoHBF(0.1);
            Trie t = aco.run(graph, 4);
            results[i] = t.cost();

        }

        for (Double d : results) {
            System.out.println("Cost for Trie: " + Math.cbrt(d));
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
