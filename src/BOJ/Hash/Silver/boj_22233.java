package BOJ.Hash.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic
//HashMap을 이용해서 처리하면 되는 문제
//문제와 예제를 보고 확실히 해야 실수를 안할 수 있다 (메모장에 없는 키워드 null처리)

public class boj_22233 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());
        HashMap<String, Boolean> hash = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            hash.put(str, false);
        }

        for(int i = 0; i < m; i++) {
            String[] arr = br.readLine().split(",");
            int length = arr.length;

            for(int j = 0; j < length; j++) {
                if(hash.get(arr[j]) != null && !hash.get(arr[j])) {
                    n--;
                    hash.put(arr[j], true);
                }
            }

            sb.append(n).append("\n");
        }

        System.out.print(sb);
    }
}
