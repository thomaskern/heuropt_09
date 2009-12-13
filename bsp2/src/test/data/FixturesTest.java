package test.data;

import data.Fixtures;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FixturesTest {

    @Test
    public void should_read_fixtures_from_file() {
        Fixtures f = new Fixtures("mebp/mebp-01.dat");
        assertEquals(f.size(), (Object) 20);
    }

    @Test
    public void should_parse_doubles_from_file() {
        Fixtures f = new Fixtures("mebp/mebp-01.dat");
        assertEquals(f.getNodes().get(0).x, 296.0);
        assertEquals(f.getNodes().get(0).y, 637.6);
        assertEquals(f.getNodes().get(13).y, 129.8);
    }

    @Test
    public void should_set_uniq_node_ids() {
        Fixtures f = new Fixtures("mebp/mebp-01.dat");
        assertTrue(f.getNodes().get(0).id != f.getNodes().get(1).id);
    }

}
