import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long getWays(int n, int[] c){
        // Complete this function
        int size=c.length;
        long ways[][]=new long[n+1][size];
        // as to have a total sum of 0 from the denominationsis only 1
        for(int i=0;i<size;i++){
            ways[0][i]=1;
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<size;j++){
                //if we include the current denomination we have to next look for the ways to make sum = i-curent denomination
                long x=(i-c[j]>=0)?ways[i-c[j]][j]:0;
                //if we exclude the current denomination the ways is nothing but the total ways till the last denomination used
                long y=(j>=1)?ways[i][j-1]:0;
                ways[i][j]=x+y;
            }
        }
         /*for(int i=0; i<m; i++)
        for(int j=S[i]; j<=n; j++)
            table[j] += table[j-S[i]];*/ // alternate way
        return ways[n][size-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] c = new int[m];
        for(int c_i=0; c_i < m; c_i++){
            c[c_i] = in.nextInt();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(n, c);
        System.out.println(ways);
    }
}
