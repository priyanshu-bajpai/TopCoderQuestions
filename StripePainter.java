public class StripePainter {

    public static void main (String args[]) {
        System.out.println(minStrokes("RGBGR"));
        System.out.println(minStrokes("ABACADA"));
        System.out.println(minStrokes("AABBCCDDCCBBAABBCCDD"));
        System.out.println(minStrokes(
                "BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD"));

    }

    static int minStrokes(String stripes) {
        int n = stripes.length();
        int dp[][] = new int [n][n];

        for (int i=0; i < n;i++) {
            dp[i][i] = 1;
        }

        for(int j=1; j <n; j++) {
            for(int i=j; i>=0;i--) {
                int min = 1+dp[i][j-1];
                for (int k = i; k<j;k++) {
                    if(stripes.charAt(k)==stripes.charAt(j)) {
                        min = Math.min(min, dp[i][k]+dp[k+1][j-1]);
                    }
                }
                dp[i][j] = min;
            }
        }

        return dp[0][n-1];
    }
}
