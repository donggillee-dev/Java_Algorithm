package Programmers_Lv2;

import java.util.Arrays;
import java.util.ArrayList;

public class WordChainGame {
    public static void main(String[] args) {
        int n = 3;
//        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        System.out.println(Arrays.toString(Solution.solution(n, words)));
    }
    private static class Solution {
        private static int[] solution(int n, String[] words) {
            int[] answer = new int[2];
            ArrayList<String> chainList = new ArrayList<>();
            chainList.add(words[0]);

            for(int i = 1; i < words.length; i++) {
                String prev = chainList.get(i - 1);

                if(prev.charAt(prev.length() - 1) != words[i].charAt(0) || chainList.contains(words[i])) {
                    int person = (i + 1) % n;

                    if(person == 0) person = n;

                    answer[0] = person;
                    answer[1] = i / n + 1;
                    break;
                } else chainList.add(words[i]);
            }

            return answer;
        }
    }
}
