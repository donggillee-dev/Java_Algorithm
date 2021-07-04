package Programmers.Programmers_2021_NaverWebtoon;

//Logic
//KMP 알고리즘을 더이상 교환 불가능할때까지 돌리는 방식으로 해결
//StringBuilder를 이용해서 문자열 조작의 시간초과나 메모리 초과를 방지하였다

public class Solution3 {
    private static class Solution {
        private int solution(String s, String t) {
            int answer = 0;
            int pat_length = t.length();
            StringBuilder parent = new StringBuilder(s);
            char[] pattern = t.toCharArray();
            int[] table = new int[pattern.length];

            //pattern 이용하여 pi테이블 생성
            makeTable(pattern, table);

            //KMP 반복
            while(true) {
                //-1을 리턴하면 더이상 일치하는 문자열 없기에 종료
                int ret = KMP(parent, pattern, table);

                if(ret == -1) break;
                else {
                    //일치하는 문자열 존재하면 해당 부분 delete해주고 변환 횟수++
                    parent.delete(ret - pat_length + 1, ret + 1);
                    answer++;
                }
            }
            return answer;
        }
        private int KMP(StringBuilder parent, char[] pattern, int[] table) {
            int parentSize = parent.length(), patternSize = pattern.length;
            int j = 0;

            for(int i = 0; i < parentSize; i++) {
                while(j > 0 && pattern[j] != parent.charAt(i)) {
                    j = table[j - 1];
                }
                if(pattern[j] == parent.charAt(i)) {
                    if(j == patternSize - 1) {
                        //KMP알고리즘을 통해서 패턴의 끝에 도달 즉 일치하는게 존재하는 순가
                        //원본 문자열의 index를 리턴
                        return i;
                    } else {
                        j++;
                    }
                }
            }
            //일치하는게 없으면 -1 리턴
            return -1;
        }
        private void makeTable(char[] pattern, int[] table) {
            int patternSize = pattern.length, j = 0;

            for (int i = 1; i < patternSize; i++) {
                while (j > 0 && pattern[j] != pattern[i]) {
                    j = table[j - 1];
                }
                if(pattern[j] == pattern[i]) {
                    table[i] = ++j;
                }
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("aabcbcd", "abc"));
    }
}