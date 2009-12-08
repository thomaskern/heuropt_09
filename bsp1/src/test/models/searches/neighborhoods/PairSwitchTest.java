/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.models.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import logic.searches.neighborhoods.PairSwitch;

/**
 * @author Christian
 */
public class PairSwitchTest {
    private Solution solution;
    public ArrayList<Job> jobs;
    public PairSwitch ps;

    @BeforeMethod
    public void setUp() {
        // code that will be invoked before this test starts
        Job job1 = new Job(1);
        Job job2 = new Job(2);
        Job job3 = new Job(3);
        Job job4 = new Job(4);
        jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        jobs.add(job4);
        solution = new Solution();
        solution.jobsequence = jobs;

    }

    @Test
    public void aTest() {
        ps = new PairSwitch(new Fixtures("fixtures/matrix_4j.txt", "fixtures/capacities.txt", "6_4"));
        ps.init(solution);
        ArrayList<Solution> neighborhood = ps.getNeighborhood(solution);
        assertTrue((neighborhood.get(0).jobsequence.get(0).id == 2));
        assertTrue((neighborhood.get(0).jobsequence.get(1).id == 1));
        assertTrue((neighborhood.get(0).jobsequence.get(2).id == 3));
        assertTrue((neighborhood.get(0).jobsequence.get(3).id == 4));

        assertTrue((neighborhood.get(1).jobsequence.get(0).id == 3));
        assertTrue((neighborhood.get(1).jobsequence.get(1).id == 2));
        assertTrue((neighborhood.get(1).jobsequence.get(2).id == 1));
        assertTrue((neighborhood.get(1).jobsequence.get(3).id == 4));

        assertTrue((neighborhood.get(2).jobsequence.get(0).id == 4));
        assertTrue((neighborhood.get(2).jobsequence.get(1).id == 2));
        assertTrue((neighborhood.get(2).jobsequence.get(2).id == 3));
        assertTrue((neighborhood.get(2).jobsequence.get(3).id == 1));

        assertTrue((neighborhood.get(3).jobsequence.get(0).id == 1));
        assertTrue((neighborhood.get(3).jobsequence.get(1).id == 3));
        assertTrue((neighborhood.get(3).jobsequence.get(2).id == 2));
        assertTrue((neighborhood.get(3).jobsequence.get(3).id == 4));

        assertTrue((neighborhood.get(4).jobsequence.get(0).id == 1));
        assertTrue((neighborhood.get(4).jobsequence.get(1).id == 4));
        assertTrue((neighborhood.get(4).jobsequence.get(2).id == 3));
        assertTrue((neighborhood.get(4).jobsequence.get(3).id == 2));

        assertTrue((neighborhood.get(5).jobsequence.get(0).id == 1));
        assertTrue((neighborhood.get(5).jobsequence.get(1).id == 2));
        assertTrue((neighborhood.get(5).jobsequence.get(2).id == 4));
        assertTrue((neighborhood.get(5).jobsequence.get(3).id == 3));


    }

    @Test
    public void bTest() {
        ps = new PairSwitch(new Fixtures("fixtures/matrix_4j.txt", "fixtures/capacities.txt", "6_4"));
        ps.init(solution);

        Solution s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 2));
        assertTrue((s.jobsequence.get(1).id == 1));
        assertTrue((s.jobsequence.get(2).id == 3));
        assertTrue((s.jobsequence.get(3).id == 4));

        s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 3));
        assertTrue((s.jobsequence.get(1).id == 2));
        assertTrue((s.jobsequence.get(2).id == 1));
        assertTrue((s.jobsequence.get(3).id == 4));

        s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 4));
        assertTrue((s.jobsequence.get(1).id == 2));
        assertTrue((s.jobsequence.get(2).id == 3));
        assertTrue((s.jobsequence.get(3).id == 1));

        s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 1));
        assertTrue((s.jobsequence.get(1).id == 3));
        assertTrue((s.jobsequence.get(2).id == 2));
        assertTrue((s.jobsequence.get(3).id == 4));

        s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 1));
        assertTrue((s.jobsequence.get(1).id == 4));
        assertTrue((s.jobsequence.get(2).id == 3));
        assertTrue((s.jobsequence.get(3).id == 2));

        s = ps.next();
        assertTrue((s.jobsequence.get(0).id == 1));
        assertTrue((s.jobsequence.get(1).id == 2));
        assertTrue((s.jobsequence.get(2).id == 4));
        assertTrue((s.jobsequence.get(3).id == 3));

        s = ps.next();
        assertTrue(s == null);
    }

    @AfterMethod
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
