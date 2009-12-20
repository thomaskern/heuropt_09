package test.data;

import data.Edge;
import data.Fixtures;
import data.Graph;
import data.NodeList;
import data.tree.Trie;
import logic.Sweep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertTrue;

public class SweepTest {

    private Graph graph;
    private NodeList nodes;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-01.dat");
        nodes = graph.getNodes();
    }


    @Test
    public void should_remove_obvious_problems_in_trie() {
        Trie t = new Trie();
        t.insert(nodes.get(0));
        t.insert(new Edge(nodes.get(0),nodes.get(2)));

        t.insert(new Edge(nodes.get(0),nodes.get(1)));
        t.insert(new Edge(nodes.get(0),nodes.get(4)));
        t.insert(new Edge(nodes.get(0),nodes.get(3)));
        t.insert(new Edge(nodes.get(3),nodes.get(7)));

        t.insert(new Edge(nodes.get(4),nodes.get(5)));
        t.insert(new Edge(nodes.get(4),nodes.get(6)));

//        664714140
//        567308731
//        583916349
       

//        System.out.println(new Edge(nodes.get(3),nodes.get(6)).cost());


        t.displayTree();

        Sweep s = new Sweep();
        Trie t1 = s.run(t,3);

        

        t1.displayTree();

        assertTrue(t1.valid(20));
    }

}
