package test.models.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.searches.neighborhoods.BlockSwitch;
import logic.searches.neighborhoods.PairSwitch;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.models.TestHelper;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

public class BlockSwitchTest extends TestHelper {
    private Solution solution;
    public ArrayList<Job> jobs;
    public PairSwitch ps;


    @BeforeMethod
    public void setUp() {
        this.f = new Fixtures("fixtures/matrix_4j.txt", "fixtures/capacities.txt", "6_4");
        f.parse_file();
    }

    @Test
    public void should_be_nice() {
        Job job1 = new Job(1);
        Job job2 = new Job(2);
        Job job3 = new Job(3);
        Job job4 = new Job(4);
        solution = new Solution();
        solution.jobsequence.add(job1);
        solution.jobsequence.add(job2);
        solution.jobsequence.add(job3);
        solution.jobsequence.add(job4);


        BlockSwitch bs = new BlockSwitch(2,f);
        bs.init(solution);

        System.out.println(bs.next());

        assertTrue(bs.next().is_valid());


    }

}
