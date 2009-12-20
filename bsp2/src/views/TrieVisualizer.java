package views;

import data.tree.Trie;

import java.awt.*;

public class TrieVisualizer extends Frame {
    private Diagram diagram;

    public TrieVisualizer() {
        create(3000,0);
    }

    private void create(int i, int i1) {
        addPanel();
        this.setBounds(i,i1,600,600);
        this.setVisible(true);
    }

    public TrieVisualizer(int i, int i1) {
        create(i,i1);
    }

    private void addPanel() {
        diagram = new Diagram();
        diagram.setSize(600,600);
        this.add(diagram);
    }

    public void draw_trie(Trie best) {
        diagram.draw_list(best);
        diagram.repaint();
    }
}