import java.util.LinkedList;
import java.util.Queue;

//https://arena.topcoder.com/#/u/practiceCode/1358/2112/2274/1/1358

public class BombMan {

    static int [][] d = new int[][] {{1,0,-1,0}, {0,-1,0,1}};
    public static class State {
        int x;
        int y;
        int bombs;

        public State(int x, int y, int bombs) {
            this.bombs = bombs;
            this.x = x;
            this.y = y;
        }
    }

    public static int shortestPath(String[] maze, int bombs) {

        int sp[][][] = new int [51][51][101];
        boolean [][][] visited = new boolean [51][51][101];

        int srcx=0, srcy=0;
        int destx=0, desty=0;
        int n = maze.length;
        int m = maze[0].length();
        for(int i=0;i<maze.length;i++) {
            for(int j=0;j<maze[i].length();j++) {
                if(maze[i].charAt(j)=='B') {
                    srcx = i;
                    srcy = j;
                }
                else if(maze[i].charAt(j)=='E') {
                    destx = i;
                    desty = j;
                }
            }
        }

        for(int i=0;i<51;i++) {
            for(int j=0;j<51;j++) {
                for(int bomb =0 ;bomb < 101; bomb++) {
                    sp[i][j][bomb]=Integer.MAX_VALUE;
                }
            }
        }

        sp[srcx][srcy][bombs] = 0;

        Queue<State> q = new LinkedList<>();
        q.add(new State(srcx, srcy, bombs));

        while(!q.isEmpty()) {
            State s = q.poll();

            int x = s.x;
            int y = s.y;
            int bomb = s.bombs;
            visited[x][y][bomb] = true;

            for(int i=0;i<4;i++) {
                int newx = s.x + d[0][i];
                int newy = s.y + d[1][i];
                if(isValid(newx, newy, n, m)) {
                    boolean hasWall = maze[newx].charAt(newy)=='#';
                    int newBomb = s.bombs +  (hasWall ? -1 : 0);
                    if (newBomb>=0 && !visited[newx][newy][newBomb]) {
                        sp[newx][newy][newBomb] = Math.min(sp[newx][newy][newBomb], sp[x][y][bomb] + (hasWall ? 3 : 1));
                        visited[newx][newy][newBomb] = true;
                        q.add(new State(newx, newy, newBomb));
                    }
                }

            }

        }

        int minDist = Integer.MAX_VALUE;
        for(int i=0;i<101;i++) {
            minDist = Math.min(minDist, sp[destx][desty][i]);
        }

        return minDist==Integer.MAX_VALUE ? -1 : minDist;
    }

    static boolean isValid (int x, int y, int n, int m) {
        return x>=0 && x<n&& y>=0 && y<m;
    }
}
