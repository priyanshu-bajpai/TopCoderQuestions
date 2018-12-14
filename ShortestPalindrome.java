public class ShortestPalindrome {
    static String longString = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
    static class Palindrome {
        String pal=longString;
        int length=Integer.MAX_VALUE;
        public Palindrome(int length, String pal) {
            this.length = length;
            this.pal = pal;
        }
        public Palindrome() {}
    }

    static String shortestPalindrome(String base) {
        int n = base.length();
        Palindrome [][] dp = new Palindrome[n][n];

        for(int i =0; i < n; i++) {
            for(int j=i; j < n; j++) {
                if(i==j) {
                    dp[i][i] = new Palindrome(1, base.substring(i,i+1));
                }
                else {
                    dp[i][j] = new Palindrome();
                }
            }
        }

        for(int l=2; l<=n; l++) {
            for(int i=0; i<=n-l; i++) {
                int j = i+l-1;
                String stri = base.substring(i, i+1);
                String strj = base.substring(j, j+1);

                if(base.charAt(i)==base.charAt(j)) {
                    dp[i][j].length = 2 + (j-i>2 ? dp[i+1][j-1].length : 0);
                    dp[i][j].pal =  stri + (j-i>2 ? dp[i+1][j-1].pal : "") + strj;
                }

                String minPal = dp[i][j].pal;
                int minLength = dp[i][j].length;

                String pali = stri + dp[i+1][j].pal + stri;
                int lengthi = dp[i+1][j].length + 2;

                String palj = strj + dp[i][j-1].pal + strj;
                int lengthj = dp[i][j-1].length + 2;

                if (lengthi <= minLength) {
                    if(lengthi==minLength) {
                        minPal = minPal.compareTo(pali) < 0 ? minPal : pali;
                    }
                    else {
                        minLength = lengthi;
                        minPal = pali;
                    }
                }

                if(lengthj <= minLength) {
                    if(lengthj==minLength) {
                        minPal = minPal.compareTo(palj) < 0 ? minPal : palj;
                    }
                    else {
                        minLength = lengthj;
                        minPal = palj;
                    }
                }

                dp[i][j].pal = minPal;
                dp[i][j].length = minLength;
            }
        }

        return dp[0][n-1].pal;
    }
}
