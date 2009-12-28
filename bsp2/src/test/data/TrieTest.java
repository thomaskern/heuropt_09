package test.data;

import data.Edge;
import data.Fixtures;
import data.Graph;
import data.NodeList;
import data.tree.Trie;
import data.tree.TrieNodeList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertFalse;

public class TrieTest {

    private Graph graph;
    private NodeList nodes;
    private Trie t;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-01.dat");
        nodes = graph.getNodes();

        t = new Trie();
        t.insert(nodes.get(0));
        t.insert(new Edge(nodes.get(0),nodes.get(2)));

        t.insert(new Edge(nodes.get(0),nodes.get(1)));
        t.insert(new Edge(nodes.get(0),nodes.get(4)));
        t.insert(new Edge(nodes.get(0),nodes.get(3)));
        t.insert(new Edge(nodes.get(4),nodes.get(7)));

        t.insert(new Edge(nodes.get(3),nodes.get(5)));
        t.insert(new Edge(nodes.get(3),nodes.get(6)));
    }


    @Test
    public void should_remove_leaf_from_tree(){
        t.delete_node(nodes.get(5));
        assertEquals(t.getTreeNodes().size(),7);
        assertEquals(t.find(4).getChildren().size(),1);
    }

    @Test
    public void should_remove_node_and_children_from_tree(){
        t.delete_node(nodes.get(3));

        assertEquals(t.getTreeNodes().size(),5);
        assertFalse(t.valid(9));
    }

    @Test
    public void should_get_descendants(){
        System.out.println(t.find(0).getId());
        System.out.println("List to String" + t.toString());
    }
}
