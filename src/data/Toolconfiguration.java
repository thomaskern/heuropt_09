package data;

import java.util.ArrayList;

public class ToolConfiguration extends ArrayList<Tool> {

    public String toString() {
        String ret = " ";
        for (Tool t : this) {
            ret += t.id + ",";

        }
        return ret.substring(0, ret.length() - 1);
    }


    public int dissimilarity(ToolConfiguration tc) {
        int cost = 0;

        for (Tool f : this) {
            boolean found = false;
            for (Tool s : tc) {
                if (s == f) {
                    found = true;
                    break;
                }
            }
            if (!found)
                cost += 1;
        }

        return cost;
    }

    public boolean includes_tool(Tool t) {
        return this.contains(t);
    }
}
