package test.data;

import data.Fixtures;
import data.Graph;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class GraphTest {

    @Test
    public void should_set_nodes(){
        Graph g = Fixtures.parse("mebp/mebp-01.dat");
        assertEquals((Object) g.size(),20);
    }

    @Test
    public void should_calculate_distance_matrix(){
        Graph g = Fixtures.parse("mebp/mebp-01.dat");

        for(int i = 0; i < 20;i++)
            assertEquals(g.getDistanceMatrix()[i][i],0.0);
        assertEquals(g.getDistanceMatrix()[0][1],175.6496797605962);
    }

    @Test
    public void should_init_phero_matrix(){
        Graph g = Fixtures.parse("mebp/mebp-01.dat");

        for(int i = 0; i < 20;i++)
            for(int z = 0; z < 20;z++)
            assertEquals(g.getPheromone_matrix()[i][z],0.5);
    }
}
