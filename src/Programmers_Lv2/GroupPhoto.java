package Programmers_Lv2;
import java.util.*;

public class GroupPhoto {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        System.out.println(sol.solution(n, data));
    }
    private static class Solution {
        static boolean visited[] = new boolean[8];
        static char[] peopleList = new char[8];
        static int answer = 0;
        public int solution(int n, String[] data) {
            char[] list = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
            answer = 0;
            collcate(n, data, 0, list);

            return answer;
        }
        public void collcate(int n, String[] data, int idx, char[] list) {
            if(idx == 8) {
                for(int i = 0; i < n; i++) {
                    int person1_idx = -1;
                    int person2_idx = -1;
                    char person1 = data[i].charAt(0);
                    char person2 = data[i].charAt(2);
                    char range = data[i].charAt(3);
                    int num = data[i].charAt(4) - '0';

                    for(int j = 0; j < peopleList.length; j++) {
                        if(person1 == peopleList[j]) person1_idx = j;
                        else if(person2 == peopleList[j]) person2_idx = j;
                        else if(person1_idx != -1 && person2_idx != -1) break;
                    }

                    int sub = Math.abs(person1_idx - person2_idx) - 1;
                    if(range == '>') {
                        if(sub <= num) return;
                    } else if(range == '<') {
                        if(sub >= num) return;
                    } else if(range == '=') {
                        if(sub != num) return;
                    } else;
                }
                answer++;
                return;
            }
            for(int i = 0; i < peopleList.length; i++) {
                if(!visited[i]){
                    visited[i] = true;
                    peopleList[idx] = list[i];
                    collcate(n, data, idx + 1, list);
                    visited[i] = false;
                }
            }

            return;
        }
    }
}
