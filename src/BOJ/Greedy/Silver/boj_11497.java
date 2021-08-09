package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//가장 차이가 적게끔 만드는 방법은 최대한 평평하게 만드는 것
//가장 작은 애들부터 양옆에 세워서 가운데에는 가장 높은애가 가게끔
//이러면 양 끝의 차이도 적어짐

//풀이 시간 : 1시간

public class boj_11497 {
    private static int[] input(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        return arr;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T --> 0) {
            int[] arr = input(br);
            int size = arr.length;

            int[] sortArr = new int[size];

            Arrays.sort(arr);
            int i = 0;
            int left = 0, right = size - 1;
            while(left <= right) {
                sortArr[left++] = arr[i++];
                if(i >= size) break;
                sortArr[right--] = arr[i++];
            }

            int[] diff = new int[size];
            for(i = 0; i < size - 1; i++) {
                diff[i] = Math.abs(sortArr[i + 1] - sortArr[i]);
            }
            diff[size - 1] = Math.abs(sortArr[size - 1] - sortArr[0]);

            Arrays.sort(diff);

            int ans = -987654321;
            for(int elem : diff) {
                ans = Math.max(ans, elem);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
