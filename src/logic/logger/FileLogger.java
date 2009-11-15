package logic.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;

class FileLogger extends Logger {
    private String filename;

    public FileLogger(String filename, int mask) {
        this.filename = filename;
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        try {
            FileWriter fstream = new FileWriter("logs/"+filename,true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(msg+"\n");
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
