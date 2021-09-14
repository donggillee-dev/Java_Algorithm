package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//각 문자들이 문자열에서 나타나는 총 개수를 카운트
//두 포인터 방식으로 왼쪽 포인터 반복문으로 이동
    //k보다 적은 개수를 가진애라면 불가능하니까 걍 skip
//그 외에는 오른쪽 포인터 이동시켜주면서 cnt가 k와 동일해질때 길이 측정

//다시 풀기

public class boj_20437 {
    private static void makeArr(int[] arr, String str, int length) {
        for(int i = 0; i < length; i++) {
            arr[str.charAt(i) - 'a']++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(; t > 0; t--) {
            String str = br.readLine();
            int length = str.length(), max = -1, min = 987654321;
            int[] arr = new int[26];
            int k = Integer.parseInt(br.readLine());

            if(k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            makeArr(arr, str, length);

            for(int i = 0; i < length; i++) {
                if(arr[str.charAt(i) - 'a'] < k) continue;

                int count = 1;
                for(int l = i + 1; l < length; l++) {
                    if(str.charAt(l) == str.charAt(i)) count++;

                    if(count == k) {
                        max = Math.max(max, l - i + 1);
                        min = Math.min(min, l - i + 1);
                        break;
                    }
                }
            }

            if(max == -1) sb.append(-1).append("\n");
            else sb.append(min + " " + max).append("\n");
        }

        System.out.print(sb);
    }
}
