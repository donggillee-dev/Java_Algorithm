package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;
//Logic
//머리가 굳어서 진짜 많이 헤멧던 문제... 굳이 헤멜 필요도 없었음....
//visited를 넣으면서 처리해줄게 아니고... 뽑으면서 처리해주면 됐음...
//풀이 시간 : 120분..........................

public class boj_12851 {
    public static void main(String[] args) throws IOException {
        int answer = Integer.MAX_VALUE;
        int cnt = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()), K = Integer.parseInt(stk.nextToken());

        if(K < N) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        Queue<int[]> Q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        Q.add(new int[]{N, 0});

        while(!Q.isEmpty()) {
            int[] elem = Q.poll();
            visited[elem[0]] = true;
            if(answer < elem[1]) continue;

            if(elem[0] == K) {
                if(answer > elem[1]) {
                    answer = elem[1];
                    cnt = 1;
                } else if(answer == elem[1]) cnt++;
            }
            if(elem[0] * 2 <= 100000 && !visited[elem[0] * 2]) {
                Q.add(new int[]{elem[0] * 2, elem[1] + 1});
            }

            if(elem[0] + 1 <= 100000 && !visited[elem[0] + 1]) {
                Q.add(new int[]{elem[0] + 1, elem[1] + 1});
            }
            if(elem[0] - 1 >= 0 && !visited[elem[0] - 1]) {
                Q.add(new int[]{elem[0] - 1, elem[1] + 1});
            }
        }

        System.out.println(answer);
        System.out.println(cnt);
    }
}
