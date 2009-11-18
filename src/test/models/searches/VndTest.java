package test.models.searches;

import data.Fixtures;
import org.testng.annotations.BeforeMethod;
import test.models.TestHelper;

public class VndTest extends TestHelper {


    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/ktns_6_4.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }

    public void should_return_valid_solution() {

    }

}
