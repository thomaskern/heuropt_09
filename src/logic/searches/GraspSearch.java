package logic.searches;

import data.Fixtures;
import data.Solution;
import logic.construction.Grasp;

public class GraspSearch {
    private Fixtures fixtures;

    public GraspSearch(Fixtures fixtures) {

        this.fixtures = fixtures;
    }

    public Solution run(){
        Grasp g = new Grasp(fixtures);
        Solution s = g.create_solution();
        return s;
        
    }
}
