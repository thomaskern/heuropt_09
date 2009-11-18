/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.models.searches.neighborhoods;

import data.Job;
import data.Solution;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * @author Christian
 */
public class PairSwitchTest {
    private Solution solution;
    public ArrayList<Job> jobs;

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
    }

    @Test
    public void aTest() {
        System.out.println("Test");
    }

    @AfterMethod
    public void cleanUp() {
        // code that will be invoked after this test ends
    }
}
