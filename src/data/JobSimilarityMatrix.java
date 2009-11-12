package data;

import java.util.ArrayList;

public class JobSimilarityMatrix {
    private ArrayList<Job> jobs;
    private Integer[][] similarity;

    public JobSimilarityMatrix(ArrayList<Job> jobs) {
        this.jobs = jobs;
        this.similarity = new Integer[jobs.size()][jobs.size()];
        calculate();
    }

    private void calculate() {
        for (Job first : jobs) {
            for (Job second : jobs) {
                if (second == first) {
                    similarity[first.id][first.id] = first.getTools().size();
                } else {
                    similarity[first.id][second.id] = calculate_same_tools(first, second);
                }
            }
        }
    }

    private Integer calculate_same_tools(Job first, Job second) {
        Integer i = 0;

        for (Tool f : first.getTools()) {
            for (Tool s : second.getTools()) {
                if (s == f)
                    i++;
            }
        }


        return i;
    }

    // id has to start from 0 and go up to the max in one steps
    public Integer[] get(int id) {
        return similarity[id];
    }

    public Integer similarity_between(int from, int to) {
        return similarity[from][to];
    }
}
