package views;

import data.Node;
import data.tree.Trie;
import data.tree.TrieNode;
import logic.Utility;

import java.awt.*;

public class Diagram extends Panel {
    private Trie trie;

    public void draw_list(Trie tree) {
        trie = tree;
    }

    public void paint(Graphics g) {
        if (trie == null)
            return;

        display_node(trie.getRoot(), g, null);
    }

    private void display_node(TrieNode node, Graphics g, Color parent_color) {
        Color c;

        if (trie.getRoot() == node) {
            c = Color.red;
        } else {
            if (node.hasChildren())
                c = set_random_color(g);
            else
                c = parent_color; //belongs to parent, therefor same color as parent
        }

        g.setColor(c);

        draw_node(node.getDataNode(), g);

        if (node.hasChildren()) {
            draw_kids(node, g, c);
            for (TrieNode n : node.getChildren()) {
                display_node(n, g, c);
            }
        }
    }

    private void draw_kids(TrieNode trienode, Graphics g, Color color) {
        if (color == null)
            set_random_color(g);

        draw_node(trienode.getDataNode(), g);

        int cost = (int) Math.cbrt(trienode.costliest_edge());
        int x = (int) Math.ceil(trienode.getDataNode().getX());
        int y = (int) Math.ceil(trienode.getDataNode().getY());
        drawCircle(x, y, cost, g);
    }

    private void drawCircle(int x, int y, int radius, Graphics g) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    private Color set_random_color(Graphics g) {
        Color c = new Color(Utility.get_random_int(255), Utility.get_random_int(255), Utility.get_random_int(255));
        g.setColor(c);
        return c;
    }

    private void draw_node(Node node, Graphics g) {
        g.fillOval((int) node.getX(), (int) node.getY(), 5, 5);
        g.drawString(String.valueOf(node.getId()), (int) node.getX() + 5, (int) node.getY() + 5);
    }
}