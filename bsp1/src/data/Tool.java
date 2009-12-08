package data;

import java.util.HashMap;

public class Tool implements Comparable<Tool> {

    public static HashMap<Integer, Tool> tools = new HashMap<Integer, Tool>();
    public Integer id;

    private Tool(Integer id) {
        this.id = id;
    }

    public String toString() {
        return "tool@" + id;
    }

    public static Tool find_or_create(String tool) {
        return find_or_create(Integer.parseInt(tool));
    }

    public static Tool find_or_create(Integer integer) {
        if (tools.containsKey(integer)) {
            return tools.get(integer);
        } else {
            Tool t = new Tool(integer);
            tools.put(integer, t);
            return t;

        }
    }

    public int compareTo(Tool o) {
        return id - o.id;
    }
}
