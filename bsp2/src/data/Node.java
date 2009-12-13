package data;

public class Node {
    public double x;
    public double y;
    public int id;

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
}
