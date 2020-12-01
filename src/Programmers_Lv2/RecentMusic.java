package Programmers_Lv2;
import java.util.*;

public class RecentMusic {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String m = "ABC";
        String[] musicinfos = {
                "12:00,12:14,HELLO,C#DEFGAB",
                "13:00,13:05,WORLD,ABCDEF"
        };

        System.out.println(sol.solution(m, musicinfos));

        return;
    }
    private static class Solution {
        public String solution(String m, String[] musicinfos) {
            StringBuilder answer_sb = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            PriorityQueue<Info> Q = new PriorityQueue<>(new Comparator<Info>() {
                @Override
                public int compare(Info o1, Info o2) {
                    return o2.time - o1.time;
                }
            });

            String[] infoArr, start, end;
            int startTime, endTime, playTime;

            for(String str : musicinfos) {
                infoArr = str.split(",");
                start = infoArr[0].split(":");
                end = infoArr[1].split(":");
                startTime = Integer.parseInt(start[0]) * 3600 + Integer.parseInt(start[1]) * 60;
                endTime = Integer.parseInt(end[0]) * 3600 + Integer.parseInt(end[1]) * 60;
                playTime = (endTime - startTime) / 60;

                int idx = 0;
                for(int i = 1; i <= playTime; i++) {
                    if(infoArr[3].charAt(idx) == '#') {
                        playTime++;
                    }
                    sb.append(infoArr[3].charAt(idx));
                    idx++;
                    if(idx == infoArr[3].length()) idx = 0;
                }

                if(String.valueOf(sb).contains(m)) {
                    int flagidx = String.valueOf(sb).indexOf(m) + m.length();
                    if((flagidx < sb.length()) && !(sb.charAt(flagidx) == '#'))
                        Q.offer(new Info(playTime, infoArr[2]));
                }
                sb.delete(0, sb.length());
            }
            answer_sb.append(Q.poll().title);
            Q.clear();
            return String.valueOf(answer_sb);
        }
    }
    private static class Info {
        int time;
        String title;
        public Info(int time, String title) {
            this.time = time;
            this.title = title;
        }
    }
}
