package Programmers.Programmers_Lv2;

import java.util.HashMap;

public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        Solution sol = new Solution();

        int answer = sol.solution(skill, skill_trees);

        System.out.println(answer);

        return;
    }
    private static class Solution {
        public int solution(String skill, String[] skill_trees) {
            int skill_length = skill.length(), answer = 0;
            HashMap<Character, Integer> hash = new HashMap<>();

            for(int i = 0, cnt = 1; i < skill_length; i++, cnt++) {
                hash.put(skill.charAt(i), cnt);
            }

            for(String st : skill_trees) {
                int length = st.length();
                boolean isPossible = true;
                int idx = 0;

                for(int i = 0; i < length; i++) {
                    char ch = st.charAt(i);

                    if(hash.get(ch) != null) {
                        int num = hash.get(ch);
                        if(num != idx + 1) {
                            isPossible = false;
                            break;
                        } else idx = num;
                    }
                }

                if(isPossible) answer++;
            }

            return answer;
        }
    }
}
