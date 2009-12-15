package data.tree;

import data.Edge;
import data.Node;
import data.NodeList;

import java.util.Collection;
import java.util.HashMap;

public class Trie {
    private NodeList nodes;
    private TreeNode root;
    private HashMap<Integer, TreeNode> treenodes;
    private HashMap<Integer, Integer> hm_cost;

    public Trie() {
        nodes = new NodeList();
        root = null;
        treenodes = new HashMap<Integer, TreeNode>();
        hm_cost = new HashMap<Integer, Integer>();
    }

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

    public void displayTree() {
        int nBlanks = 4;
        System.out.println("......................................................");
        _displayTree(root, 0, nBlanks);
        System.out.println("......................................................");
    }

    private void _displayTree(TreeNode tn, int level, int nBlanks) {
        for (int j = 0; j < nBlanks * level; j++)
            System.out.print(' ');

        System.out.print(tn.getId() + "(" + tn.getParent().getId() + "), (CTP: " + tn.getDataNode().distance_to(tn.getParent().getDataNode()) + "), (CE: " + (int)tn.costliest_edge() + ")\n");
        for (TreeNode treeNode : tn.getChildren()) {
            _displayTree(treeNode, level + 1, nBlanks);
        }
    }

    public int cost() {
        if (hm_cost.containsKey(size()))
            return hm_cost.get(size());
        else {
            double cost = 0;
            for (TreeNode treeNode : treenodes.values()) {
                cost += treeNode.costliest_edge();
            }

            hm_cost.put(size(), (int)cost);
            return (int)cost;
        }
    }

    public boolean valid(int graph_size) {
        return size() == graph_size;
    }

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

    public Collection<TreeNode> getTreeNodes() {
        return treenodes.values();
    }
}
