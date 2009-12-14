package data;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Fixtures {

    public static Graph parse(String filename) {
        Graph g = new Graph();
        Pattern p = Pattern.compile("\\s+");

        for (String line : File.read_lines(filename))
            parse_line(line, g, p);
        g.finish_parsing();
        return g;
    }

    private static void parse_line(String line, Graph g, Pattern p) {
        String[] coords = p.split(line);
        g.getNodes().add(new Node(g.getNodes().size() + 1, coords[0], coords[1]));
    }

}
