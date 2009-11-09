package logic.graph;

public class Edge {

    public int cost;
    public Vertex to;
    public Vertex from;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
        cost = from.cost_to_vertex(to);
    }
}
