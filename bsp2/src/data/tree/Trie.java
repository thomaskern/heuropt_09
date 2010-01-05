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
    private int display_counter;

    public Trie() {
        nodes = new NodeList();
        root = null;
        treenodes = new HashMap<Integer, TrieNode>();
        hm_cost = new HashMap<Integer, Integer>();
    }

    public TrieNode find(int key) {
        synchronized (treenodes) {
            return treenodes.get(key);
        }

    }

    /* returns depth of tree*/
    public int getDepth(){
        return depth(this.root);
    }

    /*goes down the tree starting node and adds 1 + the depth of the tree below the node */
    private int depth(TrieNode node){
        int max = 0;
        int tmp = 0;
        for(TrieNode child : node.getChildren()){
            tmp = depth(child);
            if(tmp > max){
                max = tmp;
            }
        }
        return 1 + max;
    }
    
    public void delete_node(Node node) {
        synchronized (treenodes) {
            clear_cache();
            TrieNode trienode = find(node.getId());
            trienode.getParent().getChildren().remove(trienode);
            treenodes.remove(node.getId());

            int counter = trienode.getChildren().size();
            for (int i = 0; i < counter; i++) {
                delete_node(trienode.getChildren().get(0).getDataNode());
            }
        }

    }

    public void insert(Edge edge) {
        synchronized (treenodes) {
            clear_cache();

            TrieNode start = new TrieNode();
            start.set_data_node(edge.getEnd());

            TrieNode tn = find(edge.getStart().getId());

            tn.add_child(start);

            nodes.add_node(edge.getEnd());
            treenodes.put(edge.getEnd().getId(), start);
        }
    }

    public void insert(Node node) {
        clear_cache();

        TrieNode newTrieNode = new TrieNode();
        newTrieNode.set_data_node(node);
        newTrieNode.autoParent();
        nodes.add_node(node);

        if (root == null) {
            root = newTrieNode;
            treenodes.put(node.getId(), root);
        }
    }

    private void clear_cache() {
        hm_cost.clear();
    }

    // returns the number of visited nodes
    public int displayTree(boolean show) {
        int nBlanks = 4;
        if (show)
            System.out.println("......................................................");
        display_counter = 0;
        _displayTree(root, 0, nBlanks, show);
        if (show)
            System.out.println("...................................................... " + display_counter);


        for (TrieNode tn : treenodes.values()) {
            System.out.println(tn.getId()+" has parent: "+tn.getParent().getId());
        }


        return display_counter;
    }

    public int displayTree() {
        return displayTree(true);
    }

    private void _displayTree(TrieNode tn, int level, int nBlanks, boolean show) {
        if (show) {
            for (int j = 0; j < nBlanks * level; j++)
                System.out.print(' ');
            System.out.print(tn.getId() + "(" + tn.getParent().getId() + "), (CTP: " + tn.getDataNode().distance_to(tn.getParent().getDataNode()) + "), (CE: " + (int) tn.costliest_edge() + ")\n");
        }

        display_counter++;


        for (TrieNode trieNode : tn.getChildren()) {
            _displayTree(trieNode, level + 1, nBlanks, show);
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

    /*removes child from parent and assigns a new parent to child*/
    public void swap_node(TrieNode child, TrieNode new_node) {
        clear_cache();

        child.getParent().getChildren().remove(child);
        child.setParent(new_node);
        new_node.getChildren().add(child);
    }
}
