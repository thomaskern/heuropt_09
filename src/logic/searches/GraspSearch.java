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
        Solution s;
        Runnable r = new Runnable(){

            public void run() {
                Grasp g = new Grasp(fixtures);
                Solution s = g.create_solution();
            }
        };





        Thread t = new Thread(r);
        t.run();

        System.out.println("RUN");
        while(t.isAlive()){

            System.out.println("ALIVE");

        }



        System.out.println("NICE");

         return null;


        
    }
}
