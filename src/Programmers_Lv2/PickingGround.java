package Programmers_Lv2;

import java.util.Arrays;
public class PickingGround {
    public static void main(String[] args) {
        //모든 경우의 수를 가지고 풀어보면 어떨까? ( Brute Force )
        //=> 이 방법으로 하게 된 경우에 4^N 으로 시간초과가 날 수 있다.
        //그럼 다른 방법으로 생각해보자 ( 행 간의 연관관계를...!)
        //최종 결과값인 최대값 즉 sol(N)은 이전 행에서 선택한 최선의 선택값에서 그 다음 최선값을 선택한 것
        //그렇다면 N-1 행까지의 최대값은 N-2까지의 최선책과 연관이 있다
        // 1 -> 2 -> ... -> N-1 -> N
        //이건 DP인데..자신이 없지만 시간 복잡도와 예시에 대한 테스트를 해보자!!
        //시간 복잡도 최악의 경우 4 * 100,000 => 400,000 시간은 초과 안됨!!
        //그렇다면 시나리오를 통한 테스트를 해보자
        //1. 현재 행에서 모든 열을 탐색한다
        //2. 선택한 열에 대해 그 다음 행에 선택 가능한 열에 현재 선택한 열의 값을 더해줌
        //2-1. 하지만 다음 행, 열에 더해주려는 값이 이미 다른 곳에서 선택한 값보다 클 경우에는 스킵
        // DP[i+1][j] = max(DP[i+1][j] , DP[i][k] + Ground[i+1][j])
        //3. 이러한 방법으로 N까지 내려오면 결국 최대값...!

        //DP는 항상 코드로 옮기기 이전에 점화식을 도출해봐야 사소한 실수와 코드 작성의 어려움을 느끼지 않을 수 있는 것 같다.

        //알았지만 정확히는 몰랐던거 => 자바의 데이터 복사에는 깊은 복사와 옅은 복사가 존재!!
        //https://coding-factory.tistory.com/548
        Solution sol = new Solution();
        int[][] land = {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}};
        System.out.println("answer : " + sol.solution(land));
    }
    private static class Solution {
        int solution(int[][] land) {
            int answer = Integer.MIN_VALUE;
            int[][] DP = new int[land.length][4];

            for(int i = 0; i < land.length; i++) {
                DP[i] = Arrays.copyOf(land[i], land[i].length);
            }

            for(int i = 0; i < DP.length - 1; i++) {
                for(int j = 0; j < DP[i].length; j++) {
                    for(int k = 0; k < DP[i].length; k++) {
                        if(( j != k) && (DP[i + 1][k] < DP[i][j] + land[i + 1][k])) {
                            DP[i + 1][k] = DP[i][j] + land[i + 1][k];
                            answer = Math.max(answer, DP[i + 1][k]);
                        }
                    }
                }
            }
            return answer;
        }
    }
}
