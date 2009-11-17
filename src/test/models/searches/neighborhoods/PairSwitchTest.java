/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.models.searches.neighborhoods;

import data.Job;
import data.Solution;
import java.util.ArrayList;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Christian
 */
public class PairSwitchTest {
    private Solution solution;
    public ArrayList<Job> jobs;

    @BeforeClass
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
        solution = new Solution(jobs);
    }

    @Test
    public void aTest() {
        System.out.println("Test");
    }

    @AfterClass
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
