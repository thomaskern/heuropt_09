package data.tree;

import data.Node;

public class TrieNode implements Comparable<TrieNode> {
    private Node data_node;           // data item
    private TrieNodeList children;         // this node's left child
    private TrieNode parent;

    public TrieNode() {
        children = new TrieNodeList();
    }

    public String toString()
    {
        String s = "{";
        s += data_node.getId();
        s += ", ";
        s += data_node;
        s += "} ";
        return s;
    }

    public int getId() {
        return data_node.getId();
    }

    public void add_child(TrieNode end) {
        end.parent = this;
        children.add(end);
    }

    public TrieNodeList getChildren() {
        return children;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void autoParent() {
        parent = this;
    }

    public int compareTo(TrieNode o) {
        return getId() - o.getId();
    }

    public double costliest_edge() {
        return children.max_to_node(this.data_node);
    }

    public void set_data_node(Node _data_node) {
        this.data_node = _data_node;
    }

    public Node getDataNode() {
        return data_node;
    }

    public boolean hasChildren() {
        return this.children.size() > 0;
    }
}
