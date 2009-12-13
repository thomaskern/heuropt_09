package test.data;

import data.Node;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NodeTest {

    @Test
    public void should_calculate_distance_between_nodes(){
        Node n = new Node(1,3,4);
        Node n1 = new Node(2,7,9);
        assertEquals(n.distance_to(n1),Math.sqrt(41));
    }
}
