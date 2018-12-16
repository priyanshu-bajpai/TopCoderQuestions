import java.util.Stack;

//https://arena.topcoder.com/#/u/practiceCode/1240/1490/1593/1/1240
public class Circuits {

    public static int howLong(String[] connects, String[] costs) {
        int n = connects.length;
        int [][]adjMat = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0; j<n;j++) {
                adjMat[i][j]=-1;
            }
        }

        for(int i=0;i<n;i++) {
            if(connects[i].isEmpty())
                continue;
            String cons = connects[i];
            String[] adjEle = cons.split("\\s+");

            String costAdj = costs[i];
            String[] costEle = costAdj.split("\\s+");

            for(int j =0; j<adjEle.length;j++) {
                int idx = Integer.valueOf(adjEle[j]);
                int cost = Integer.valueOf(costEle[j]);
                adjMat[i][idx] = cost;
            }
        }

        Stack<Integer> stack = new Stack<>();
        boolean [] visited = new boolean [n];
        for(int i=0; i<n;i++) {
            if(!visited[i]) {
                topoSort(adjMat, stack, visited, i);
            }
        }

        int [] distance = new int[n];
        int globalMax = 0;
        while (!stack.isEmpty()) {
            int top = stack.pop();
            for(int i =0 ; i<n; i++) {
                if(adjMat[top][i]>=0) {
                    distance[i] = Math.max(distance[i], distance[top]+adjMat[top][i]);
                    globalMax = Math.max(globalMax, distance[i]);
                }
            }
        }

        return globalMax;
    }

    static void topoSort(int[][] adjMat, Stack<Integer> stack, boolean [] visited, int src) {
        int n = adjMat.length;
        visited[src] = true;
        for(int i =0 ; i<n; i++) {
            if(adjMat[src][i]>=0 && !visited[i]) {
                topoSort(adjMat, stack, visited, i);
            }
        }

        stack.push(src);
    }
}