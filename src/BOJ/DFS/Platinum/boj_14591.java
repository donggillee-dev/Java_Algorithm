package BOJ.DFS.Platinum;
import java.io.*;
import java.util.*;

public class boj_14591 {
    private static int length = -1, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] map = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];
        ArrayList<Integer> answer = new ArrayList<>();
        StringTokenizer stk;

        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
            stk = new StringTokenizer(br.readLine());
            for(int j = 1, num = 0; j <= N; j++) {
                num = Integer.parseInt(stk.nextToken());
                if(num == 1) map[i].add(j);
            }
        }
        visited[1] = true;
        DFS(map, visited, answer, 1, 1, sb);

        sb.append(answer.size()).append("\n");
        for(int i = answer.size() - 1; i >= 0; i--) {
            sb.append(answer.get(i)).append(" ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void DFS(ArrayList<Integer>[] map, boolean[] visited, ArrayList<Integer> answer, int p, int depth, StringBuilder sb) {
        for(int i = 0; i < map[p].size(); i++) {
            int next = map[p].get(i);
            if(!visited[next]) {
                visited[next] = true;
                DFS(map, visited, answer, next, depth + 1, sb);
            }
        }
        answer.add(p);
    }
}

