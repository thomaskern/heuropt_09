package data.tree;

import data.Edge;
import data.Node;
import data.NodeList;

import java.util.HashMap;               // for Stack class

public class Trie {
    public NodeList getNodes() {
        return nodes;
    }

    public TreeNode getRoot() {
        return root;
    }

    public boolean contains_node(Node node) {
        return nodes.contains(node);
    }

    public int size() {
        return nodes.size();
    }

    private NodeList nodes;
    private TreeNode root;
    private HashMap<Integer, TreeNode> treenodes;

    // -------------------------------------------------------------
    public Trie()                  // constructor
    {
        nodes = new NodeList();
        root = null;
        treenodes = new HashMap<Integer, TreeNode>();
    }            // no nodes in tree yet

    // -------------------------------------------------------------
    public TreeNode find(int key)      // find node with given key
    {
        return treenodes.get(key);
    }

    public void insert(Edge edge) {
        TreeNode start = new TreeNode();
        start.set_data_node(edge.getEnd());

        TreeNode tn = find(edge.getStart().getId());

        tn.add_child(start);

        nodes.add_node(edge.getEnd());
        treenodes.put(edge.getEnd().getId(), start);
    }

    // -------------------------------------------------------------
    public void insert(Node node) {
        TreeNode newTreeNode = new TreeNode();
        newTreeNode.set_data_node(node);
        newTreeNode.autoParent();
        nodes.add_node(node);

        if (root == null) {
            root = newTreeNode;
            treenodes.put(node.getId(), root);
        }
    }

    // -------------------------------------------------------------
    public void displayTree() {
        int nBlanks = 4;
        System.out.println("......................................................");
        _displayTree(root, 0, nBlanks);
        System.out.println("......................................................");
    }

    private void _displayTree(TreeNode tn, int level, int nBlanks) {
        for (int j = 0; j < nBlanks * level; j++)
            System.out.print(' ');

        System.out.print(tn.getId() + "(" + tn.getParent().getId() + "), (CTP: "+tn.getDataNode().distance_to(tn.getParent().getDataNode())+"), (CE: "+tn.costliest_edge()+")\n");
        for (TreeNode treeNode : tn.getChildren()) {
            _displayTree(treeNode, level + 1, nBlanks);
        }
    }

    public double cost() {
        double cost = 0;
        for (TreeNode treeNode : treenodes.values()) {
            cost += treeNode.costliest_edge();            
        }

        return cost;
//        return _cost(root, 0.0);
    }

    private double _cost(TreeNode tn, double _cost) {

        double cost = 0;
        if (tn.getChildren().size() > 0) {
            cost = tn.costliest_edge();
            for (TreeNode tn1 : tn.getChildren()) {
                cost += _cost(tn1, 0);
            }
        }

        return _cost + cost;
    }

    public boolean valid(int graph_size) {
        return size() == graph_size;
    }
}
