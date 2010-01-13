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
        graph = Fixtures.parse("mebp/mebp-08.dat");
    }

    @Test
    public void should_initiate_multiple_ants() {
        AcoHBF aco = new AcoHBF(0.1);
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

    @Test
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-10.dat");
        AcoHBF aco = new AcoHBF(0.18);
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

}
