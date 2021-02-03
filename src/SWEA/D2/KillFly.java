package SWEA.D2;
import java.util.*;

public class KillFly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int t = 1; t <= test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] a = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    a[i][j] = sc.nextInt();
                }
            }

            int max = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int sum = 0;
                    for(int k = 0; k < m; k++) {
                        for(int s = 0; s < m; s++) {
                            if(i+k >= n || j + s >= n)
                                continue;
                            sum += a[i + k][j + s];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
            System.out.println("#" + t + " " + max);
        }
    }
}
