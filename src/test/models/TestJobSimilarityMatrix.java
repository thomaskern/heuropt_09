package test.models;

import data.Fixtures;
import data.JobSimilarityMatrix;
import models.Job;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestJobSimilarityMatrix extends TestHelper {
    private JobSimilarityMatrix m;

    @BeforeMethod
    public void load_fixtures() {
        this.f = new Fixtures("fixtures/matrix_3j_3to_NSS_0.txt");
        f.parse_file();
        m = new JobSimilarityMatrix(get_jobs());
    }

    @Test
    public void should_be_equal_if_comparing_same_job() {
        assertEquals((Object) m.similarity_between(get_jobs().get(0).id, get_jobs().get(0).id), get_jobs().get(0).getTools().size());
    }

    @Test
    public void should_calculate_similarity_of_three_jobs() {
        assertEquals(m.similarity_between(get_jobs().get(0).id, get_jobs().get(1).id), (Object) 1);
        assertEquals((Object) m.similarity_between(get_jobs().get(0).id, get_jobs().get(2).id), 2);
    }

    @Test
    public void should_return_all_similarities_to_other_jobs_for_a_specific_job() {
        assertEquals(m.get(get_jobs().get(0).id), new Integer[]{3, 1, 2});
    }
}
