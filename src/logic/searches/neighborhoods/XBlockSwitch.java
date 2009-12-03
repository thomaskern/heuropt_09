package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;
import java.util.Random;

/*
 *  Rotation Neighborhood - Jobs are rotated triplet wise. The last job of the
 *  triplet is moved to its beginning and the other two jobs are shifted right.
 *
 **/

public class XBlockSwitch implements INeighborhood {

    private ArrayList<Job> jobsequence;
    private Fixtures fixtures;
    private int i;
    private int j;
    private int n;
    private int bsize;
    private int blocks;

    public XBlockSwitch(Fixtures fixtures, int blockNumber) {
        this.fixtures = fixtures;
        this.blocks = blockNumber;
    }

    public ArrayList<Solution> getNeighborhood(Solution solution) {
        init(solution);
         ArrayList<Solution> neighborhood = new ArrayList<Solution>();

     /* If invalid blocksize is selected null is returned */
       if(blocks > n )
            return null;

        for (int k = 1; k <= blocks; k++) {
            for (int l = k; l <= blocks; l++) {
                if (k != l) {
                    neighborhood.add(get_solution(switchBlocks(l, k)));
                }
            }
        }

         return neighborhood;
    }

    public void init(Solution solution) {
  
        this.jobsequence = solution.jobsequence;
        this.n = jobsequence.size();
        this.bsize = n / blocks;
        this.i = 1;
        this.j = 1;
    }

    public Solution next() {
        /* If invalid blocksize is selected null is returned */
       if(blocks > n )
            return null;

       if (!((i == (blocks)) && j == blocks)) {
            Solution s = get_solution(switchBlocks(j, i));

            if (j == (blocks)) {
                i++;
                j = i + 1;
            } else {
                j++;
            }

            return s;

        } else {
            return null;
        }
    }
    

    public Solution random() {
        
        /* If invalid blocksize is selected null is returned */
       if(blocks > n )
            return null;

        int a = 1;
        int b = 1;

        while (a == b) {
            a = 1 + (new Random()).nextInt(blocks-1);
            b = 1 + (new Random()).nextInt(blocks-1);
        }

        return get_solution(switchBlocks(b, a));
    }


    private ArrayList<Job> switchBlocks(int a, int b){
        ArrayList<Job> newJobSequence = new ArrayList<Job>();
        ArrayList<Job> block = new ArrayList<Job>();

        for (Job job : jobsequence) {
            newJobSequence.add(job);
        }

       for(int i = 0; i < bsize;i++){
           block.add(newJobSequence.get(((a-1)*bsize)+i));
           newJobSequence.set((((a-1)*bsize)+i), newJobSequence.get(((b-1)*bsize)+i));
       }

       for(int i = 0; i < bsize; i++){
           newJobSequence.set((((b-1)*bsize)+i), block.get(i));
       }

       return newJobSequence;
    }

    private Solution get_solution(ArrayList<Job> sequence) {
        return new Ktns(sequence, fixtures).run();
    }
}
