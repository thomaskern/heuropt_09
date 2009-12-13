package data;

import java.io.*;
import java.util.ArrayList;

public class File {
    public static ArrayList<String> read_lines(String filename) {
        FileInputStream fstream;
        ArrayList<String> list = new ArrayList<String>();

        try {
            fstream = new FileInputStream(filename);

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
