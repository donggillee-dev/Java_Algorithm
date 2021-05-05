package BOJ.KMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//문제를 제대로 안읽어서 틀렸던 문제..
//교과서 사이사이에 숫자를 실수로 적었으므로 kmp로 원본과 비교할때 숫자의 경우 i index를 그냥 증가시켜버리면 된

public class boj_16172 {
    private static void makeTable(String pat, int[] pt) {
        int j = 0, length = pt.length;

        for(int i = 1; i < length; i++) {
            while(j > 0 && pat.charAt(j) != pat.charAt(i)) {
                j = pt[j - 1];
            }

            if(pat.charAt(j) == pat.charAt(i)) {
                pt[i] = ++j;
            }
        }
    }
    private static void kmp(int[] pt, String ori, String pat) {
        int j = 0, ori_length = ori.length();
        int pat_length = pat.length();

        for(int i = 0; i < ori_length; i++) {
            if(ori.charAt(i) <= '9' && ori.charAt(i) >= 0) continue;
            while(j > 0 && ori.charAt(i) != pat.charAt(j)) {
                j = pt[j - 1];
            }

            if(ori.charAt(i) == pat.charAt(j)) {
                if(j == pat_length - 1) {
                    System.out.println(1);
                    return;
                } else {
                    j++;
                }
            }
        }
        System.out.println(0);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ori = br.readLine();
        String pat = br.readLine();

        int[] pi = new int[pat.length()];

        makeTable(pat, pi);
        kmp(pi, ori, pat);
    }
}
