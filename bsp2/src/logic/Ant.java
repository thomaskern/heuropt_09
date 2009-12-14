package logic;

import data.Edge;
import data.EdgeList;
import data.Graph;
import data.Node;
import data.tree.Tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Ant extends Thread {
    private Aco aco;
    private Graph graph;
    private Tree tree;

    public Ant(Aco aco, Graph graph) {
        this.aco = aco;
        this.graph = graph;
        tree = new Tree();
    }

    public void run() {
        init_broadcast_tree();
        local_search();

        this.aco.ant_done(this);
    }

    private void local_search() {
        while (!tree.valid(graph.size())) {
            int next = Utility.get_random_int(100);
            EdgeList nh = calculate_probabilities_for_nh(get_neighborhood());

            Collections.sort(nh, new EdgeCostSorter());

            double t = 0;
            for (Edge edge : nh) {
                if(t + edge.getProbability() * 100 > next){
                    tree.insert(edge.getEnd());
                    break;
                }
                t += edge.getProbability()*100;
            }
        }
        System.out.println(tree.size());
        tree.displayTree();
    }

    private EdgeList calculate_probabilities_for_nh(EdgeList neighborhood) {
        HashMap<Edge, Double> edge_costs = calculate_edge_costs(neighborhood);
        double total = sum_costs(edge_costs);

        for (Edge e : neighborhood) {
            e.setProbability(calculate_probability_for_edge(e, edge_costs, total));
        }

        return neighborhood;
    }

    private double sum_costs(HashMap<Edge, Double> edge_costs) {
        double cost = 0;

        for (double v : edge_costs.values()) {
            cost += v;
        }

        return cost;
    }

    private HashMap<Edge, Double> calculate_edge_costs(EdgeList neighborhood) {
        HashMap<Edge, Double> hm = new HashMap<Edge, Double>();

        for (Edge e : neighborhood) {
            hm.put(e, Math.pow(graph.get_pheromone_for_edge(e), graph.getAlpha()) * Math.pow(e.cost(), graph.getBeta()));
        }

        return hm;
    }

    private double calculate_probability_for_edge(Edge e, HashMap<Edge, Double> edge_costs, double total) {
        return edge_costs.get(e) / total;
    }

    private EdgeList get_neighborhood() {
        EdgeList el = new EdgeList();

        for (Node node : tree.getNodes()) {
            for (Edge e : graph.getEdges()) {
                if (e.getStart() != node || tree.contains_node(e.getEnd()))
                    continue;

                el.add(e);
            }
        }

        return el;
    }

    private void init_broadcast_tree() {
        tree.insert(graph.start_node());
    }

    public Tree getTree() {
        return tree;
    }
}
