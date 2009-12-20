package logic;

import data.Edge;

import java.util.Comparator;


public class EdgeCostSorter implements Comparator<Edge> {
    public int compare(Edge o1, Edge o2) {
        double prob = o1.getProbability() - o2.getProbability();

// compare has to return int; probability can be really low (0.000005 for example); therefor add fix amount
        if(prob < 0)
            return (int) Math.round(prob - 10);
        else
            return (int) Math.round(prob + 10);
    }
}
