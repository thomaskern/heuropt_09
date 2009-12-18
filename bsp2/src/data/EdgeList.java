package data;

import java.util.ArrayList;

public class EdgeList extends ArrayList<Edge> {

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Edge e : this)
            sb.append("Start: ").append(e.getStart().getId()).append(", End: ").append(e.getEnd().getId()).append(", Cost: ").append(e.cost());


        return sb.toString();
    }

}
