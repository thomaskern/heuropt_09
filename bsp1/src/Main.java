/*
 * Thomas Kern und Christian Weichselbaum
 * 0326577 und 0525522
 * Heuristische Optimierungsverfahren WS 2009/2010
 */

import data.Fixtures;
import test.models.searches.LocalSearchTest;

public class Main {

    public static void main(String[] args) {

        Fixtures f = new Fixtures("matrices/matrix_10j_9to_NSS_0.txt", "matrices/capacities.txt", "9_10");
        f.parse_file();

    }

}
