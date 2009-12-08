package logic.searches.neighborhoods;

import data.Fixtures;
import data.Job;
import data.Solution;
import logic.ToolSequences.Ktns;

import java.util.ArrayList;

public class BlockSwitch implements INeighborhood {
    private int number_blocks;
    private Fixtures fixtures;
    private int block_size;
    private Solution solution;

    public BlockSwitch(int numberblocks, Fixtures fixtures) {
        this.number_blocks = numberblocks;
        this.fixtures = fixtures;
    }

    public void init(Solution solution) {
        this.solution = solution;
        this.block_size = solution.jobsequence.size() / number_blocks;
    }

    public ArrayList<Solution> getNeighborhood(Solution solution) {
        return null;
    }

    public Solution next() {
          ArrayList<Job> s = new ArrayList<Job>();

        for (int i = 0; i < number_blocks; i++) {
            for (int z = 0; z < block_size; z++) {
//                System.out.println(i+"::"+z);
//                System.out.println(((i + 1) * block_size + z) % solution.jobsequence.size());
                s.add(solution.jobsequence.get(((i + 1)* block_size + z) % solution.jobsequence.size()));
            }
        }

        Ktns k = new Ktns(s,fixtures);
        return k.run();
    }

    public Solution random() {
        return next();
    }
}
