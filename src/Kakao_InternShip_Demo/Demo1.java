package Kakao_InternShip_Demo;
import java.io.*;
import java.util.*;

public class Demo1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[][] sample = {{1, 1}, {2, 2}, {1, 2}};
        System.out.println(Arrays.toString(solution(sample)));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int[] solution(int[][] v) {
        int[] ret = new int[2];
        HashMap<String, Integer> H = new HashMap<>();
        for(int i = 0; i < v.length; i++) {
            int x = v[i][0];
            int y = v[i][1];
            String X = "X_" + x;
            String Y = "Y_" + y;

            if(H.containsKey(X)) {
                int cnt = H.get(X);
                cnt++;
                H.put(X, cnt);
            } else {
                H.put(X, 1);
            }

            if(H.containsKey(Y)) {
                int cnt = H.get(Y);
                cnt++;
                H.put(Y, cnt);
            } else {
                H.put(Y, 1);
            }
        }
        H.forEach((key, value) -> {
            if(value == 1){
                String[] arr = key.split("_");
                if(arr[0].equals("X")) {
                    ret[0] = Integer.parseInt(arr[1]);
                } else {
                    ret[1] = Integer.parseInt(arr[1]);
                }
            }
        });
        return ret;
    }
}
