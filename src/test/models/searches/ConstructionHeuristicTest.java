package test.models.searches;

import data.Fixtures;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.models.TestHelper;

public class ConstructionHeuristicTest extends TestHelper {
    @BeforeTest
    public void setUp() {
        this.f = new Fixtures("fixtures/matrix_3j_3to_NSS_0.txt");
        f.parse_file();
    }

    @Test
    public void should_(){
        
    }
}
