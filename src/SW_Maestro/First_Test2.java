package SW_Maestro;

import java.io.*;
import java.util.*;

public class First_Test2 {
    static int[][] ans;
    static int[][] map;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        ans = new int[50][50];
        map = new int[50][50];

        for(int i = 0; i < T; i++) {//T개의 테스트케이스만큼 반복
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());//행, 렬 길이정보 입력
            M = Integer.parseInt(stk.nextToken());
            for(int j = 0; j < N; j++) {
                Arrays.fill(ans[i], 0);//ans행렬 각 행에 대해서 초기화 후
                StringTokenizer stk2 = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {//map행렬 0 or 1입력에 대해서 값 세팅
                    map[j][k] = Integer.parseInt(stk2.nextToken());
                }
            }

            for(int t = 0; t < N; t++) {//행 렬에 대해 탐색을 해간다(2x2로 왜냐하면 색지는 2x2이기 떄문)
                for(int v = 0; v < M - 1; v++) {
                    if(map[t][v] == 1) {//탐색했을때 색칠하고싶은 칸이면
                        if(map[t + 1][v + 1] == 1 && map[t + 1][v] == 1 && map[t][v + 1] == 1) {//2x2에 대해 다 색칠하고싶은 칸인지 확인 후 맞으면 ans배열 색칠부분 1로 세팅
                            ans[t][v] = 1;
                            ans[t][v + 1] = 1;
                            ans[t + 1][v + 1] = 1;
                            ans[t + 1][v] = 1;
                        }
                    }
                }
            }
            if(IsChk()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");

            flush();
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }


    private static void flush() {//다음 테스트케이스를 위한 배열 초기화
        for(int i = 0; i < N; i++) {
            Arrays.fill(map[i], 0);
            Arrays.fill(ans[i], 0);
        }
    }

    private static boolean IsChk() {//정답 검증부분
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != ans[i][j]) return false;//조작하지 않은 map배열과 ans배열의 값이 같으면 색칠하고싶은 부분에 색칠했다는 뜻이므로 정답 아니면 오답 표시
            }
        }
        return true;
    }

}
