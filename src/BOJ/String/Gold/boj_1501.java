package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic
//문자열별 앞뒤빼고 정렬해서 같은 키가 존재하는지 확인만 하면 되는 문제, 조합으로 풀면 메모리초과
//풀이 시간 : 1시간 20분
public class boj_1501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), M = 0;
        HashMap<String, Integer> hash = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        while(N --> 0) {
            String input = sortString(br.readLine());
            hash.put(input, hash.getOrDefault(input, 0) + 1);
        }

        M = Integer.parseInt(br.readLine());

        while(M --> 0) {
            int ans = 1;
            StringTokenizer stk = new StringTokenizer(br.readLine());
            while(stk.hasMoreTokens()) {

                String word = sortString(stk.nextToken());
                int cnt = hash.getOrDefault(word, 0);
                ans *= cnt;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
    private static String sortString(String str) {
        if(str.length() <= 2) return str;
        char[] arr = new char[str.length() - 2];

        for(int i = 1, idx = 0; i < str.length() - 1; i++, idx++) {
            arr[idx] = str.charAt(i);
        }

        Arrays.sort(arr);
        String ret = str.charAt(0) + "";

        for(int i = 0; i < arr.length; i++) {
            ret += arr[i];
        }
        ret += str.charAt(str.length() - 1);

        return ret;
    }
}
