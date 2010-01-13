package logic;

import java.io.File;
import java.util.Random;

public class Utility {

    public static final boolean DEBUG = false;

    public static Integer get_random_int(int max) {
        Random r = new Random();
        return 1 + r.nextInt(max - 1);
    }

    public static int next_log_file_id() {
        File d = new File("logs/");
        return d.listFiles().length + 1;
    }

    public static int available_processor() {
        int c = Runtime.getRuntime().availableProcessors();

        if (c > 8)
            c = 8;

        return DEBUG ? 1 : c;
    }

    public static void print_time(long time) {
        System.out.println("Time used: " + (System.currentTimeMillis() - time));
    }
}
