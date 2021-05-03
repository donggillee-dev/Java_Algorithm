package SSAFY_ALGO_TEST.A;

import java.io.*;
import java.util.StringTokenizer;

public class Algo1_서울_09_이동길 {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringBuilder sb = new StringBuilder();

         //TestCase 개수 입력받기
         int TestCase = Integer.parseInt(br.readLine());
         //N, X, K를 입력받기 위한 토크나이저
         StringTokenizer stk;

         //TestCase개수만큼 for-loop
         for(int tc = 1; tc <= TestCase; tc++) {
             //N, X, K 입력받은거 토크나이징
             stk = new StringTokenizer(br.readLine());
             int N = Integer.parseInt(stk.nextToken());
             int X = Integer.parseInt(stk.nextToken());
             int K = Integer.parseInt(stk.nextToken());

             //야바위 횟수만큼 for-loop
             for(int i = 0; i < K; i++) {
                 stk = new StringTokenizer(br.readLine());
                 int A = Integer.parseInt(stk.nextToken());
                 int B = Integer.parseInt(stk.nextToken());

                 //바꾸려는 위치가 A, B둘 중에 현재 간식이 들어있는 위치와 동일할 경우 간식 위치 바꿔줌
                 if(A == X) {
                     X = B;
                 } else if(B == X) {
                     X = A;
                 }
             }

             //최종적으로 바뀐 간식의 위치를 StringBuilder에 넣어준다
             sb.append("#"+tc+" ").append(X).append("\n");
         }
         bw.write(sb.toString());
         bw.close();
         br.close();
    }
}
