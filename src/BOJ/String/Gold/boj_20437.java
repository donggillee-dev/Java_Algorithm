package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_20437 {
    private static int getShort(int k, int length, String str) {
        int ans = 987654321, width = k - 1;
        int[] count = new int[26];

        loop:while (width < length) {
            Arrays.fill(count, 0);

            int leftIdx = 0, rightIdx = 0;
            for (; rightIdx < width; rightIdx++) { //create window
                char ch = str.charAt(rightIdx);

                count[ch - 'a']++;

                if(count[ch - 'a'] == k) {
                    ans = Math.min(ans, rightIdx - leftIdx + 1);
                    break loop;
                }
            }

            while(rightIdx < length) {
                char rightCh = str.charAt(rightIdx);
                char leftCh = str.charAt(leftIdx);

                count[leftCh - 'a']--;
                count[rightCh - 'a']++;

                if(count[rightCh - 'a'] == k) {
                    ans = Math.min(ans, rightIdx - leftIdx);
                    break loop;
                }

                rightIdx++;
                leftIdx++;
            }
            width++;
        }

        return ans;
    }

    private static int getLong(int k, int length, String str) {
        int ans = -1, width = length;

        int[] count = new int[26];

        loop:while (width >= 0) {
            Arrays.fill(count, 0);

            int leftIdx = 0, rightIdx = 0;
            for (; rightIdx < width; rightIdx++) { //create window
                char ch = str.charAt(rightIdx);
                count[ch - 'a']++;

                if(count[ch - 'a'] == k && str.charAt(leftIdx) == str.charAt(rightIdx)) {
                    ans = Math.max(ans, rightIdx - leftIdx + 1);
                }
            }

            while(rightIdx < length) {
                char rightCh = str.charAt(rightIdx);
                char leftCh = str.charAt(leftIdx);

                count[leftCh - 'a']--;
                leftIdx++;

                if(count[rightCh - 'a'] == k && str.charAt(leftIdx) == str.charAt(rightIdx)) {
                    ans = Math.max(ans, rightIdx - leftIdx);
                    break loop;
                }

                count[rightCh - 'a']++;
                rightIdx++;
            }
            width--;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());
            int length = str.length();

            int ansA = getShort(k, length, str);
            int ansB = getLong(k, length, str);

            if(ansA == 987654321 || ansB == -1) {
                sb.append(-1);
            } else {
                sb.append(ansA + " " + ansB).append("\n");
            }
        }

        System.out.print(sb);
    }
}
