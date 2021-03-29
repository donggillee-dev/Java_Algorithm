package BOJ.DP.Gold;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2629 {
    private static int N;
    private static int[] arr; //주어지는 추의 무게를 담을 배열입니다.
    private static boolean[][] DP; //올려본 추의 무게가 이미 구한 값인지 체크하기 위한 메모이제이션 배열입니다.

    private static void brute(int cnt, int weight) {//bruteForce를 이용하여 해결하였습니다
        if (cnt > N || DP[cnt][weight]) return;
        //주어지는 추의 개수는 30개이므로 모두 DFS 혹은 브루트포스로 돌면 무조건 시간초과가 날 것이라고 생각이 들어서
        //메모이제이션을 이용한 DP를 추가하였습니다.

        DP[cnt][weight] = true;
        brute(cnt + 1, weight + arr[cnt]); //왼쪽에 올려보는 경우
        brute(cnt + 1, Math.abs(weight - arr[cnt])); //오른쪽에 올려보는 경우에는 이미 왼쪽에 올라가져있는 값에서 빼주어야 측정할 수 있는 값이 나온다고 생각하였습니다.
        brute(cnt + 1, weight);//현재 cnt번쨰 추를 아예 올리지 않는 경우도 존재하기에 아무것도 올리지 않는 경우로 재귀를 돌아줍니다.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arr = new int[N + 1];

        for (int i = 0; i < N; i++) {//주어지는 추의 무게를 배열에 담습니다.
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        //메모이제이션 배열은 주어진 추로 원하는 값을 구할 수 있는지 없는지만 판별하면 되기 때문에 boolean으로 설정합니다.
        DP = new boolean[N + 1][40001];
        brute(0, 0);//구현해놓은 재귀함수를 돕니다.

        //측정하기 위한 구슬의 측정 여부를 판별하기 위한 Logic입니다.
        int cnt = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());

        //구슬의 무게를 하나씩 입력받아 DP배열의 해당 위치에 값이 true인지 false인지로 측정 가능 여부를 체크합니다.
        //또한 구슬의 무게가 추로 측정할 수 있는 범위를 벗어나면 무조건 측정 불가능이기에 이 경우도 체크해주었습니다.
        while (cnt-- > 0) {
            int num = Integer.parseInt(stk.nextToken());
            if(!DP[N][num])sb.append("N").append(" ");
            else sb.append("Y").append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}