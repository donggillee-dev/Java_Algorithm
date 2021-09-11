package Programmers.Programmers_2021_First_LinePlus.Part1;

import java.io.*;
import java.util.HashMap;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Solution.solution(new String[]{"SI JAVA JAVASCRIPT SQL PYTHON C#\", \"CONTENTS JAVASCRIPT JAVA PYTHON SQL C++\", \"HARDWARE C C++ PYTHON JAVA JAVASCRIPT\", \"PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP\", \"GAME C++ C# JAVASCRIPT C JAVA"}, new String[]{"PYTHON", "C++", "SQL"}, new int[]{7, 5, 5});

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Solution {
        private static String solution(String[] table, String[] languages, int[] preference) {
            StringBuilder answer = new StringBuilder();
            int answerScore = Integer.MIN_VALUE;
            String[] rowArr;
            HashMap<String, HashMap<String, Integer>> totalHash = new HashMap<String, HashMap<String, Integer>>();

            for(String row : table) {
                rowArr = row.split(" ");
                totalHash.put(rowArr[0], new HashMap<>());
                int score = 5;

                for(int i = 1; i <= 5; i++, score--) {
                    totalHash.get(rowArr[0]).put(rowArr[i], score);
                }
            }

            for(String depart : totalHash.keySet()) {
                int length = preference.length, departScore = 0;
                for(int i = 0; i < length; i++) {
                    if(totalHash.get(depart).get(languages[i]) != null) {
                        departScore += (preference[i] * totalHash.get(depart).get(languages[i]));
                    }
                }
                if(departScore >= answerScore) {
                    if(departScore == answerScore) {
                        if(answer.toString().compareTo(depart) > 0) {
                            answer.setLength(0);
                            answer.append(depart);
                        }
                    } else {
                        answer.setLength(0);
                        answer.append(depart);
                        answerScore = departScore;
                    }
                }
            }

            return answer.toString();
        }
    }
}
