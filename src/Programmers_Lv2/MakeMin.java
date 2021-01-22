package Programmers_Lv2;

//두 개의 배열이 주어짐
//각 배열에서 하나씩 꺼내가지고 각각 곱하고 곱해진 해당 원소는 다시 사용할 수 없음
//그렇게 해서 모든 배열의 원소들을 각각 하나씩 꺼내서 곱한 다음 그 곱한 수들을 모두 더했을시 최소값이 나오게 하여라

//나의 아이디어
//모든 경우의 수를 다해보기에는 시간 복잡도가 초과날 것!
    //
//그렇다면 DP인건가...?
    //DP로도 풀 수 있을 것 같다 하지만 그래도 시간복잡도는 높을 것으로 예상...
//생각을 단순화해보자
    //각 합을 그 순간 최소로 만들 수 있는 방법이 없을까?
    //A배열에서의 최소값과 B배열에서 최대값을 더하면 서로 상쇄시킬 수 있는 느낌이다!!
    //이 방식으로 하면 시간복잡도가 Worst-Case의 경우 1000밖에 안된다!! => 정렬에 대한 시간복잡도는 제외
    //각 배열을 오름, 내림차순으로 각각 정렬한 다음에 각각 원소를 앞에서부터 더해주면
    //결국엔 모든것이 최대 + 최소로 상쇄되어 최종적으로 가장 작은 값이 나올 것

import java.util.Arrays;
import java.util.Collections;

public class MakeMin {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] A = {1, 4, 2};
        int[] B = {5, 4 ,4};
        System.out.println(sol.solution(A, B));
    }
    private static class Solution {
        public int solution(int[] A, int[] B) {
            int answer = 0;

            Integer[] copyA = new Integer[A.length];
            for(int i = 0; i < A.length; i++) {
                copyA[i] = A[i];
            }

            Arrays.sort(copyA, Collections.reverseOrder());
            Arrays.sort(B);

            for(int i = 0; i < copyA.length; i++) {
                answer += (copyA[i] * B[i]);
            }
            return answer;
        }
    }
}
