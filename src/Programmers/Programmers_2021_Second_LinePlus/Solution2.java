package Programmers.Programmers_2021_Second_LinePlus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution2 {
    private static class Info implements Comparable<Info> {
        char ch;
        int cnt;

        public Info(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            if(o.cnt == this.cnt) {
                return Character.compare(this.ch, o.ch);
            } else {
                return o.cnt - this.cnt;
            }
        }
    }

    private static HashMap<Character, Integer>[] initData(int totDay, String[] research, HashSet<Character> keywordSet) {
        HashMap<Character, Integer>[] table = new HashMap[totDay];

        for (int i = 0; i < totDay; i++) {
            table[i] = new HashMap<>();
        }

        for (int i = 0; i < totDay; i++) {
            int strLength = research[i].length();
            for (int j = 0; j < strLength; j++) {
                char keyword = research[i].charAt(j);

                keywordSet.add(keyword);
                table[i].put(keyword, table[i].getOrDefault(keyword, 0) + 1);
            }
        }

        return table;
    }

    private static String solution(String[] research, int n, int k) {
        String answer = "";
        int totDay = research.length;
        HashSet<Character> keywordSet = new HashSet<>();
        HashMap<Character, Integer>[] table = initData(totDay, research, keywordSet);
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for (char keyword : keywordSet) {
            int sum = 0, min = 987654321, day = 0, idx = 0;
            int cnt = 0;

            while (idx < totDay) {
                if (table[idx].get(keyword) != null) {
                    int num = table[idx].get(keyword);

                    sum += num;
                    min = Math.min(min, num);
                    day++;

                    if (day == n) {
                        if (min >= k && sum >= (2 * n * k)) {
                            cnt++;
                        }
                        sum = num;
                        min = num;
                        day = 1;
                    }
                } else {
                    day = 0;
                    sum = 0;
                    min = 987654321;
                }
                idx++;
            }

            if(cnt != 0) {
                pq.add(new Info(keyword, cnt));
            }
        }

        if (pq.isEmpty()) {
            answer = "None";
        } else {
            answer += pq.peek().ch;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"abaaaa", "aaa", "abaaaaaa", "fzfffffffa"}, 2, 2));
        System.out.println(solution(new String[]{"yxxy", "xxyyy"}, 2, 1));
        System.out.println(solution(new String[]{"yxxy", "xxyyy", "yz"}, 2, 1));
        System.out.println(solution(new String[]{"xy", "xy"}, 1, 1));
    }
}
