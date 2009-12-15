package data;

public class Node implements Comparable<Node> {
    private double x;
    private double y;
    private int id;

    public Node(int id, double x, double y) {
        assign(id, x, y);
    }

    public Node(int id, String x, String y) {
        assign(id, Double.valueOf(x), Double.valueOf(y));
    }

    public Double distance_to(Node n) {
        return Math.sqrt(Math.pow(n.x - this.x, 2) + Math.pow(n.y - this.y, 2));
    }

    private void assign(int id, Double x, Double y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int compareTo(Node o) {
        return o.getId() - this.id;
    }

}
