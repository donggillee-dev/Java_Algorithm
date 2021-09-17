package Programmers.Programmers_Weekly;

public class Challenge1 {
    private static long solution(int price, int money, int count) {
        long answer = 0;

        for(int i = 1; i <= count; i++) {
            answer += (i * price);
        }

        return answer <= money ? 0 : (answer - money);
    }
}
