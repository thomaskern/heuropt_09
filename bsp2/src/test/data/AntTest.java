package test.data;

import data.Fixtures;
import data.Graph;
import logic.Aco;
import logic.Ant;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AntTest {

    private Graph graph;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-01.dat");
    }

    @Test
    public void should_initiate_multiple_ants() {
        Aco aco = new Aco();
        Ant a = new Ant(1,aco,graph);
        a.run();
    }

}
