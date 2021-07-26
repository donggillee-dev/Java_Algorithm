package BOJ.FloydWarshall.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//이전에 DFS로 풀었던 방식을 와샬로 풀어봄
//구슬의 상하관계를 이용해서 a -> b, b -> c 동일하면 a -> c도 연결
//이렇게 다 연결해주고 모든 연관관계를 카운팅 해줘서 구슬 무게 올라가는거 중간값, 내려가는거 중간값을 넘어가는 애들에 대해서는 중간값 절대 못되니까 정답 도출

//풀이 시간 : 40분

public class boj_2617 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[][] arr = new int[n + 1][n + 1];

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            arr[a][b] = -1; // a -> b 내림 무게
            arr[b][a] = 1; // b -> a 오름 무게
        }

        for(int i = 1; i <= n; i++) { //내림이 가능? , 오름이 가능? => 그렇다면 중간거친애 제외하고도 오름 내림 가능
            for(int j = 1; j <= n; j++) {
                for(int k = 1; k <= n; k++) {
                    if(arr[j][i] != 0 && arr[j][i] == arr[i][k]) {
                        arr[j][k] = arr[j][i];
                    }
                }
            }
        }

        int half = (n / 2) + 1;
        int ans = 0;

        for(int i = 1; i <= n; i++) {
            int descCnt = 0, ascCnt = 0;
            for(int j = 1; j <= n; j++) {
                if(arr[i][j] == -1) descCnt++;
                if(arr[i][j] == 1) ascCnt++;
            }

            if(descCnt >= half || ascCnt >= half) ans++;
        }

        System.out.println(ans);
    }
}
