package Programmers.Programmers_Lv1;
//Logic
//그냥 시뮬레이션 문제
//풀이 시간 : 15분
public class Keypad {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }
    private static class Solution {
        private static int check(int num) {
            if(num == 1 || num == 4 || num == 7) return -1;
            else if(num == 3 || num == 6 || num == 9) return 1;
            else return 0;
        }
        private static boolean calc(int lx, int ly, int rx, int ry, int tx, int ty, String hand) {
            int left_x = Math.abs(lx - tx);
            int left_y = Math.abs(ly - ty);
            int right_x = Math.abs(rx - tx);
            int right_y = Math.abs(ry - ty);
            int dist_left = left_x + left_y;
            int dist_right = right_x + right_y;


            if(dist_left < dist_right) {
                return true;
            } else if(dist_left > dist_right) {
                return false;
            } else {
                if(hand.equals("left")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        private static String solution(int[] numbers, String hand) {
            StringBuilder sb = new StringBuilder();
            int length = numbers.length;
            int left_x = 3, left_y = 0, right_x = 3, right_y = 2;

            for(int i = 0; i < length; i++) {
                int ret = check(numbers[i]);

                if(ret == -1) {
                    left_x = numbers[i] / 3;
                    left_y = 0;
                    sb.append("L");
                } else if(ret == 1) {
                    right_x = numbers[i] / 3 - 1;
                    right_y = 2;
                    sb.append("R");
                } else {
                    int num_x = numbers[i] / 3;

                    if(numbers[i] == 0) {
                        num_x = 3;
                    }

                    if(calc(left_x, left_y, right_x, right_y, num_x, 1, hand)) {
                        sb.append("L");
                        left_x = num_x;
                        left_y = 1;
                    } else {
                        sb.append("R");
                        right_x = num_x;
                        right_y = 1;
                    }
                }
            }
            return sb.toString();
        }
    }
}
