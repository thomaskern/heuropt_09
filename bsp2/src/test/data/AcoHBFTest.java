package test.data;

import data.Fixtures;
import data.Graph;
import data.tree.Trie;
import logic.AcoHbf;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AcoHBFTest {

    private Graph graph;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-06.dat");

    }

    @Test
    public void should_initiate_multiple_ants() {
        AcoHbf aco = new AcoHbf(0.09);
        graph.setAlpha(0.50);
        graph.setBeta(0.50);

        Trie t = aco.run(graph, 6);
        assert t.valid(50);
    }

    @Test
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-12.dat");
        graph.setAlpha(0.4);
        graph.setBeta(0.6);
        

        AcoHbf aco = new AcoHbf(0.10);
        Trie t = aco.run(graph, 3);
        assert t.valid(20);
    }

}
