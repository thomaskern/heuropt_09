package data;

public class Edge {
    private Node start;
    private Node end;
    private double probability;

    public Edge(Node start, Node end){
        this.start = start;
        this.end = end;
    }

    public double cost(){
        return start.distance_to(end);
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
}
