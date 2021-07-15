package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//단순히 BFS 문제이다
//문제를 좀 제대로 읽자 항상 느끼지만
//BFS, DFS 둘 다 큐나 스택에 넣어줄떄 방문 처리해야하는거 다시금 상기하게됨

//풀이 시간 : 18분

public class boj_16174 {
    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private static boolean bfs(int n, int[][] board) {
        boolean[][] visited = new boolean[n][n];
        Queue<Pos> q = new LinkedList<>();

        visited[0][0] = true;
        q.add(new Pos(0, 0));

        while(!q.isEmpty()) {
            Pos elem = q.poll();
            int dist = board[elem.row][elem.col];

            if(elem.row == n - 1 && elem.col == n - 1) return true;

            //아래 이동 가능
            if(elem.row + dist < n && !visited[elem.row + dist][elem.col]) {
                visited[elem.row + dist][elem.col] = true;
                q.add(new Pos(elem.row + dist, elem.col));
            }

            //오른쪽 이동 가능
            if(elem.col + dist < n && !visited[elem.row][elem.col + dist]) {
                visited[elem.row][elem.col + dist] = true;
                q.add(new Pos(elem.row, elem.col + dist));
            }

        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        if(bfs(n, board)) {
            System.out.println("HaruHaru");
        } else {
            System.out.println("Hing");
        }
    }
}
