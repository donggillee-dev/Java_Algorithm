package BOJ.UnionFind.Gold;

import java.io.*;
import java.util.*;
//Logic

//파티에 온 사람들의 집합관계 즉 Union-Find를 진행해줌
//Union-Find를 한 결과값으로 각 파티에 참석하는 사람들 번호를 탐색한다
//탐색 도중에 진실을 아는 사람과 연관이 있는 사람이 존재하는 파티이면 지민이는 해당 파티에 참가할 수 없음
//풀이 시간 : 80분
public class boj_1043 {
    private static int[] party;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        int personNum = Integer.parseInt(stk.nextToken());
        int answer = M;

        //진실을 알고있는 사람이 0명이면 모든 파티에 다 참가할 수 있으니까 바로 리턴
        if(personNum == 0) {
            sb.append(answer);
            bw.write(sb.toString());
            bw.close();
            br.close();
            return;
        }


        int[] knownList = new int[personNum + 1];
        ArrayList<Integer>[] partyList = new ArrayList[M + 1];
        party = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            party[i] = i;
        }

        for(int i = 1; i <= personNum; i++) {
            knownList[i] = Integer.parseInt(stk.nextToken());
        }
        //데이터 세팅 및 Union-Find 부분
        for(int i = 1; i <= M; i++) {
            partyList[i] = new ArrayList<>();
            stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken());
            for(int j = 0; j < num; j++) {
                partyList[i].add(Integer.parseInt(stk.nextToken()));
            }
            for(int j = 1; j < num; j++) {
                unionFind(partyList[i].get(0), partyList[i].get(j));
            }
        }
        //findparent를 하면서 진실을 아는 사람과 접점이 있는 파티인지 아닌지 체크
        for(int i = 1; i <= M; i++) {
            loop:for(int j = 0; j < partyList[i].size(); j++) {
                int node1 = findParent(partyList[i].get(j));
                for(int k = 1; k <= personNum; k++) {
                    int node2 = findParent(knownList[k]);
                    if(node1 == node2) {
                        answer--;
                        break loop;
                    }
                }
            }
        }

        sb.append(answer);
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
