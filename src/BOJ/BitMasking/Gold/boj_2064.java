package BOJ.BitMasking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//네트워크 개념을 알아야 풀 수 있는 문제
//네트워크 ip => ip에다가 서브넷 마스크를 & 하면 구할 수 있음
//서브넷 마스크 => 모든 네트워크 ip들끼리 한 비트씩 왼쪽에서 비교해보고 달라지는 bit부터가 0 이전까지는 1
    //연속된 1과 0으로 이루어진 것
    //1111111100... => 가능, 11110111 => 불가능

public class boj_2064 {
    private static String ipToString(int ip) {
        StringBuilder sb = new StringBuilder();

        int bit = (1 << 8) - 1;

        for(int i = 0, shift = 24; i < 4; i++, shift -= 8) {
            sb.append((ip >> shift) & bit);

            if(i == 3) break;
            else sb.append(".");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] table = new int[n];

        for(int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), ".");

            for(int j = 0; j < 4; j++) {
                table[i] <<= 8;
                table[i] |= Integer.parseInt(stk.nextToken());
            }
        }

        int mask = 0;
        loop:for(int i = 31; i >= 0; i--) {
            int bit = (1 << i);

            for(int j = 1; j < n; j++) {
                if((table[0] & bit) != (table[j] & bit)) {
                    break loop;
                }
            }

            mask |= bit;
        }
        System.out.println(ipToString(table[0] & mask));
        System.out.println(ipToString(mask));
    }
}
