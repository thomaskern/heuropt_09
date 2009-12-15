package data;

public class Graph {
    private NodeList nodes;
    private double[][] distance_matrix;
    private double[][] pheromone_matrix;
    private double pheromone_init;
    private double pheromone_evaporation;
    private double[] pheromone_limits;
    private double alpha;
    private double beta;
    private EdgeList edges;

    public Graph() {
        create(0.5, 0.1, 0.5, 0.5);
    }

    public Graph(double pheromone_init, double pheromone_evaporation, double alpha, double beta) {
        create(pheromone_init, pheromone_evaporation, alpha, beta);
    }

    private void create(double pheromone_init, double pheromone_evaporation, double alpha, double beta) {
        nodes = new NodeList();
        this.alpha = alpha;
        this.beta = beta;
        this.pheromone_init = pheromone_init;
        this.pheromone_evaporation = pheromone_evaporation;
        this.pheromone_limits = new double[]{0.0, 10.0};
    }

    public NodeList getNodes() {
        return nodes;
    }

    public Node start_node() {
        return nodes.get(0);
    }

    public Integer size() {
        return nodes.size();
    }

    public void finish_parsing() {
        this.edges = new EdgeList();
        this.distance_matrix = new double[size()][size()];
        this.pheromone_matrix = new double[size()][size()];

        create_edges();
        calculate_pheromone_matrix();
        calculate_distance_matrix();
    }

    private void create_edges() {
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int z = i + 1; z < nodes.size(); z++) {
                Node n1 = nodes.get(z);

                Edge e = new Edge(n, n1);
                Edge e1 = new Edge(n1, n);
                edges.add(e);
                edges.add(e1);
            }
        }
    }

    private void calculate_pheromone_matrix() {
        for (int i = 0; i < size(); i++) {
            for (int z = 0; z < size(); z++) {
                pheromone_matrix[i][z] = pheromone_init;
            }
        }
    }

    private void calculate_distance_matrix() {
        for (int i = 0; i < size(); i++) {
            Node n = nodes.get(i);
            for (int z = 0; z < size(); z++) {
                distance_matrix[i][z] = n.distance_to(nodes.get(z));
            }
        }
    }

    public double[][] getDistanceMatrix() {
        return this.distance_matrix;
    }

    public double[][] getPheromoneMatrix() {
        return pheromone_matrix;
    }

    public double getPheromoneEvaporation() {
        return pheromone_evaporation;
    }

    public double[] getPheromoneLimits() {
        return pheromone_limits;
    }

    public double getBeta() {
        return beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public EdgeList getEdges() {
        return edges;
    }

    public double get_pheromone_for_edge(Edge e) {
        return get_pheromone_for_edge(e.getStart().getId(), e.getEnd().getId());

    }

    public double get_pheromone_for_edge(int from, int to) {
        return pheromone_matrix[from - 1][to - 1];
    }

    public void update_pheromone_value(int from, int to, double v) {
        if (v < 0)
            v = 0;
        pheromone_matrix[from - 1][to - 1] = v;
    }
}
