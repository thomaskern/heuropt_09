package logic;

import java.util.Random;

public class Utility {

    public static Integer get_random_int(int max){
        Random r = new Random();
        return r.nextInt(max);
    }
}
