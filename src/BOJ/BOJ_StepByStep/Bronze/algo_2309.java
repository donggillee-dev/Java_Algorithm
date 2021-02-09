package BOJ.BOJ_StepByStep.Bronze;

import java.io.*;
import java.util.PriorityQueue;

public class algo_2309 {
    private static int[] tallArr = new int[9];
    private static boolean[] visited = new boolean[9];
    private static PriorityQueue<Integer> Q = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < tallArr.length; i++) {
            tallArr[i] = Integer.parseInt(br.readLine());
        }

        DFS(0, 0);

        while(!Q.isEmpty()) {
            sb.append(Q.poll()).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static boolean DFS(int depth, int total) {
        if(depth == 7 && total == 100) {
            for(int i = 0; i < visited.length; i++) {
                if (visited[i])
                    Q.offer(tallArr[i]);
            }
            return true;
        } else if(depth > 7) return false;

        for(int i = depth; i < tallArr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(DFS(depth + 1, total + tallArr[i])) return true;
                visited[i] = false;
            }
        }
        return false;
    }

}
