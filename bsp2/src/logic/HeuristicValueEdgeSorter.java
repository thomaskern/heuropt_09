package logic;

import data.Edge;

import java.util.Comparator;

public class HeuristicValueEdgeSorter implements Comparator<Edge> {

// sorted by heuristic value, ASC   
    public int compare(Edge e1, Edge e2) {
        return e1.getHeuristicValue() > e2.getHeuristicValue() ? 1 : -1;
    }

}
