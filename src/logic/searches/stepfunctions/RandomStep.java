package logic.searches.stepfunctions;

import data.Solution;
import logic.searches.neighborhoods.INeighborhood;

public class RandomStep implements IStepFunction {
    public Solution run(Solution solution, INeighborhood n) {
//        int selJobSq = new Random().nextInt(jobsequences.size());
//        return new Solution(jobsequences.get(selJobSq));
        n.start(solution);
        return n.next();
    }

    public boolean breakup() {
        return false;
    }
}
