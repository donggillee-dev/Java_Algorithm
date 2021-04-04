package Programmers.Programmers_Lv2;
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
    }
    private static class Solution {
        public String solution(String m, String[] musicinfos) {
            HashMap<String, String> hash = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            StringBuilder ansTitle = new StringBuilder("(None)");
            int[] pi = new int[m.length()];
            int ansLength = 0;

            initHash(hash);

            m = changeStr(hash, m);
            makeTable(sb.toString(), pi);

            for(String line : musicinfos) {
                String[] info = line.split(",");
                String[] start = info[0].split(":");
                String[] end = info[1].split(":");
                int startTime = Integer.parseInt(start[0]) * 3600 + Integer.parseInt(start[1]) * 60;
                int endTime = Integer.parseInt(end[0]) * 3600 + Integer.parseInt(end[1]) * 60;
                int playTime = (endTime - startTime) / 60;

                info[3] = changeStr(hash, info[3]);

                sb.append(info[3]);
                int length = sb.length();

                if(length < playTime) {
                    int cnt = (playTime / length);
                    int rem = playTime % length;

                    while(cnt --> 0) {
                        sb.append(info[3]);
                    }

                    if(rem != 0) {
                        for(int i = 0; i < rem; i++) {
                            sb.append(info[3].charAt(i));
                        }
                    }
                } else if(length > playTime) {
                    sb.setLength(playTime);
                }

                if(kmp(sb.toString(), m, pi)) {
                    if(ansLength < playTime) {
                        ansLength = playTime;
                        ansTitle.setLength(0);
                        ansTitle.append(info[2]);
                    }
                }

                sb.setLength(0);
            }

            return ansTitle.toString();
        }
        private static void initHash(HashMap<String, String> hash) {
            hash.put("C#", "c");
            hash.put("D#", "d");
            hash.put("F#", "f");
            hash.put("G#", "g");
            hash.put("A#", "a");
        }

        private static String changeStr(HashMap<String, String> hash, String origin) {
            StringBuilder sb = new StringBuilder(origin);
            StringBuilder tmp = new StringBuilder();

            for(String key : hash.keySet()) {
                tmp.append(sb.toString().replaceAll(key, hash.get(key)));
                sb.setLength(0);
                sb.append(tmp);
                tmp.setLength(0);
            }

            return sb.toString();
        }
        private static void makeTable(String pattern, int[] pi) {
            int j = 0, length = pattern.length();

            for(int i = 1; i < length; i++) {
                while(j != 0 && pattern.charAt(i) != pattern.charAt(j)) {
                    j = pi[j - 1];
                }

                if(pattern.charAt(i) == pattern.charAt(j)) {
                    pi[i] = ++j;
                }
            }
        }
        private static boolean kmp(String parent, String pattern, int[] pi) {
            int j = 0, parentLength = parent.length(), patternLength = pattern.length();

            for(int i = 0; i < parentLength; i++) {
                while(j != 0 && parent.charAt(i) != pattern.charAt(j)) {
                    j = pi[j - 1];
                }

                if(parent.charAt(i) == pattern.charAt(j)) {
                    if(j == patternLength - 1) {
                        return true;
                    } else {
                        j++;
                    }
                }
            }

            return false;
        }
    }
}
