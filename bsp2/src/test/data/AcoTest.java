package test.data;

import data.Fixtures;
import data.Graph;
import data.tree.Trie;
import logic.Aco;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

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
    public void should_initiate_multiple_ants_on_large_dataset() {
        graph = Fixtures.parse("mebp/mebp-12.dat");
        Aco aco = new Aco();
        Trie t = aco.run(graph, 4);
        assert t.valid(20);
    }

}
