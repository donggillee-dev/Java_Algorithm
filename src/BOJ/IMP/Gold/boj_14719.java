package BOJ.IMP.Gold;

import java.io.*;
import java.util.StringTokenizer;

//Logic
//현재 위치(1 ~ n-1)에서 왼쪽, 오른쪽 확인해서 가장 높은 왼쪽 오른쪽 구하고
//그 중 낮은거 높이 - 현재 높이가 채워지는 빗물 높이임

//풀이 시간 : 15분

public class boj_14719 {
    private static int h, w;
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static int[] input() throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        h = stoi(stk.nextToken());
        w = stoi(stk.nextToken());

        int[] arr = new int[w];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++) {
            arr[i] = stoi(stk.nextToken());
        }

        return arr;
    }
    private static int solution(int[] arr) {
        int ans = 0;

        for(int i = 1; i < w - 1; i++) {
            int left, right;
            left = right = arr[i];

            for(int j = i - 1; j >= 0; j--) {
                left = Math.max(left, arr[j]);
            }

            for(int j = i + 1; j < w; j++) {
                right = Math.max(right, arr[j]);
            }

            int leftDiff = left - arr[i];
            int rightDiff = right - arr[i];

            if(leftDiff < rightDiff) {
                ans += leftDiff;
            } else {
                ans += rightDiff;
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        int[] arr = input();

        System.out.print(solution(arr));
    }
}
