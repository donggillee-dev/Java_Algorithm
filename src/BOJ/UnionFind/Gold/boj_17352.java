package BOJ.UnionFind.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//n - 2개만큼을 가지고 unionFind를 해준 다음에
//연결된 애들을 findParent해서 두 노드의 부모가 다른지 체크 => n - 2개가 다 연결되어있기때문에 인접 노드끼리만 검증하면 된다
//풀이 시간 : 20분

public class boj_17352 {
    private static int n;
    private static void initParent(int[] parents) {
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }
    private static int findParent(int x, int[] parents) {
        if(x == parents[x]) return x;
        else return parents[x] = findParent(parents[x], parents);
    }
    private static boolean unionFind(int a, int b, int[] parents) {
        a = findParent(a, parents);
        b = findParent(b, parents);

        if(a == b)
            return true;

        if(a < b) parents[b] = a;
        else parents[a] = b;

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        int[] parents = new int[n + 1];

        initParent(parents);

        for(int i = 0; i < n - 2; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            unionFind(a, b, parents);
        }

        for(int i = 1; i < n; i++) {
            if(!unionFind(i, i + 1, parents)) {
                System.out.println(i + " " + (i + 1));
                break;
            }
        }
    }
}
