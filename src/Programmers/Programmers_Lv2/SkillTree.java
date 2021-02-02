package Programmers.Programmers_Lv2;

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
            int answer = 0;

            for(int i = 0; i < skill_trees.length; i++) {
                String skill_tree = skill_trees[i];
                int idx = 0;

                for(int j = 0; j < skill_tree.length(); j++) {
                    if(idx >= skill.length()) break;
                    if(skill_tree.charAt(j) == skill.charAt(idx)) {
                        idx++;
                    }
                }
                if(idx >= skill.length())
                    answer++;
                else {
                    boolean isPossible = true;
                    StringBuilder sb = new StringBuilder();

                    for(int j = idx; j < skill.length(); j++) {
                        sb.append(skill.charAt(j));

                        if(skill_tree.contains(String.valueOf(sb))) {
                            isPossible = false;
                            break;
                        }
                        sb.delete(0, sb.length());
                    }
                    if(isPossible) answer++;
                }
            }
            return answer;
        }
    }
}
