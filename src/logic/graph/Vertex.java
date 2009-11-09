package logic.graph;

import data.ToolConfiguration;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {
    private ToolConfiguration tc;
    public ArrayList<Edge> edges;
    private Integer id;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(id).append(": Vertex").append("@").append(tc);

        for (Edge e : this.edges) {
            sb.append(": Edge@").append(e.to.id).append("|").append(e.cost);
            if (edges.lastIndexOf(e) < edges.size() - 1)
                sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    public Integer id() {
        return id;
    }

    public Integer cost_to_vertex(Vertex x) {
        return this.tc.dissimilarity(x.get_toolconfiguration());
    }

    public Vertex(int id, ToolConfiguration tc) {
        this.tc = tc;
        this.id = id;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    public ToolConfiguration get_toolconfiguration() {
        return tc;
    }


    public boolean equals(Object o) {
        return o.getClass() == Vertex.class && (this == o || equals((Vertex) o));
    }

    private boolean equals(Vertex c) {
        return this.id.equals(c.id);
    }

    public int compareTo(Vertex c) {
        return this.id - c.id;
    }

    public ArrayList<Vertex> getNeighbours() {
        ArrayList<Vertex> n = new ArrayList<Vertex>();
        for (Edge e : edges)
            n.add(e.to);
        return n;
    }
}
