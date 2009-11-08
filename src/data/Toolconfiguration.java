package data;

import models.Tool;

import java.util.ArrayList;

public class ToolConfiguration extends ArrayList<Tool> {

    public String toString(){
        String ret = "";
        for(Tool t : this){
            ret += " " + t.id;

        }
        return ret;
    }
        

}
