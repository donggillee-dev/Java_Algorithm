import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    static int answer = Integer.MAX_VALUE;
    static boolean[] Visited;
    static int[][] Map;
    public static void main(String[] args) {
        long ex = 0;
        int N = 1000000;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                ex++;
            }
        }
        System.out.println(ex);
//        int[][] route = {{1, 2, 5}, {1, 3, 2}, {1, 4, 5}, {1, 5, 7}, {2, 3, 6}, {2, 4, 2}, {2, 5, 6}, {3, 4, 7}, {3, 5, 1}, {4, 5, 6}};
//        int N = 5;
//        Map = new int[N][N];
//        Visited = new boolean[N];
//        int pos1 = 1, pos2 = 4;
//
//        int posA = pos1 - 1, posB = pos2 - 1;
//
//        for(int i = 0; i < route.length; i++) {
//            int A = route[i][0];
//            int B = route[i][1];
//            int length = route[i][2];
//            Map[A - 1][B - 1] = length;
//            Map[B - 1][A - 1] = length;
//        }
//        Visited[posA] = true;
//        Visited[posB] = true;
//        DFS(posA, posB, N, 0);
//        System.out.println(answer);
        return;
    }
    static void DFS(int posA, int posB, int N, int dist) {
        int cnt = 0;
        while(cnt < N) {
            if(!Visited[cnt]) break;
            cnt++;
        }
        if(cnt == N) {
            if(dist < answer) {
                answer = dist;
            }
            return;
        }

        for(int i = 0; i < N; i++) {
            if(i != posA && !Visited[i]) {
                Visited[i] = true;
                DFS(i, posB, N, dist + Map[posA][i]);
                Visited[i] = false;
            }
        }
        for(int j = N - 1; j >= 0; j--) {
            if(j != posB && !Visited[j]) {
                Visited[j] = true;
                DFS(posA, j, N, dist + Map[posB][j]);
                Visited[j] = false;
            }
        }

        return;
    }
    static public void deleteElement(Queue<Integer> queue, int num) {
        List<Integer> temp = new ArrayList<>();
        while(!queue.isEmpty()) {
            int extract = queue.poll();
            if(extract == num) break;
            temp.add(extract);
        }
        queue.addAll(temp);
    }
}