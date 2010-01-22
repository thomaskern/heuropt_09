package logic.logger;

class StdoutLogger extends Logger {

    public StdoutLogger(int mask) {
        this.mask = mask;
    }

    protected void writeMessage(String msg, boolean b) {
        if(b)
            System.out.println("Writing to stdout: " + msg);
        else
            System.out.print("Writing to stdout: " + msg);
    }
}
