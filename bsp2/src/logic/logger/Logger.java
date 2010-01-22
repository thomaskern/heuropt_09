package logic.logger;

import java.io.File;

public abstract class Logger {
    public static int ERR = 3;
    public static int NOTICE = 5;
    public static int DEBUG = 7;
    protected int mask;

    protected Logger next;

    public Logger setNext(Logger l) {
        next = l;
        return l;
    }

    public void message(String msg, int priority) {
        if (priority <= mask) {
            writeMessage(msg, true);
        }
        if (next != null) {
            next.message(msg, priority);
        }
    }

    abstract protected void writeMessage(String msg, boolean b);

    public void message(String s) {
        message(s, 0);
    }

    public static void clear_logdir() {
        File dir = new File("logs/");
        for(File f : dir.listFiles()){
            f.delete();
        }
    }

    public void message(String msg, boolean linebreak) {
         writeMessage(msg,linebreak);

        if (next != null) {
            next.message(msg, 0);
        }
    }
}


