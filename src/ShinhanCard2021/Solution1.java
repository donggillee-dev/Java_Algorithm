package ShinhanCard2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;

        while(true) {
            int money = stoi(br.readLine());

            if(money == -1) break;

            sum += money;
        }

        System.out.println(sum);
    }
}
