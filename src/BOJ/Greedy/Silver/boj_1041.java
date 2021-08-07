package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic

//1면에 가장 작은거는 마주보는 것들중 가장 작은 애
//2면에 가장 작은거는 마주보는 것들 2개 합친것들 중 가장 작은거
//3면에 가장 작은거는 다 합친거

//5면이 보이니까
//3면이 보이는 곳은 4곳
//위의 조합중에 가장 작은 숫자
//2면이 보이는 곳은 (n - 2) * 8 + 4
// 위의 조합중에 가장 작은 숫자
//1면이 보이는 곳은 (n - 2) * (n - 2) * 5 + (n - 2) * 4
// A ~ F중 가장 작은 숫자

public class boj_1041 {

    private static long min1, min2, min3;

    private static void getMin(int[] arr) {
        long af = Math.min(arr[0], arr[5]);
        long be = Math.min(arr[1], arr[4]);
        long cd = Math.min(arr[2], arr[3]);

        min1 = Math.min(af, Math.min(be, cd));
        min2 = Math.min(af + be, Math.min(af + cd, be + cd));
        min3 = af + be + cd;
    }

    private static long getAns(long n, int[] arr) {
        long sum = 0;
        if (n == 1) {
            int max = -987654321;
            for (int elem : arr) {
                max = Math.max(elem, max);
                sum += elem;
            }

            return sum - max;
        }

        long one = 4 * (n - 1) * (n - 2) + (n - 2) * (n - 2);
        long two = (n - 2) * 8 + 4;
        long three = 4;

        sum = one * min1 + two * min2 + three * min3;

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        long n = Integer.parseInt(br.readLine());
        int[] arr = new int[6];

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        getMin(arr);

        System.out.println(getAns(n, arr));
    }
}
