package SSAFY_ALGO_HW;

import java.util.Scanner;

public class Baby_Gin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.next();
        int[] arr = new int[10];

        for(int i = 0; i < tmp.length(); i++) {
            arr[tmp.charAt(i) - '0']++;
        }


        //removeTriplet
        for(int i = 0; i < arr.length; i++) {
            arr[i] %= 3;
        }

        //removeRun
        for(int i = 0; i < arr.length - 2; i++) {
            if(arr[i] >= 1) {
                if(arr[i + 1] >= 1 && arr[i + 2] >= 1) {
                    arr[i]--;
                    arr[i + 1]--;
                    arr[i + 2]--;
                    if(arr[i] >= 1) i--;
                }
            }
        }

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                System.out.println("no");
                return;
            }
        }

        System.out.println("yes");
    }
}
