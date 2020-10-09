package Programmers_2020_Coupang;

import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
        int[] score = {24,22,20,10,5,3,2,1};
        int ans = solution(3, score);
        System.out.println(ans);
    }
    public static int solution(int k, int[] score) {
        int answer = score.length;
        long[] diff = new long[score.length - 1];
        long[] Sortdiff;

        List<Info> ModifiedCntList = new ArrayList<>();
        List<Integer> ModifiedUsersNum = new ArrayList<>();

        for(int i = 0; i < score.length - 1; i++) {
            diff[i] = Math.abs(score[i + 1] - score[i]);
        }
        Sortdiff = diff.clone();
        Arrays.sort(Sortdiff);
        long num = Sortdiff[0];
        int cnt = 1;
        for(int i = 1; i < Sortdiff.length; i++) {
            if(num != Sortdiff[i]) {
                ModifiedCntList.add(new Info(num, cnt));
                cnt = 1;
                num = Sortdiff[i];
            } else
                cnt++;
        }
        for(int i = 0; i < ModifiedCntList.size(); i++) {
            Info tmp = ModifiedCntList.get(i);
            if(tmp.cnt >= k) {
                for(int j = 0; j < diff.length; j++) {
                    if(tmp.d == diff[j] && !ModifiedUsersNum.contains(j))
                        ModifiedUsersNum.add(j);
                    if(tmp.d == diff[j] && !ModifiedUsersNum.contains((j + 1)))
                        ModifiedUsersNum.add(j + 1);
                }
            }
        }
        answer -= ModifiedUsersNum.size();
        return answer;
    }
    static class Info {
        long d;
        int cnt;
        public  Info(long d1, int cnt1) {
            this.d = d1;
            this.cnt = cnt1;
        }
    }
}
