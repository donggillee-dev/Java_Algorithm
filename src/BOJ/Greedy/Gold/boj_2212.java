package BOJ.Greedy.Gold;

import java.io.*;
import java.util.*;

//Logic
//각 센서의 위치를 오름차순으로 정렬하고
//인접한 센서간의 거리차이를 구해서 오름차순으로 정렬한다
//k개의 기지국 범위 합(답)은 정렬된 거리배열의 가장 높은 값들 중에 k - 1개를 포함하지 않을 수 있다
//기지국이 묶는 센서 더미와 더미 사이를 k - 1번 뛰어넘을 수 있게 기지국을 배치할 수 있기 때문

public class boj_2212 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), k = Integer.parseInt(br.readLine());
        int ans = 0;
        int[] arr = new int[n];
        int[] diff = new int[n - 1];

        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = stoi(stk.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff);

        for(int i = 0; i < n - k; i++) {
            ans += diff[i];
        }

        System.out.print(ans);
    }
}
