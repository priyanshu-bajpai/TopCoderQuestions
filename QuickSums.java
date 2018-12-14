public class QuickSums {
/*
    public static void main (String args[]) {
        System.out.println(minSums("99", 99));
    }*/

    public static int minSums(String numbers, int sum) {
        int n = numbers.length();
        int m = sum+1;

        int dp[][] = new int [n][m];

        for(int digits=0; digits<n; digits++) {
            for(int sumVal=0; sumVal<m; sumVal++) {
                dp[digits][sumVal] = Integer.MAX_VALUE;
            }
        }

        for (int sumVal=0; sumVal<m; sumVal++) {
            dp[0][sumVal] = getNumber(numbers, 0, 0) == sumVal ?
                    0 : Integer.MAX_VALUE;
        }

        for (int digit = 1; digit < n; digit++) {
              for(int sumVal=0; sumVal<m; sumVal++) {
                  int minSum = getNumber(numbers, 0, digit)
                          == sumVal ? 0 : Integer.MAX_VALUE;
                  for(int i=digit; i>=1 && (getNumber(numbers, i, digit)<=sumVal); i--) {
                      if(dp[i-1][sumVal-getNumber(numbers, i, digit)] != Integer.MAX_VALUE) {
                          minSum = Math.min(minSum,
                                  1 + dp[i-1][sumVal-getNumber(numbers,i,digit)]);
                      }
                  }
                  dp[digit][sumVal] = minSum;
              }
        }

        return dp[n-1][sum] < Integer.MAX_VALUE ? dp[n-1][sum] : -1;
    }

    public static int getNumber(String number, int startIdx, int endIdx) {
        int shiftLeft=10;
        int num = number.charAt(startIdx) - '0';

        for(int idx=startIdx+1;idx<=endIdx;idx++) {
            int digit = number.charAt(idx)-'0';
            num*=shiftLeft;
            num+=digit;
        }
        return num;
    }
}
