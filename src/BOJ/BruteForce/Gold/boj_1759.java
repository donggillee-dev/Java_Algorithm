package BOJ.BruteForce.Gold;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

//Logic
//1. 암호는 사전적으로 오름차순 순서를 가지므로 들어온 문자들을 우선적으로 정렬해준다
//2. 정렬해준 문자들에 대해서 하나씩 꺼내 원하는 C만큼 길이의 문자열을 조합으로 완성해 나간다
//3. C길이의 문자열 조합에 완성했다면 조건 검증 => 모음이 최소 1개 이상, 자음이 최소 2개 이상

public class boj_1759 {
    private static int L, C;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        L = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        char[] arr = new char[L], input = new char[C];

        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < C; i++) {
            input[i] = stk.nextToken().charAt(0);
        }

        Arrays.sort(input);
        func(0, 0, arr, input, sb);
        System.out.print(sb);
    }
    private static void func(int cnt, int idx, char[] arr, char[] input, StringBuilder sb) {
        if(cnt == L) {
            int mo = 0, ja = 0;
            for(int i = 0; i < cnt; i++) {
                if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                    mo++;
                } else {
                    ja++;
                }
                sb.append(arr[i]);
            }
            if(mo >= 1 && ja >= 2) {
                sb.append("\n");
            } else {
                sb.setLength(sb.length() - L);
            }
            return;
        }

        for(int i = idx; i < C; i++) {
            arr[cnt] = input[i];
            func(cnt + 1, i + 1, arr, input, sb);
        }
    }
}
