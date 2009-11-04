package data;

import java.util.HashMap;

public class Capacity {
    private static HashMap<String, Integer> capacities;


    public static void load(String capacity_filename) {
        capacities = new HashMap<String, Integer>();
        for (String line : File.read_lines(capacity_filename))
            parse_line(line);
    }

    private static void parse_line(String strLine) {
        String[] splits = strLine.split(":");
        capacities.put(splits[0] + "_" + splits[1], Integer.parseInt(splits[2]));
    }

    public static Integer get(String capacity_id) {
        return capacities.get(capacity_id);
    }

}
