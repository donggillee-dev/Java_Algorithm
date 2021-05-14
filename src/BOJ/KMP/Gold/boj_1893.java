package BOJ.KMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//KMP 알고리즘을 이용해서 해결할 수 있는 문제
//문자열의 순서를 이용해서 원본 문자열을 하나씩 shift해보고 shift한 문자열로 시저 암호 문자열과 kmp를 돌려서
//단 하나의 일치만 나와야됨 => 그러면 shift한 값에 대해서 정답 큐에 넣어준다
//정답 큐에 있는 크기를 가지고 출력 형식에 맞게 출력해주면 됨...

//풀이 시간 : 오래걸림...
public class boj_1893 {
    private static int[] makeTable(char[] pattern) {
        int length = pattern.length;
        int j = 0;
        int[] table = new int[length];

        for(int i = 1; i < length; i++) {
            while(j > 0 && pattern[j] != pattern[i]) {
                j = table[j - 1];
            }

            if(pattern[j] == pattern[i]) {
                table[i] = ++j;
            }
        }

        return table;
    }
    private static ArrayList<Integer> kmp(String origin, char[] pattern) {
        int[] table = makeTable(pattern);
        int oriLen = origin.length(), patLen = table.length, j = 0;
        ArrayList<Integer> ret = new ArrayList<>();

        for(int i = 0; i < oriLen; i++) {
            while(j > 0 && origin.charAt(i) != pattern[j]) {
                j = table[j - 1];
            }
            if(origin.charAt(i) == pattern[j]) {
                if(j == patLen - 1) {
                    ret.add(i - patLen);
                    j = table[j];
                } else {
                    ++j;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while(t --> 0) {
            Queue<Integer> q = new LinkedList<>();
            HashMap<Character, Integer> hm = new HashMap<>();
            String order = br.readLine();
            String origin = br.readLine();
            String secret = br.readLine();
            int ordLen = order.length();

            for(int i = 0; i < ordLen; i++) {
                hm.put(order.charAt(i), i);
            }

            int oriLen = origin.length();
            for(int shift = 0; shift < ordLen; shift++) {
                char[] tmp = origin.toCharArray();

                for(int i = 0; i < oriLen; i++) {
                    tmp[i] = order.charAt((hm.get(origin.charAt(i)) + shift) % ordLen);
                }

                if(kmp(secret, tmp).size() == 1) {
                    q.add(shift);
                }
            }

            if(q.size() == 0) {
                sb.append("no solution");
            } else {
                if(q.size() == 1) {
                    sb.append("unique: " + q.peek());
                } else {
                    sb.append("ambiguous: ");
                    while(!q.isEmpty()) {
                        sb.append(q.poll() + " ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
