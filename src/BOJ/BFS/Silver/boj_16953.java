package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//bfs로도 풀 수 있고 그리디로도 풀 수 있는 문제
//그리디로는 b의 값에 맨 뒤가 1이면은 10으로 나누고
//2로 나누어서 나머지가 0이면 2로 나누고 이렇게 가면 된다

//풀이 시간 : 15분

public class boj_16953 {
    private static long a, b;
    private static boolean flag = false;
    private static long ans = Long.MAX_VALUE;

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static void bfs(long cur, long depth) {

        if(cur > b) return;

        if(cur == b) {

            flag = true;
            ans = Math.min(ans, depth);

            return;
        }

        bfs(cur << 1, depth + 1);
        bfs((cur * 10) + 1, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        a = stoi(stk.nextToken());
        b = stoi(stk.nextToken());

        bfs(a, 0);

        if(flag) System.out.println(ans + 1);
        else System.out.println(-1);
    }
}

//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] line = br.readLine().split(" ");
//        int a = Integer.parseInt(line[0]);
//        int b = Integer.parseInt(line[1]);
//        System.out.println(find(a,b,0));
//    }
//    public static int find(int a, int b, int step) {
//        while(true) {
//            if(b==a) {
//                return step + 1;
//            }
//            if(b<a || (b%2==1 && b%10 >=3 && b%10 <= 9)) {
//                return -1;
//            }
//            if(b%10==1) {
//                b = b / 10;
//                step++;
//            }
//            else if(b%2==0) {
//                b = b / 2;
//                step++;
//            }
//        }
//    }
//}
