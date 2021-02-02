package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_14890 {
    static int N, L, ans = 0;
    static int[][] Map;
    static boolean[][] Built;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        Map = new int[N][N];
        Built = new boolean[N][N];//이미 지어진 땅인지 확인하기 위한 배열
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {//Row에 대해서 검증 절차함수
            RowPossible(i);
        }
        for(int i = 0; i < N; i++) {//Column에 대해서 검증 절차함수
            ColumnPossible(i);
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void RowPossible(int Row) {
        boolean Possible = true;
        int length = 1;
        int height = Map[Row][0];//Row의 첫번쨰 원소 -> 비교대상

        loop1:for(int j = 1; j < N; j++) {
            if(Math.abs(Map[Row][j] - height) > 1) {//높이 차이가 2 이상이면 불가능
                Possible = false;
                break loop1;
            }

            if(Map[Row][j] > height) {//올라가는 경사
                if(length < L) {//경사로의 밑면 길이에 비해 설치할 수 있는 공간의 길이가 작으면 불가능
                    Possible = false;
                    break loop1;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {//이미 지어진 경우인지 확인
                    if(Built[Row][idx]) {
                        Possible = false;
                        break loop1;
                    }
                    idx--;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {//경사로 설치
                    Built[Row][idx] = true;
                    idx--;
                }
                height = Map[Row][j];//다음 높낮이 비교를 위해 값 갱신
                length = 1;
            } else if(Map[Row][j] < height) {//내려가는 경사
                height = Map[Row][j];//내려가는 곳이니까 미래에 대해서 설치하는 것이므로 내려온 높이에 대해서 경사로 길이만큼 비교해서 설치해줘야함
                for(int k = 1, idx = j + k; k < L && idx < N; k++) {//경사로 길이 만큼
                    if(height != Map[Row][idx] || Built[Row][idx]) {//설치 됐었는지 검증 && 높이가 같은지 검증
                        Possible = false;
                        break loop1;
                    }
                    idx++;
                }
                for(int k = 0, idx = j + k; idx < N && k < L; k++) {//경사로 설치
                    Built[Row][idx] = true;
                    idx++;
                }
                j += (L - 1);//다음을 위해 경사로 길이 - 1만큼 현재 탐색 idx값 갱신
                if(j >= N) {//경사로가 배열 크기보다 넘어섰으면 불가능한 부분
                    Possible = false;
                } else {
                    height = Map[Row][j];
                    length = 1;
                }
            } else {//똑같은 높이인 경우
                length++;
            }
        }
        if(Possible) {
//            System.out.println(Row);
            ans++;
        }
        Init_Built();//다음 행에 검증 절차를 위해 배열 초기화
    }
    private static void ColumnPossible(int Column) {//Row와 동일한 매커니즘이다.
        boolean Possible = true;
        int length = 1;
        int height = Map[0][Column];

        loop1:for(int j = 1; j < N; j++) {
            if(Math.abs(Map[j][Column] - height) > 1) {
                Possible = false;
                break loop1;
            }

            if(Map[j][Column] > height) {//올라가는 경사
                if(length < L) {
                    Possible = false;
                    break loop1;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    if(Built[idx][Column]) {
                        Possible = false;
                        break loop1;
                    }
                    idx--;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    Built[idx][Column] = true;
                    idx--;
                }
                height = Map[j][Column];
                length = 1;
            } else if(Map[j][Column] < height) {//내려가는 경사
                height = Map[j][Column];
                for(int k = 1, idx = j + k; k < L && idx < N; k++) {
                    if(height != Map[idx][Column] || Built[idx][Column]) {
                        Possible = false;
                        break loop1;
                    }
                    idx++;
                }
                for(int k = 0, idx = j + k; idx < N && k < L; k++) {
                    Built[idx][Column] = true;
                    idx++;
                }
                j += (L - 1);
                if(j >= N) {
                    Possible = false;
                } else {
                    height = Map[j][Column];
                    length = 1;
                }
            } else {//똑같은 높이인 경우
                length++;
            }
        }
        if(Possible) {
//            System.out.println(Column);
            ans++;
        }
        Init_Built();
    }
    private static void Init_Built() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(Built[i], false);
        }
    }
}