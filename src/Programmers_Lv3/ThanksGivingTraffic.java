package Programmers_Lv3;

public class ThanksGivingTraffic {
    public static void main(String[] args) {
        String[] lines =  {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
//        String[] lines = {
//        "2016-09-15 01:00:04.002 2.0s",
//        "2016-09-15 01:00:07.000 2s"
//        };
        Solution sol = new Solution();
        int ans = sol.solution(lines);

        System.out.println(ans);

        return;
    }
    private static class Solution {
        public int solution(String[] lines) {
            int answer = 0;
            int[][] times = new int[lines.length][2];

            getTimes(lines, times);

            for(int i = 0; i < times.length; i++) {
                int cnt = 0;
                int startSection = times[i][0];
                int endSection = startSection + 1000;

                for(int j = 0; j < times.length; j++) {
                    if(times[j][0] >= startSection && times[j][0] < endSection) {
                        cnt++;
                    } else if(times[j][1] >= startSection && times[j][1] < endSection) {
                        cnt++;
                    } else if(times[j][0] >= startSection && times[j][1] <= endSection) {
                        cnt++;
                    } else;
                }
                if(answer < cnt) answer = cnt;
            }
            for(int i = 0; i < times.length; i++) {
                int cnt = 0;
                int startSection = times[i][1];
                int endSection = startSection + 1000;

                for(int j = 0; j < times.length; j++) {
                    if(times[j][0] >= startSection && times[j][0] < endSection) {
                        cnt++;
                    } else if(times[j][1] >= startSection && times[j][1] < endSection) {
                        cnt++;
                    } else if(times[j][0] >= startSection && times[j][1] <= endSection) {
                        cnt++;
                    } else;
                }
                if(answer < cnt) answer = cnt;
            }
            return answer;
        }

        public void getTimes(String[] lines, int[][] times) {
            for(int i = 0; i < lines.length; i++) {
                String[] log = lines[i].split(" ");
                String[] timeArr = log[1].split(":");
                String miliSec = log[2].substring(0, log[2].length() - 1);

                int endTime = 0;
                int startTime = 0;
                int processTime = (int)(Double.parseDouble(miliSec) * 1000);

                endTime += Integer.parseInt(timeArr[0]) * 60 * 60 * 1000;
                endTime += Integer.parseInt(timeArr[1]) * 60 * 1000;
                endTime += (int)(Double.parseDouble(timeArr[2]) * 1000);

                startTime = endTime - processTime + 1;

                times[i][0] = startTime;
                times[i][1] = endTime;
            }
            return;
        }
    }
}
