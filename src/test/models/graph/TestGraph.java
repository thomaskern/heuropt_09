package test.models.graph;

import data.Fixtures;
import logic.graph.Graph;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestGraph extends TestHelper {

    @BeforeMethod
    public void load_fixtures() {
        this.f = new Fixtures("fixtures/graph_matrix_3_3.txt", "fixtures/capacities.txt", "3_3");
        f.parse_file();
    }


    @Test
    public void should_return_valid_solution() {
        Graph g = new Graph(f.get_jobs_as_arraylist(), f);
        assertTrue(g.generate_solution().is_valid());
    }

    @Test
    public void should_return_optimal_configuration() {
        Graph g = new Graph(f.get_jobs_as_arraylist(), f);
        assertEquals((Object) g.generate_solution().calculate_costs(), 5);

    }


}
