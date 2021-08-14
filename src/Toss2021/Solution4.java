package Toss2021;

import java.util.regex.Pattern;

public class Solution4 {
    public static void main(String[] args) {
        solution("25,000");
    }

    private static boolean solution(String amountText) {
        boolean answer = true;
        String pattern = "^[0-9,]*$";
        int length = amountText.length();

        if(!Pattern.matches(pattern, amountText)) return false;

        if(amountText.charAt(0) == '0') return false;

        if(amountText.charAt(0) == ',' || amountText.charAt(length - 1) == ',') return false;

        if(amountText.contains(",")) {
            for(int i = length - 1, cnt = 0; i >= 0; i--, cnt++) {
                if(cnt == 3) {

                    if(amountText.charAt(i) != ',') {
                        return false;
                    } else {
                        cnt = 0;
                    }
                }
            }
        }


        return answer;
    }
}
