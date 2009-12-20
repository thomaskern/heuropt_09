package data;

public class Edge implements Comparable<Edge> {
    private Node start;
    private Node end;
    private double probability;
    private Double cost;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
        cost = -1.0;
    }

    public double cost() {
        if (cost == -1)
            cost = start.distance_to(end);
        return cost;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public int compareTo(Edge o) {
        int i = exponent_needed(o);
        return (int) (cost() * i - o.cost() * i);
    }

    /* ?? */
    private int exponent_needed(Edge o) {
        int i = 0;
        double tmp = o.cost() < cost() ? o.cost() : cost();
        while(true){
            if(tmp * i > 10)
                break;
            i++;
        }
        return i;
    }
}
