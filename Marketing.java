import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://community.topcoder.com/stat?c=problem_statement&pm=1524&rd=4472

public class Marketing {

    public static long howMany(String[] input) {
        ArrayList<ArrayList<Integer>> adjList  = new ArrayList<>();

        int inputSize = input.length;
        for(int i=0; i<inputSize ;i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<inputSize ;i++) {
            if(input[i].isEmpty())
                continue;
            String [] adjElement = input[i].split("\\s+");
            for (String idxstr : adjElement) {
                int n = Integer.valueOf(idxstr);
                adjList.get(i).add(n);
                adjList.get(n).add(i);
            }
        }

        int [] assignment = new int[inputSize];

        for(int i =0; i < adjList.size(); i++) {
            if(assignment[i]==0) {
                assignment[i] = 1;
                for (int adj:adjList.get(i)) {
                    assignment[adj]=2;
                }
            }
            else if (assignment[i]==1) {
                for (int adj:adjList.get(i)) {
                    if (assignment[adj]==1) {
                        return -1;
                    }
                }
            }
            else if (assignment[i]==2) {
                for (int adj:adjList.get(i)) {
                    if (assignment[adj]==2) {
                        return -1;
                    }
                }
            }
        }

        int numConnectedComponents = 0;
        boolean []visited = new boolean[inputSize];

        for (int i =0; i<inputSize; i++) {
            if(!visited[i]) {
                numConnectedComponents++;
                performBFS(i, adjList, visited);
            }
        }

        return Math.round(Math.pow(2, numConnectedComponents));
    }

    static void performBFS(int start, ArrayList<ArrayList<Integer>> adjList, boolean [] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int front = q.poll();
            visited[front]= true;
            for (int adjEle : adjList.get(front)) {
                if (!visited[adjEle]) {
                    q.add(adjEle);
                }
            }
        }
    }

}
