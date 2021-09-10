package Programmers.WeeklyChallenge;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week4 {
    private static class Info implements Comparable<Info> {
        String name;
        int score;

        public Info(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Info o) {
            if(o.score == this.score) return this.name.compareTo(o.name);
            return o.score - this.score;
        }
    }
    private static String solution(String[] table, String[] languages, int[] preference) {
        HashMap<String, HashMap<String, Integer>> tableHash = new HashMap<>();
        HashMap<String, Integer> prefHash = new HashMap<>();
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(String str : table) {
            StringTokenizer stk = new StringTokenizer(str);
            String depart = stk.nextToken();

            tableHash.put(depart, new HashMap<>());

            int score = 5;
            while(stk.hasMoreTokens()) {
                tableHash.get(depart).put(stk.nextToken(), score--);
            }
        }

        int size = preference.length;
        for(int i = 0; i < size; i++) {
            prefHash.put(languages[i], preference[i]);
        }

        for(String job : tableHash.keySet()) {
            int score = 0;

            HashMap<String, Integer> subHash = tableHash.get(job);
            for(String language : prefHash.keySet()) {
                int prefScore = subHash.get(language) == null ? 0 : subHash.get(language);
                if(subHash.get(language) != null) {
                    score += (prefHash.get(language) * prefScore);
                }
            }

            pq.add(new Info(job, score));
        }

        return pq.peek().name;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
                new String[]{"PYTHON", "C++", "SQL"}, new int[]{7, 5, 5}));
        System.out.println(solution(new String[]{"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
                new String[]{"JAVA", "JAVASCRIPT"}, new int[]{7, 5}));
    }
}
