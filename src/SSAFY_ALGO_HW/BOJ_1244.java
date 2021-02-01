package SSAFY_ALGO_HW;

import java.io.*;
import java.util.StringTokenizer;
public class BOJ_1244 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        //남학생의 경우 스위치 번호가 자기가 받은 수의 배수이면 그 스위치들의 상태를 바꿔줌
        //여학생은 자기가 받은 수의 스위치 번호를 기준으로 좌우가 대칭이면서 가장 긴 좌우 구간을 찾고 해당 구간의 스위치 상태를 모두 바
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        boolean[] arr = new boolean[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; stk.hasMoreTokens() ; i++) {
            if(Integer.parseInt(stk.nextToken()) == 1) arr[i] = true;
            else arr[i] = false;
        }

        cnt = Integer.parseInt(br.readLine());
        for(int i = 0; i < cnt; i++) {
            stk = new StringTokenizer(br.readLine());
            boolean isFemale = Integer.parseInt(stk.nextToken()) == 1 ? false : true;
            int idx = Integer.parseInt(stk.nextToken());
            onOff(isFemale, idx, 0, arr);

        }

        for(int i = 0; i < arr.length; i++) {
            if(i % 20 == 0 && i > 0) {
                sb.delete(sb.length() - 1, sb.length());
                sb.append("\n");
            }
            if(arr[i]) sb.append("1");
            else sb.append("0");
            sb.append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();

    }
    private static void onOff(boolean isFemale, int idx, int width, boolean[] arr) {
        if(isFemale) {
            int left = (idx - width) - 1;
            int right = (idx + width) - 1;

            if(left < 0 || right >= arr.length) {
                for(int i = left + 1; i <= right - 1; i++) {
                    arr[i] = !arr[i];
                }
                return;
            }

            if(arr[left] == arr[right]) onOff(isFemale, idx, width + 1, arr);
            else {
                for(int i = left + 1; i <= right - 1; i++) {
                    arr[i] = !arr[i];
                }
            }
        } else {
            int pos = idx * (width + 1)  - 1;

            if(pos >= arr.length) return;

            arr[pos] = !arr[pos];
            onOff(isFemale, idx, width + 1, arr);
        }
    }
}
