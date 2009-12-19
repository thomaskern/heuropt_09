package data.tree;

import data.Edge;
import data.Node;
import data.NodeList;

import java.util.Collection;
import java.util.HashMap;

public class Trie {
    private NodeList nodes;
    private TrieNode root;
    private HashMap<Integer, TrieNode> treenodes;
    private HashMap<Integer, Integer> hm_cost;

    public Trie() {
        nodes = new NodeList();
        root = null;
        treenodes = new HashMap<Integer, TrieNode>();
        hm_cost = new HashMap<Integer, Integer>();
    }

    public TrieNode find(int key)      // find node with given key
    {
        return treenodes.get(key);
    }

    public void insert(Edge edge) {
        TrieNode start = new TrieNode();
        start.set_data_node(edge.getEnd());

        TrieNode tn = find(edge.getStart().getId());

        tn.add_child(start);

        nodes.add_node(edge.getEnd());
        treenodes.put(edge.getEnd().getId(), start);
    }

    public void insert(Node node) {
        TrieNode newTrieNode = new TrieNode();
        newTrieNode.set_data_node(node);
        newTrieNode.autoParent();
        nodes.add_node(node);

        if (root == null) {
            root = newTrieNode;
            treenodes.put(node.getId(), root);
        }
    }

    public void displayTree() {
        int nBlanks = 4;
        System.out.println("......................................................");
        _displayTree(root, 0, nBlanks);
        System.out.println("......................................................");
    }

    private void _displayTree(TrieNode tn, int level, int nBlanks) {
        for (int j = 0; j < nBlanks * level; j++)
            System.out.print(' ');

        System.out.print(tn.getId() + "(" + tn.getParent().getId() + "), (CTP: " + tn.getDataNode().distance_to(tn.getParent().getDataNode()) + "), (CE: " + (int) tn.costliest_edge() + ")\n");
        for (TrieNode trieNode : tn.getChildren()) {
            _displayTree(trieNode, level + 1, nBlanks);
        }
    }

    // cache purely based on size of tree, if tree changes without size, one would have to clear the cache
    public int cost() {
        if (hm_cost.containsKey(size()))
            return hm_cost.get(size());
        else {
            double cost = 0;
            for (TrieNode trieNode : treenodes.values()) {
                cost += trieNode.costliest_edge();
            }

            hm_cost.put(size(), (int) cost);
            return (int) cost;
        }
    }

    public boolean valid(int graph_size) {
        return size() == graph_size;
    }

    public NodeList getNodes() {
        return nodes;
    }

    public TrieNode getRoot() {
        return root;
    }

    public boolean contains_node(Node node) {
        return nodes.contains(node);
    }

    public int size() {
        return nodes.size();
    }

    public Collection<TrieNode> getTreeNodes() {
        return treenodes.values();
    }

    public int rounded_cost() {
        return (int) Math.round(Math.cbrt(cost()));
    }

    public void delete_node(Node node) {
        TrieNode trienode = find(node.getId());
        trienode.getParent().getChildren().remove(trienode);
        treenodes.remove(node.getId());

        int counter = trienode.getChildren().size();
        for(int i = 0; i < counter;i++){
            delete_node(trienode.getChildren().get(0).getDataNode());
        }

    }
}
