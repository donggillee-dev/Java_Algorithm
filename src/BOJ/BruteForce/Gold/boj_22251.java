package BOJ.BruteForce.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//비트마스킹으로 풀면될듯?
//비트마스킹을 이용해서 xor로 숫자와 숫자 사이에 필요한 변환 갯수를 카운트
//이걸 이용해서 현재 각 자리의 숫자를 0~9까지 변환 dfs돈다

//풀이 시간 : 50분

public class boj_22251 {
    private static int[] num = {
            0b1111110,
            0b0011000,
            0b0110111,
            0b0111101,
            0b1011001,
            0b1101101,
            0b1101111,
            0b0111000,
            0b1111111,
            0b1111101
    };
    private static int[][] diffCnt = new int[10][10];
    private static int n, k, x;
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static char[] init(int x) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(i == j) continue;

                int xor = num[i] ^ num[j];
                int cnt = 0;
                while(xor != 0) {
                    if((xor & 1) == 1) cnt++;
                    xor >>= 1;
                }

                diffCnt[i][j] = cnt;
            }
        }

        String str = "" + x;
        int length = str.length();
        char[] ch = new char[k];

        Arrays.fill(ch, '0');

        for(int i = length - 1, idx = k - 1; i >= 0; i--, idx--) {
            ch[idx] = str.charAt(i);
        }

        return ch;
    }
    private static int ctoi(char[] arr) {
        int length = arr.length;
        int num = 0;

        for(int i = 0; i < length; i++) {
            num += (arr[i] - '0') * Math.pow(10, length - i - 1);
        }

        return num;
    }
    private static int dfs(char[] arr, int depth, int cnt) {
        if(depth >= arr.length) {
            int num = ctoi(arr);
            if(num == x) return 0;
            if(num >= 1 && num <= n) return 1;

            return 0;
        }
        int ch = arr[depth] - '0';
        int ret = 0;
        for(int i = 0; i < 10; i++) {
            if((ch != i) && (diffCnt[ch][i] <= cnt)) {
                arr[depth] = (char)('0' + i);
                ret += dfs(arr, depth + 1, cnt - diffCnt[ch][i]);
                arr[depth] = (char)('0' + ch);
            } else if(diffCnt[ch][i] <= cnt){
                ret += dfs(arr, depth + 1, cnt);
            }
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = stoi(stk.nextToken());
        k = stoi(stk.nextToken());
        int p = stoi(stk.nextToken());
        x = stoi(stk.nextToken());

        char[] ch = init(x);

        System.out.println(dfs(ch, 0, p));
    }
}
