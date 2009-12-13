package data;

import java.util.ArrayList;

public class Fixtures {
    private String filename;

    public NodeList getNodes() {
        return nodes;
    }

    private NodeList nodes;


    public Fixtures(String filename){
        this.filename = filename;
        this.nodes = new NodeList();
        parse_file();
    }

    private void parse_file() {
        for (String line : File.read_lines(filename))
            parse_line(line);
    }

    private void parse_line(String line) {
        String[] coords = line.split(" ");
        Node n = new Node(this.nodes.size()+1,coords[0], coords[2]);
        this.nodes.add(n);
    }

    public Integer size() {
        return nodes.size();
    }
}
