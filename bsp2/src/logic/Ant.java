package logic;

import data.*;
import data.tree.Trie;
import logic.search.Vnd;

import java.util.Collections;
import java.util.HashMap;

public class Ant extends Thread {
    private int id;

    private Aco aco;
    private Graph graph;
    private Trie tree;
    private EdgeList nh;


    public Ant(int id, Aco aco, Graph graph) {
        this.id = id;
        this.aco = aco;
        this.graph = graph;
        tree = new Trie();
    }

    public void run() {
        this.nh = new EdgeList();

        System.out.println("START CON");
        construct_broadcast_tree();
        System.out.println("START LS");
        local_search();

        this.aco.ant_done(this);
    }

    private void local_search() {
        Sweep s = new Sweep();
        tree = s.run(tree, 3);

        Vnd v = new Vnd();
        tree = v.run(tree);
    }

    private Edge find_edge(EdgeList nh) {
        int next = Utility.get_random_int(100);
        double t = 0;

        for (Edge edge : nh) {
            if (t + edge.getProbability() > next) {
                return edge;
            }
            t += edge.getProbability();
        }
        return null;
    }

    /* adds implicit edges? */
    private NodeList add_edges(EdgeList nh, Edge _edge) {
        NodeList added_nodes = new NodeList();
        if (_edge != null) {
            added_nodes.add(_edge.getEnd());

            for (Edge edge : nh) {
                if (_edge.getStart() == edge.getStart() &&
                        edge.cost() < _edge.cost() && !tree.contains_node(edge.getEnd())) {
                    tree.insert(edge);
                    added_nodes.add(edge.getEnd());
                }
            }
            tree.insert(_edge);
        }
        return added_nodes;
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
            hm.put(e, calculate_heuristic_value(e, graph));
        }

        return hm;
    }

    private Double calculate_heuristic_value(Edge e, Graph graph) {
        return Math.pow(graph.get_pheromone_for_edge(e), graph.getAlpha()) * Math.pow(e.cost(), graph.getBeta());
    }

    /*  - total are the full costs of the whole neighborhood */
    private double calculate_probability_for_edge(Edge e, HashMap<Edge, Double> edge_costs, double total) {
        return (edge_costs.get(e) * 100) / total;
    }

    private EdgeList get_neighborhood() {
        EdgeList el = new EdgeList();

        for (Node node : tree.getNodes()) {
            EdgeList edges = get_edges_for_node(node);
            el.addAll(edges);
        }

        return el;
    }

    private EdgeList get_edges_for_node(Node node) {
        EdgeList edges = new EdgeList();

        for (Edge e : graph.getEdges()) {
            if (e.getStart() != node || tree.contains_node(e.getEnd())) {
                continue;
            }

            e.setHeuristicValue(calculate_heuristic_value(e, graph));
            edges.add(e);
        }

        Collections.sort(edges, new HeuristicValueEdgeSorter());
        return edges;
    }

    private void add_best_edges_to_nh(EdgeList el, EdgeList edges) {
        for (int i = 0; i < (int) Math.ceil(edges.size() * 0.5); i++) {
            el.add(edges.get(i));
        }
//        el.addAll(edges);
    }

    private void construct_broadcast_tree() {
        tree.insert(graph.start_node());
        this.nh.addAll(get_neighborhood());

        long time = System.currentTimeMillis();

        while (!tree.valid(graph.size())) {
            System.out.println("N" + id + " :" + tree.size()+"::"+nh.size());
            EdgeList nh = calculate_probabilities_for_nh(filter_nh(this.nh));
            Collections.sort(nh, new EdgeCostSorter());

            update_nh(add_edges(nh, find_edge(nh)));
        }

        Utility.print_time(time);
    }

    private void update_nh(NodeList edgeList) {
        for (Node n : edgeList) {
            nh.addAll(get_edges_for_node(n));
        }
    }

    private EdgeList filter_nh(EdgeList neighborhood) {
        EdgeList n = new EdgeList();
        add_best_edges_to_nh(n, neighborhood);
        return n;
    }

    public Trie getTree() {
        return tree;
    }
}
