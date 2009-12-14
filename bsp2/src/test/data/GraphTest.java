package test.data;

import data.Fixtures;
import data.Graph;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GraphTest {
    private Graph g;

    @BeforeMethod
    public void before_method(){
        g = Fixtures.parse("mebp/mebp-01.dat");
    }

    @Test
    public void should_set_nodes(){
        assertEquals((Object) g.size(),20);
    }

    @Test
    public void should_calculate_distance_matrix(){
        for(int i = 0; i < 20;i++)
            assertEquals(g.getDistanceMatrix()[i][i],0.0);
        assertEquals(g.getDistanceMatrix()[0][1],175.6496797605962);
    }

    @Test
    public void should_init_phero_matrix(){
        for(int i = 0; i < 20;i++)
            for(int z = 0; z < 20;z++)
            assertEquals(g.getPheromoneMatrix()[i][z],0.5);
    }
}
