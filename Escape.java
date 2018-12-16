import java.util.Objects;
import java.util.TreeSet;

//https://arena.topcoder.com/#/u/practiceCode/1185/1146/1170/1/1185

public class Escape {

    static int [][] d = new int[][]{{1,0,-1, 0}, {0,-1,0,1}};

    static public class Node implements Comparable<Node>{
        int x, y;
        int weight;

        public Node(int x, int y, int weight){
            this.x=x;
            this.y=y;
            this.weight=weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y &&
                    weight == node.weight;
        }

        @Override
        public int compareTo(Node e1) {
            int d= weight - e1.weight;
            if(d==0) d=x- e1.x;
            if(d==0) d=y-e1.y;
            return d;
        }
    }

    public static int lowest(String [] harm, String [] deadly) {
        int [][] mat = getMatrix(harm, deadly);
        int n= mat.length;
        int m= mat[0].length;
        Node[][] mat2 = new Node[mat.length][mat[0].length];
        TreeSet<Node> heap = new TreeSet<>();

        for(int i=0;i<mat2.length;i++){
            for(int j=0;j<mat2[i].length;j++){
               mat2[i][j]=new Node(i,j,Integer.MAX_VALUE);
            }
        }

        mat2[0][0].weight=0;
        heap.add(mat2[0][0]);

        int shPathLength = Integer.MAX_VALUE;

        while(!heap.isEmpty()) {
            Node e = heap.pollFirst();
            if(e.x==n-1 && e.y==m-1) {
                shPathLength = Math.min(e.weight, shPathLength);
                break;
            }
            fillEdges(e.x, e.y, heap, mat,mat2, e.weight);
        }

        return shPathLength==Integer.MAX_VALUE?-1:shPathLength;
    }

    static void fillEdges(int x, int y,
                          TreeSet<Node> heap, int[][] mat,Node[][]mat2, int curWeight) {
        int n =  mat.length;
        int m =  mat[0].length;
        for(int i=0; i < 4; i++) {
            int newx = x + d[0][i];
            int newy = y + d[1][i];

            if(checkValid(newx, newy, n, m)) {
                Node p = mat2[newx][newy];
                if(mat[newx][newy]!=-1) {
                    if(p.weight>curWeight + mat[newx][newy]) {
                        heap.remove(p);
                        p.weight = curWeight+mat[newx][newy];
                        heap.add(p);
                    }
                }
            }
        }
    }

    static int[][] getMatrix(String[] harm, String [] deadly) {
        int[][] mat = new int[501][501];

        for(String s : harm) {
            String [] coordinates = s.split("\\s+");
            int x1 = Integer.valueOf(coordinates[0]);
            int y1 = Integer.valueOf(coordinates[1]);
            int x2 = Integer.valueOf(coordinates[2]);
            int y2 = Integer.valueOf(coordinates[3]);

            for(int i=Math.min(y1, y2); i <=Math.max(y1, y2); i++) {
                for(int j=Math.min(x1, x2); j<=Math.max(x2,x1); j++) {
                    mat[i][j] = 1;
                }
            }
        }

        for(String s : deadly) {
            String [] coordinates = s.split("\\s+");
            int x1 = Integer.valueOf(coordinates[0]);
            int y1 = Integer.valueOf(coordinates[1]);
            int x2 = Integer.valueOf(coordinates[2]);
            int y2 = Integer.valueOf(coordinates[3]);

            for(int i=Math.min(y1, y2); i <=Math.max(y1, y2); i++) {
                for(int j=Math.min(x1, x2); j<=Math.max(x2,x1); j++) {
                    mat[i][j] = -1;
                }
            }
        }

        return mat;
    }

    static boolean checkValid(int x, int y, int n, int m) {
        return x>=0 && x<n && y>=0 && y<m;
    }
}
