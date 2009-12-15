package logic;

import java.io.File;
import java.util.Random;

public class Utility {

    public static Integer get_random_int(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    public static int next_log_file_id() {

        File d = new File("logs/");
        return d.listFiles().length + 1;
    }
}