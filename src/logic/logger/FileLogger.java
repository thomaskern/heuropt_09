package logic.logger;

class FileLogger extends Logger {
    private String filename;

    public FileLogger(String filename, int mask) {
        this.filename = filename;
        this.mask = mask;
    }

    protected void writeMessage(String msg) {
        System.out.println("Sending via email: " + msg);
    }
}
