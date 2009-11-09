package test.models.graph;

import data.Fixtures;
import data.Solution;
import logic.graph.Graph;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestGraph extends TestHelper {

    Solution s;

    @BeforeMethod
    public void load_fixtures() {
        this.f = new Fixtures("fixtures/graph_matrix_3_3.txt","fixtures/capacities.txt","2");
        f.parse_file();
    }


    @Test
    public void should_return_valid_solution(){
        Graph g = new Graph(f.get_jobs_as_arraylist(), f.capacity());
        assertTrue(g.generate_solution().is_valid());
    }

    @Test
    public void should_return_optimal_configuration(){
        Graph g = new Graph(f.get_jobs_as_arraylist(), f.capacity());
        assertEquals((Object) g.generate_solution().calculate_costs(),1);

    }


}
