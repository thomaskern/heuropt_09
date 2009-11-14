package test.models.construction;

import data.Fixtures;
import logic.construction.ConstructionHeuristic;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.models.TestHelper;

import static org.testng.Assert.assertTrue;

public class ConstructionHeuristicTest extends TestHelper {
    @BeforeTest
    public void setUp() {
        this.f = new Fixtures("fixtures/graph_matrix_3_3.txt", "fixtures/capacities.txt", "3_3");
        f.parse_file();
    }

    @Test
    public void should_create_valid_solution() {
        ConstructionHeuristic c = new ConstructionHeuristic();
        assertTrue(c.create_solution(this.f.get_jobs_as_arraylist(), f).is_valid());
    }

    @Test
    public void should_create_valid_solution_big() {
        this.f = new Fixtures("matrices/matrix_10j_10to_NSS_0.txt","matrices/capacities.txt","10_10");
        f.parse_file();

        ConstructionHeuristic c = new ConstructionHeuristic();
        assertTrue(c.create_solution(this.f.get_jobs_as_arraylist(),f).is_valid());
    }

        @Test
    public void should_create_valid_solution_huge() {
        this.f = new Fixtures("matrices/matrix_40j_60to_NSS_0.txt","matrices/capacities.txt","40_60");
        f.parse_file();

        ConstructionHeuristic c = new ConstructionHeuristic();
        assertTrue(c.create_solution(this.f.get_jobs_as_arraylist(),f).is_valid());
    }
}