package Programmers.Programmers_Lv2;

public class JoyStick {
    private static int solution(String name) {
        int answer = 0, length = name.length();

        int min = length - 1;

        for(int i = 0; i < length; i++) {
            answer += (name.charAt(i) - 'A') > ('Z' - name.charAt(i) + 1) ? ('Z' - name.charAt(i) + 1) : (name.charAt(i) - 'A');

            int index = i + 1;

            while(index < length && name.charAt(index) == 'A') index++;

            min = Math.min(min, (i * 2 + (length - index)));
        }

        return answer + min;
    }
}
