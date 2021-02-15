package SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class CardGame {
    private static int winCase = 0;
    private static int loseCase = 0;
    private static int[] arr1; //규영이 카드
    private static int[] arr2; //인영이 카드
    private static boolean[] visited;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = Integer.parseInt(br.readLine());


        for(int tc = 1; tc <= TestCase; tc++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            arr1 = new int[9];
            arr2 = new int[9];
            visited = new boolean[19];

            for(int i = 0; i < 9; i++) {
                arr1[i] = Integer.parseInt(stk.nextToken());
                visited[arr1[i]] = true;
            }

            for(int i = 1, idx = 0; i <= 18; i++) {
                if(!visited[i]) {
                    arr2[idx] = i;
                    idx++;
                }
            }

            Logic(0, 9, 0, 0);
            sb.append("#").append(tc).append(" ").append(winCase).append(" ").append(loseCase).append("\n");
            winCase = 0;
            loseCase = 0;
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    //sumA => 규영이 점수 합
    //sumB => 인영이 점수 합
    private static void Logic(int cnt, int depth, int sumA, int sumB) {
        if(depth == cnt) {
            if(sumA > sumB) winCase++;
            else if(sumA < sumB) loseCase++;
            return;
        }

        for(int j = 0; j < 9; j++) {
            int numB = arr2[j];
            if(!visited[numB]) {
                visited[numB] = true;
                if(arr1[cnt] > numB) {
                    Logic(cnt + 1, depth, sumA + arr1[cnt] + numB, sumB);
                } else {
                    Logic(cnt + 1, depth, sumA, sumB + arr1[cnt] + numB);
                }
                visited[numB] = false;
            }
        }

        return;
    }
}
