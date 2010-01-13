package test.data;

import data.Fixtures;
import data.Graph;
import data.tree.Trie;
import logic.Aco;
import logic.AcoHBF;
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
        AcoHBF aco = new AcoHBF(0.1);
        graph.setAlpha(0.45);
        graph.setBeta(0.55);

        Trie t = aco.run(graph, 6);
        assert t.valid(20);
    }

    @Test
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-12.dat");
        graph.setAlpha(0.4);
        graph.setBeta(0.6);
        
        AcoHBF aco = new AcoHBF(0.18);
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

}
