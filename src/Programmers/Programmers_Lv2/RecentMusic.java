package Programmers.Programmers_Lv2;
import java.util.*;

//Logic
//내 아이디어는
//  1. 문자열에 섞여있는 #을 어떻게든 해주고싶다
//  2. 내가 들은 멜로디가 라디오에서 들려주는 멜로디 ( 총 플레이시간 ) 의 부분 문자열인지 체크 => KMP 알고리즘

// 첫번째 아이디어를 위해서는 hashMap을 이용해서 C#, A#, 등등 #이 붙은 음표들을 replaceAll로 처리해주었다
// 그 과정에서 String자체게 문자열 조작 연산을 해주면 메모리 재할당, 삭제에 의해 시간 복잡도가 증가하므로 StringBuilder를 이요
// musicinfo배열에 대해서는 split 메소드릉 이용해서 플레이시간과 총 플레이 시간에 따른 멜로디를 재구성하였다
// 재구성된 멜로디 문자열(문자열 전체)과 비교대상인 내가 들은 멜로디(부분 문자열)을 KMP 알고리즘으로 비교해준다

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
