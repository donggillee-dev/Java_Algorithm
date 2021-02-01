package Programmers_Lv2;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsClustering {
    public static void main(String[] args) {
        System.out.println(Solution.solution("handshake", "shake hands"));
    }
    private static class Solution {
        private static int solution(String str1, String str2) {
            Map<String, Integer> hash1 = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
            Map<String, Integer> hash2 = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
            Pattern nonValidPattern = Pattern.compile("[^a-zA-Z]");
            Matcher matcher;
            StringBuilder sb = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            sb.append(str1);

            for(int i = 0; i < sb.length() - 1; i++) {
                tmp.delete(0, tmp.length());
                tmp.append(sb.charAt(i)).append(sb.charAt(i + 1));
                matcher = nonValidPattern.matcher(String.valueOf(tmp));
                if(matcher.find()) continue;
                if(hash1.containsKey(tmp.toString())) {
                    int n = hash1.get(tmp.toString());
                    n++;
                    hash1.put(tmp.toString(), n);
                } else {
                    hash1.put(tmp.toString(), 1);
                }
            }

            sb.delete(0, sb.length());
            sb.append(str2);

            for(int i = 0; i < sb.length() - 1; i++) {
                tmp.delete(0, tmp.length());
                tmp.append(sb.charAt(i)).append(sb.charAt(i + 1));
                matcher = nonValidPattern.matcher(String.valueOf(tmp));
                if(matcher.find()) continue;
                if(hash2.containsKey(tmp.toString())) {
                    int n = hash2.get(tmp.toString());
                    n++;
                    hash2.put(tmp.toString(), n);
                } else {
                    hash2.put(tmp.toString(), 1);
                }
            }

            int union = 0;
            int inter = 0;

            for(String key : hash1.keySet()) {
                if(hash2.containsKey(key)) {
                    int first = hash1.get(key);
                    int second = hash2.get(key);
                    inter += Math.min(first, second);
                    if(first < second) {
                        hash1.put(key, second);
                        hash2.remove(key);
                    } else {
                        hash2.remove(key);
                    }
                }
            }

            for(int elem : hash1.values()) {
                union += elem;
            }

            for(int elem : hash2.values()) {
                union += elem;
            }


            if(union == 0 && inter == 0) return 0;
            else return (int)((inter/(double)union) * 65536);
        }
    }
}
