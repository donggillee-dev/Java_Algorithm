package BOJ.UnionFind.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//적인 애들과 적이 아닌 애들에 대해 각각 분리 집합으로 만들면 되는 문제
//각 번호에 대해 적을 나타내는 enemy 배열을 가지고
    //a의 적에 대해서는 b와 유니온파인드 해주고
    //b의 적에 대해서는 a와 유니온파인드 해준다

//다만 a와 b의 조상이 같게 나오면 둘이 적대관계 이론이 맞지 않으므로 바로 0 출력

//풀이 시간 : 엄청 오래 걸림

public class boj_12893 {
    private static int n;

    private static void initParent(int[] parent, int[] enemy) {
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            enemy[i] = 0;
        }
    }

    private static int findParent(int x, int[] parent) {
        if (x == parent[x]) return x;
        else return parent[x] = findParent(parent[x], parent);
    }

    private static void unionFind(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);

        if(a != b)
            parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());

        int m = Integer.parseInt(stk.nextToken());
        int[] parent = new int[n + 1];
        int[] enemy = new int[n + 1];

        initParent(parent, enemy);

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            //두 노드가 같은 적, 적 관계 or 친구, 친구 관계인지 확인
            a = findParent(a, parent);
            b = findParent(b, parent);

            if(a == b) { //친구와 친구 or 적과 적이 들어와지면 바로 0 출력
                System.out.println(0);
                System.exit(0);
            }

            if(enemy[a] == 0) { //a의 적이 초기화 상태?
                //a의 적은 b
                enemy[a] = b;
            } else {
                //초기화된 상태가 아니라면 a의 적과 b는 친구 관계이므로 연결
                unionFind(enemy[a], b, parent);
            }

            if(enemy[b] == 0) { //b의 적이 초기화 상태?
                //b의 적은 a로 초기화
                enemy[b] = a;
            } else {
                //그렇지 않다면 b의 적과 a를 연결해준다.
                unionFind(enemy[b], a, parent);
            }

        }
        System.out.println(1);
    }
}