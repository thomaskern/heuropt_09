package data.tree;

import data.Node;

public class TreeNode implements Comparable<TreeNode> {


    private Node data_node;           // data item
    private TreeNodeList children;         // this node's left child
    private TreeNode parent;

    public TreeNode() {
        children = new TreeNodeList();
    }

    public String toString()      // _displayTree ourself
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

    public void add_child(TreeNode end) {
        end.parent = this;
        children.add(end);
    }

    public TreeNodeList getChildren() {
        return children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void autoParent() {
        parent = this;
    }

    public int compareTo(TreeNode o) {
        return getId() - o.getId();
    }

    public double costliest_edge() {
        double cost = 0;
        for (TreeNode treeNode : children) {
            if (treeNode.data_node.distance_to(this.data_node) > cost)
                cost = treeNode.data_node.distance_to(this.data_node);
        }

        return Math.pow(cost,3);
    }

    public void set_data_node(Node _data_node) {
        this.data_node = _data_node;
    }

    public Node getDataNode() {
        return data_node;
    }
}
