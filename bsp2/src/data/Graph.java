package data;

public class Graph {
    private NodeList nodes;
    private double[][] distance_matrix;
    private double[][] pheromone_matrix;
    private double pheromone_init;

    public Graph() {
        create(0.5);
    }

    private void create(double pheromone_init) {
        nodes = new NodeList();
        this.pheromone_init = pheromone_init;
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
        this.distance_matrix = new double[size()][size()];
        this.pheromone_matrix = new double[size()][size()];

        calculate_pheromone_matrix();
        calculate_distance_matrix();
    }

    private void calculate_pheromone_matrix() {
         for(int i =0; i < size();i++){
            for(int z = 0; z < size();z++){
                pheromone_matrix[i][z] = pheromone_init;
            }
        }
    }

    private void calculate_distance_matrix() {
        for(int i =0; i < size();i++){
            Node n = nodes.get(i);
            for(int z = 0; z < size();z++){
                distance_matrix[i][z] = n.distance_to(nodes.get(z));
            }
        }
    }

    public double[][] getDistanceMatrix() {
        return this.distance_matrix;
    }

    public double[][] getPheromone_matrix() {
        return pheromone_matrix;
    }
}
