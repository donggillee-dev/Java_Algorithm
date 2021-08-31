package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

//Logic
//달 + 100 + 일로 해서 각 날짜를 표현
//3월 1일보다 전에 시작하면서 3월 1일 이후에 늦게 지는 꽃들중에 가장 늦게 지는 꽃을 픽 => 이렇게 기준점으로 잡고 이 기준점을 0301이라고 생각해서 모든 꽃들 하나씩 다 순회
//주어지는 꽃은 동일한 종류가 주어질 수도 있음

//풀이 시간 : 50분

public class boj_2457 {
    private static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start * 1000 + end);
        }

        @Override
        public boolean equals(Object o) {
            Info i = (Info) o;

            if (i.start == this.start && i.end == this.end) return true;

            return false;
        }

        @Override
        public int compareTo(Info o) {
            if (o.start == this.start) return this.start - o.start;
            else {
                return this.end - o.end;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int ans = 0;
        HashSet<Info> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken()) * 100 + Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken()) * 100 + Integer.parseInt(stk.nextToken());

            set.add(new Info(start, end));
        }


        int cur = 301;
        boolean flag = false;

        while (cur < 1201) {
            int max = cur;
            for (Info inf : set) {
                if (inf.start <= cur && max < inf.end) {
                    max = inf.end;
                    flag = true;
                }
            }

            if(flag) {
                flag = false;
                cur = max;
                ans++;
            } else { //이거 안해주면 시간초과남
                break;
            }
        }

        if (cur < 1201) System.out.println(0);
        else System.out.println(ans);
    }
}
