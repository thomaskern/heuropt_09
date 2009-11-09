package test.models;

import data.Fixtures;
import data.JobSimilarityMatrix;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestJobSimilarityMatrix extends TestHelper {
    private JobSimilarityMatrix m;

    @BeforeMethod
    public void load_fixtures() {
        this.f = new Fixtures("fixtures/matrix_3j_3to_NSS_0.txt");
        f.parse_file();
        m = new JobSimilarityMatrix(f.get_jobs_as_arraylist());
    }

    @Test
    public void should_be_equal_if_comparing_same_job() {
        assertEquals((Object) m.similarity_between(f.get_jobs_as_arraylist().get(0).id, f.get_jobs_as_arraylist().get(0).id), f.get_jobs_as_arraylist().get(0).getTools().size());
    }

    @Test
    public void should_calculate_similarity_of_three_jobs() {
        assertEquals(m.similarity_between(f.get_jobs_as_arraylist().get(0).id, f.get_jobs_as_arraylist().get(1).id), (Object) 1);
        assertEquals((Object) m.similarity_between(f.get_jobs_as_arraylist().get(0).id, f.get_jobs_as_arraylist().get(2).id), 2);
    }

    @Test
    public void should_return_all_similarities_to_other_jobs_for_a_specific_job() {
        assertEquals(m.get(f.get_jobs_as_arraylist().get(0).id), new Integer[]{4, 1, 2});
    }

    @Test
    public void should_return_correct_similarities() {
        assertEquals(m.get(2), new Integer[]{2, 2, 3});
    }

    @Test
    public void should_(){

    }
}
