package BOJ.Tree.Gold;

import java.io.*;
import java.util.StringTokenizer;
//Logic

//파티에 온 사람들의 집합관계 즉 Union-Find를 진행해줌
//Union-Find를 한 결과값으로 각 파티에 참석하는 사람들 번호를 탐색한다
//탐색 도중에 진실을 아는 사람과 연관이 있는 사람이 존재하는 파티이면 지민이는 해당 파티에 참가할 수 없음

public class boj_1043 {
    private static int[] party;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        party = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            party[i] = i;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int findParent(int x) {
        if (party[x] == x) { return x; }
        return party[x] = findParent(party[x]);
    }
    private static void unionFind(int node1, int node2) {
        int root1 = findParent(node1);
        int root2 = findParent(node2);

        party[root2] = root1;
    }
}
