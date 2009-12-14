package data;

public class Fixtures {

    public static Graph parse(String filename){
        Graph g = new Graph();
        for (String line : File.read_lines(filename))
            parse_line(line, g);
        g.finish_parsing();
        return g;
    }

    private static void parse_line(String line, Graph g) {
        String[] coords = line.split(" ");
        Node n = new Node(g.getNodes().size() + 1, coords[0], coords[2]);
        g.getNodes().add(n);
    }

}
