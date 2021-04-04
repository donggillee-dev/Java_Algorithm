package Programmers.Programmers_2021_KakaoCommerce;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        System.out.println( sol.solution(new int[]{1,1,1,1,1}, new int[]{9,9,9,99,9}) );
    }

    private int solution(int[] gift_cards, int[] wants) {
        int answer = 0;
        int length = gift_cards.length;

        int max = 0;

        for(int i = 0; i < length; i++) {
            if(gift_cards[i] > max) max = gift_cards[i];
            if(wants[i] > max) max = wants[i];
        }

        int[] cntArr = new int[max + 1];

        for(int i = 0; i < length; i++) {
            cntArr[gift_cards[i]]++;
        }

        for(int i = 0; i < length; i++) {
            int cnt = cntArr[wants[i]];

            if(cnt > 0) cntArr[wants[i]]--;
            else answer++;
        }

        return answer;
    }
}
