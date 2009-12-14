package logic;

public class Utility {

    public static int available_processor() {
        int c = Runtime.getRuntime().availableProcessors();

        if (c > 8)
            c = 8;

        return c;
    }
}
