package HyundaiCard2021;

public class Solution4 {
    private static int solve(String road, int n) {
        int result = 0;
        int s = 0, e = 0, fix = n, temp = 0;

        for(; s < road.length(); s++) {
            // 보수한지 안한지 판별
            if(s > 0 && road.charAt(s - 1) == '0') {
                fix++;
            }

            while(e < road.length()) {
                if(road.charAt(e) == '1') {
                    e++;
                    temp++;
                } else {
                    if(fix <= 0) {
                        break;
                    } else {
                        fix--;
                        e++;
                        temp++;
                    }
                }
            }

            result = Math.max(result, temp);
            temp--;
        }
        return result;
    }
}
