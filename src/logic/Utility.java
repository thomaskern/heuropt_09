package logic;

import java.util.Calendar;
import java.util.Random;

public class Utility {

    public static Integer get_random_int(int max) {
        Calendar cal = Calendar.getInstance();
        Random r = new Random(cal.getTimeInMillis() * cal.getTimeInMillis());
        return r.nextInt(max);
    }
}
