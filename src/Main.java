import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int min = Integer.MAX_VALUE;
    static boolean [][] visit;  //이미 방문한 정점의 정보를 담을 배열
    static int [][] map;
    static Stack<Point> S;

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][K];
        visit = new boolean[N][K];
        for(int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < K; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
        dfsSol();
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void dfsSol() {
        S = new Stack<Point>();
        S.add(new Point(0, 0, 1, 0));
        visit[0][0] = true;

        while(!S.empty()) {
            Point p = S.pop();
            int x = p.getX();
            int y = p.getY();
            int d = p.getDepth();
            int metWall = p.getTimeMetWall();
            int flag = 0;
            int poss_path = 0;

            if(x == N - 1 && y == K - 1) {
                if(d < min) {
                    min = d;
                    if(sb.length() > 0) {
                        sb.delete(0, sb.length() - 1);
                        sb.append(d).append("\n");
                    }
                }
            }
            if(x != 0 && !visit[x - 1][y]) {
                poss_path++;
                if(map[x-1][y] == 1) {
                    if(metWall == 0) {
                        S.push(new Point(x - 1, y, d + 1, metWall + 1));
                        visit[x - 1][y] = true;
                    }
                    else flag++;
                } else {
                    S.push(new Point(x - 1, y, d + 1, metWall));
                    visit[x - 1][y] = true;
                }
            }
            if(y != 0 && !visit[x][y - 1]) {
                poss_path++;
                if(map[x][y - 1] == 1) {
                    if(metWall == 0) {
                        S.push(new Point(x, y - 1, d + 1, metWall + 1));
                        visit[x][y - 1] = true;
                    }
                    else flag++;
                } else {
                    S.push(new Point(x, y - 1, d + 1, metWall));
                    visit[x][y - 1] = true;
                }
            }
            if(x < N - 1 && !visit[x + 1][y]) {
                poss_path++;
                if(map[x + 1][y] == 1) {
                    if(metWall == 0) {
                        S.push(new Point(x + 1, y, d + 1, metWall + 1));
                        visit[x + 1][y] = true;
                    }
                    else flag++;
                } else {
                    S.push(new Point(x + 1, y, d + 1, metWall));
                    visit[x + 1][y] = true;
                }
            }
            if(y < K - 1 && !visit[x][y + 1]) {
                poss_path++;
                if(map[x][y + 1] == 1) {
                    if(metWall == 0) {
                        S.push(new Point(x, y + 1, d + 1, metWall + 1));
                        visit[x][y + 1] = true;
                    }
                    else flag++;
                } else {
                    S.push(new Point(x, y + 1, d + 1, metWall));
                    visit[x][y + 1] = true;
                }
            }

            if(poss_path == flag) visit[x][y] = false;
            if(S.empty()) {
                if(x != N - 1 && y != K - 1 && min == Integer.MAX_VALUE) sb.append(-1).append("\n");
            }
        }
    }
}
class Point {
    int x, y, depth, metWall;

    public Point(int x1, int y1, int d, int w) {
        x = x1;
        y = y1;
        depth = d;
        metWall = w;
    }
    public int getTimeMetWall() {
        return metWall;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDepth() {
        return depth;
    }
}
