package data.tree;

import data.Node;

import java.util.ArrayList;

public class TreeNodeList extends ArrayList<TreeNode> {
    public double max_to_node(Node data_node) {
        double cost = 0;
        for (TreeNode treeNode : this) {
            if (treeNode.getDataNode().distance_to(data_node) > cost)
                cost = treeNode.getDataNode().distance_to(data_node);
        }

        return Math.pow(cost, 3);
    }
}
