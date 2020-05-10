package Kakao_Internship_CT;

import java.io.*;
import java.util.*;
public class Problem3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String[] gems = {"A", "B", "C", "C", "D", "E", "F", "A", "B", "C", "D", "E", "F"};
        System.out.println(Arrays.toString(solution(gems)));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int[] solution(String[] gems) {
        int[] DP = new int[gems.length];
        ArrayList<String> arrayList = new ArrayList<>();
        int[] ans = new int[2];
        int start = 0;
        int end = 0;
        int width = Integer.MAX_VALUE;
        int gem_count;
        for(String data : gems){
            if(!arrayList.contains(data))
                arrayList.add(data);
        }
        gem_count = arrayList.size();
        int j = gems.length;
        int count = 0;
        ArrayList<String> tmp = new ArrayList<>();
        for(int k = 0; k < j; k++) {
            if(!tmp.contains(gems[k])) {
                tmp.add(gems[k]);
                count++;
            }
            if(count == gem_count) {
                System.out.println(k + " " + start + " " + width);
                if(k - start < width) {
                   start = end + 1;
//                   width
                }
                tmp = new ArrayList<>();
                count = 0;
            }
        }
        System.out.println(start + " " + end);
//        System.out.println(Arrays.toString(DP));
//        for(int i = 0; i < DP.length; i++) {
//            if(DP[i] != 0) {
//                if(width > (DP[i] - i)) {
//                    width = DP[i] - i;
//                    start = DP[i] - i;
//                    end = DP[i];
//                }
//            }
//        }
//        System.out.println(start + " " + end);
//        ans[0] = start + 1;
//        ans[1] = end + 1;
        return ans;
    }

}