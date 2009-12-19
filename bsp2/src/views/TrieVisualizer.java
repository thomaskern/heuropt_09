package views;

import data.tree.Trie;

import java.awt.*;

public class TrieVisualizer extends Frame {
    private Diagram diagram;

    public TrieVisualizer() {
        addPanel();
        //set frame size
        this.setSize(1200, 1200);
        //make this frame visible
        this.setVisible(true);
    }

    private void addPanel() {
        diagram = new Diagram();
        diagram.setSize(1200,1200);
        this.add(diagram);
    }

    public void draw_trie(Trie best) {
        diagram.draw_list(best);
        diagram.repaint();
    }
}