package test.data;

import data.Fixtures;
import data.Graph;
import logic.Aco;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class AcoTest {

    private Graph graph;

    @BeforeMethod
    public void before_method() {
        graph = Fixtures.parse("mebp/mebp-01.dat");
        double cost = 0;
        ArrayList<Double> costs = new ArrayList<Double>();
//        for(double d : graph.getDistanceMatrix()[0]){
//            if(d >cost)
//                cost = d;
//        }

        for(int i = 0; i < graph.getDistanceMatrix().length;i++){
            for(int z = 0; z < graph.getDistanceMatrix()[i].length;z++){
                costs.add(graph.getDistanceMatrix()[i][z]);
            }

        }
        Collections.sort(costs);
        System.out.println(costs);
        System.out.println(1116*1116*1116);
        System.out.println((int)Math.pow(1116,3));
    }

    @Test
    public void should_initiate_multiple_ants() {
        Aco aco = new Aco();
        aco.run(graph, 4);
    }

}
