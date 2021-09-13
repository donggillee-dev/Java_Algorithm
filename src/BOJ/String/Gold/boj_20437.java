package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Logic
//각 문자들이 문자열에서 나타나는 총 개수를 카운트
//두 포인터 방식으로 왼쪽 포인터 반복문으로 이동
    //k보다 적은 개수를 가진애라면 불가능하니까 걍 skip
//그 외에는 오른쪽 포인터 이동시켜주면서 cnt가 k와 동일해질때 길이 측정

//다시 풀기

public class boj_20437 {

    private static void makeArr(int length, String str, int[] arr) {
        for(int i = 0; i < length; i++) {
            int idx = str.charAt(i) - 'a';
            arr[idx]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[26];

        while (t-- > 0) {
            String str = br.readLine();
            int length = str.length();
            int k = Integer.parseInt(br.readLine());
            int max = -1, min = 987654321;

            if(k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            Arrays.fill(arr, 0);
            makeArr(length, str, arr);

            for(int i = 0; i < length; i++) {
                if(arr[str.charAt(i) - 'a'] < k) continue;

                int cnt = 1;
                for(int j = i + 1; j < length; j++) {
                    if(str.charAt(j) == str.charAt(i)) cnt++;

                    if(cnt == k) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }

            if(min == 987654321 || max == -1) {
                sb.append(-1).append("\n");
            } else {
                sb.append(min + " " + max).append("\n");
            }
        }

        System.out.print(sb);
    }
}
