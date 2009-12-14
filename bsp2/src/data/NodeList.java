package data;

import java.util.ArrayList;

public class NodeList extends ArrayList<Node> {
    public void add_node(Node node) {
        if(!this.contains(node))
            add(node);
    }
}
