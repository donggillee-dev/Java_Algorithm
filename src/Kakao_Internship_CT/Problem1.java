package Kakao_Internship_CT;
import java.io.*;
import java.util.*;

public class Problem1 {
    static int[] dir_x = {1, -1, 0, 0};
    static int[] dir_y = {0, 0, 1, -1};
    static int disL;
    static int disR;
    static boolean[][] Visited = new boolean[4][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] nums = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String ans = solution(nums, hand);
        System.out.println(ans);
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static String solution(int[] numbers, String hand) {
        int[][] Phone = new int[4][3];
        int LeftHandPosX = 3, LeftHandPosY = 0;
        int RightHandPosX = 3, RightHandPosY = 2;

        String answer = "";
        for(int i = 0; i < numbers.length; i++) {
//            System.out.println(LeftHandPosX + " " + LeftHandPosY);
//            System.out.println(RightHandPosX + " " + RightHandPosY);
//            System.out.println();
            int target = numbers[i];
            if(target == 2 || target == 5 || target == 8 || target == 0) {
                int tmpRx = RightHandPosX, tmpRy = RightHandPosY, tmpLx = LeftHandPosX, tmpLy = LeftHandPosY;
                int tar_x = 0, tar_y = 0;
                int right_dis, left_dis;
                if(target == 2) {
                    tar_x = 0;
                    tar_y = 1;
                } else if(target == 5) {
                    tar_x = 1;
                    tar_y = 1;
                } else if(target == 8) {
                    tar_x = 2;
                    tar_y = 1;
                } else {
                    tar_x = 3;
                    tar_y = 1;
                }
                disL = Integer.MAX_VALUE;
                disR = Integer.MAX_VALUE;
                Visited[RightHandPosX][RightHandPosY] = true;
                RightDFS(0, tar_x, tar_y, tmpRx, tmpRy);
                Visited[RightHandPosX][RightHandPosY] = false;
                Visited[LeftHandPosX][LeftHandPosY] = true;
                LeftDFS(0, tar_x, tar_y, tmpLx, tmpLy);
                Visited[LeftHandPosX][LeftHandPosY] = false;
                if(disR < disL) {
                    RightHandPosX = tar_x;
                    RightHandPosY = tar_y;
                    answer += "R";
                } else if(disR > disL) {
                    LeftHandPosX = tar_x;
                    LeftHandPosY = tar_y;
                    answer += "L";
                } else {
                    if(hand.equals("right")) {
                        answer += "R";
                        RightHandPosX = tar_x;
                        RightHandPosY = tar_y;
                    } else {
                        LeftHandPosX = tar_x;
                        LeftHandPosY = tar_y;
                        answer += "L";
                    }
                }
            } else if(target == 1 || target == 4 || target == 7) {
                answer += "L";
                if(target == 1) {
                    LeftHandPosX = 0;
                    LeftHandPosY = 0;
                } else if(target == 4) {
                    LeftHandPosX = 1;
                    LeftHandPosY = 0;
                } else {
                    LeftHandPosX = 2;
                    LeftHandPosY = 0;
                }
            } else {
                answer += "R";
                if(target == 3) {
                    RightHandPosX = 0;
                    RightHandPosY = 2;
                }else if(target == 6) {
                    RightHandPosX = 1;
                    RightHandPosY = 2;
                } else {
                    RightHandPosX = 2;
                    RightHandPosY = 2;
                }
            }
        }
        return answer;
    }
    private static void LeftDFS(int distance, int tar_x, int tar_y, int cur_x, int cur_y) {
        if(cur_x == tar_x && cur_y == tar_y) {
            if(disL > distance) disL = distance;
            return;
        } else {
            for(int i = 0; i < 4; i++) {
                int nx = cur_x + dir_x[i];
                int ny = cur_y + dir_y[i];
                if(nx < 0 || nx > 3 || ny < 0 || ny > 2) continue;
                if(!Visited[nx][ny]) {
                    Visited[nx][ny] = true;
                    LeftDFS(distance + 1, tar_x, tar_y, nx, ny);
                    Visited[nx][ny] = false;
                }
            }
        }
    }
    private static void RightDFS(int distance, int tar_x, int tar_y, int cur_x, int cur_y) {
        if(cur_x == tar_x && cur_y == tar_y) {
            if(disR > distance) disR = distance;
            return;
        } else {
            for(int i = 0; i < 4; i++) {
                int nx = cur_x + dir_x[i];
                int ny = cur_y + dir_y[i];
                if(nx < 0 || nx > 3 || ny < 0 || ny > 2) continue;
                if(!Visited[nx][ny]) {
                    Visited[nx][ny] = true;
                    RightDFS(distance + 1, tar_x, tar_y, nx, ny);
                    Visited[nx][ny] = false;
                }

            }
        }
    }
}
