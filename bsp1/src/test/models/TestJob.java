package test.models;

import data.Job;
import org.testng.annotations.Test;

public class TestJob {

    @Test
    public void method1() {
        Job j = new Job(2);
        assert j.id == 2;
    }


}
