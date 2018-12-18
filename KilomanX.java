//https://arena.topcoder.com/?fbclid=IwAR1bb1ZyQYdb9AbwQecaqvMwPsiIO27bVf8paHmIld8FtI33meb1Za13e8o#/u/practiceCode/1336/2125/2288/1/1336
public class KilomanX {

    public static int leastShots(String [] damageChart, int[] bossHealth) {
        int bosses = bossHealth.length;
        int n = bosses;

        int [][] weapons = new int[bosses][bosses];
        for(int i=0;i<bosses;i++) {
            for (int j = 0; j < n; j++) {
                weapons[i][j] = damageChart[i].charAt(j) - '0';
                System.out.print(weapons[i][j] + " ");
            }
        }

        int numState = (int)Math.round(Math.pow(2, bosses));

        int state[] = new int[numState];
        for(int i=0; i<numState;i++) {
            state[i] = Integer.MAX_VALUE;
        }
        state[0] = 0;

        for(int i=1;i<numState;i++) {
            for(int j=0;j<bosses;j++) {
                if(((1<<j) & i) > 0) {
                    int prevState = ~(1<<j) & i;
                    int maxDamage = 1;
                    for(int k=0;k<bosses;k++) {
                        if(((1<<k) & prevState) > 0) {
                            maxDamage = Math.max(weapons[k][j], maxDamage);
                        }
                    }

                    state[i] = Math.min(state[i],
                            state[prevState] + (int)Math.ceil((double)bossHealth[j]/(double)maxDamage));

                }
            }
        }

        return state[numState-1];

    }
}
