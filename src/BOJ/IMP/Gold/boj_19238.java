package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//bfs + 시뮬레이션 문제
//처음에 생각했던 조건대로 시뮬레이션 내의 조건 처리를 해야했는데
    //사람의 경우 0 보다 큰수
    //벽의 경우 -1
    //그 외의 경우 0
//얘네를 생각 안해주고 구현해서 많이 틀린 문제
//좀 더 꼼꼼히 생각할 것

//풀이 시간 : 45분

public class boj_19238 {
    private static int n, m, fuel;
    private static int[] dir_r = {-1, 0, 1, 0};
    private static int[] dir_c = {0, -1, 0, 1};
    private static ArrayList<Person> personList = new ArrayList<>();

    private static class Person {
        int sRow, sCol, eCol, eRow;

        public Person(int sRow, int sCol, int eRow, int eCol) {
            this.sRow = sRow;
            this.sCol = sCol;
            this.eRow = eRow;
            this.eCol = eCol;
        }
    }

    private static class Pos {
        int row, col, dist;

        public Pos(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static boolean isPossible(int row, int col, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= n || col >= n || visited[row][col]) return false;

        return true;
    }

    private static int findPerson(int sRow, int sCol, int[][] arr) { //조건에 맞는 손님을 찾는 과정
        int personIdx = 0;
        int personRow = 987654321, personCol = 987654321, personDist = 987654321;
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        q.add(new Pos(sRow, sCol, 0));
        visited[sRow][sCol] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (p.dist > personDist) continue;

            if (arr[p.row][p.col] > 0) { //해당 위치에 사람이 존재하는 경우
                if (personDist > p.dist) {
                    personDist = p.dist;
                    personRow = p.row;
                    personCol = p.col;
                } else if (personDist == p.dist) {
                    if (personRow > p.row) {
                        personRow = p.row;
                        personCol = p.col;
                    } else if (personRow  == p.row) {
                        if (personCol > p.col) {
                            personCol = p.col;
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.row + dir_r[i];
                int nc = p.col + dir_c[i];

                if (isPossible(nr, nc, visited) && arr[nr][nc] >= 0) {
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc, p.dist + 1));
                }
            }
        }

        if(personDist == 987654321) return -1;

        personIdx = arr[personRow][personCol] - 1;
        arr[personRow][personCol] = 0;

        if (personDist > fuel) return -1; //승객을 태우러가는데 연료 다 떨어지면 -1리턴

        fuel -= personDist;

        return personIdx;
    }

    private static int drive(int sRow, int sCol, int eRow, int eCol, int[][] arr) {
        int dist = 987654321;
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        q.add(new Pos(sRow, sCol, 0));
        visited[sRow][sCol] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            if (p.row == eRow && p.col == eCol) {
                dist = Math.min(dist, p.dist);
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.row + dir_r[i];
                int nc = p.col + dir_c[i];

                if (isPossible(nr, nc, visited) && arr[nr][nc] >= 0) {
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc, p.dist + 1));
                }
            }
        }

        if (dist > fuel) return -1; //승객 목적지로 데려다주는데 연료 다 떨어지면 불가능, 또는 해당 위치까지 경로가 없을 경우에

        return dist;
    }

    private static boolean simulation(int sRow, int sCol, int[][] arr) {
        for (int i = 0; i < m; i++) {
            int personIdx = findPerson(sRow, sCol, arr);

            if (personIdx == -1) return false;

            Person p = personList.get(personIdx);
            int dist = drive(p.sRow, p.sCol, p.eRow, p.eCol, arr);

            if (dist == -1) return false;

            fuel += dist;
            sRow = p.eRow;
            sCol = p.eCol;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        //n, m, 연료 입력
        n = stoi(stk.nextToken());
        m = stoi(stk.nextToken());
        fuel = stoi(stk.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = stoi(stk.nextToken());
                if (arr[i][j] == 1) arr[i][j] = -1; //벽을 -1로 세팅
            }
        }

        stk = new StringTokenizer(br.readLine());

        //택시의 시작 행, 열 입력
        int sRow = stoi(stk.nextToken()) - 1;
        int sCol = stoi(stk.nextToken()) - 1;

        //각 사람의 정보를 입력
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int sR = stoi(stk.nextToken()) - 1;
            int sC = stoi(stk.nextToken()) - 1;

            arr[sR][sC] = (i + 1);

            personList.add(new Person(
                    sR, sC,
                    stoi(stk.nextToken()) - 1,
                    stoi(stk.nextToken()) - 1
            ));
        }

        if (!simulation(sRow, sCol, arr)) System.out.println(-1);
        else System.out.println(fuel);
    }
}
