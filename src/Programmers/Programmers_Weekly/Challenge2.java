package Programmers.Programmers_Weekly;

public class Challenge2 {
    private static int min = 101, max = -1;
    private static boolean isFlag = false;

    private static void getMinMax(int length, int col, int[][] scores) {
        min = 101;
        max = -1;
        isFlag = false;

        int cntMax = 1, cntMin = 1;

        for(int row = 0; row < length; row++) {
            if(min == scores[row][col]) {
                cntMin++;
            } else if(min > scores[row][col]) {
                min = scores[row][col];
                cntMin = 1;
            }

            if(max == scores[row][col]) {
                cntMax++;
            } else if(max < scores[row][col]) {
                max = scores[row][col];
                cntMax = 1;
            }
        }

        if((cntMax >= 2 && scores[col][col] == max) || (cntMin >= 2) && scores[col][col] == min) isFlag = true;
    }

    private static String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
        int length = scores.length;

        for(int col = 0; col < length; col++) {
            int cnt = length;
            getMinMax(length, col, scores);

            double sum = 0;
            for(int row = 0; row < length; row++) {
                if(!isFlag && col == row && (scores[row][col] == max || scores[row][col] == min)) {
                    cnt--;
                    continue;
                }

                sum += scores[row][col];
            }

            sum /= cnt;

            if(sum >= 90) {
                sb.append('A');
            } else if(sum >= 80) {
                sb.append('B');
            } else if(sum >= 70) {
                sb.append('C');
            } else if(sum >= 50) {
                sb.append('D');
            } else {
                sb.append('F');
            }
        }

        return sb.toString();
    }
}
