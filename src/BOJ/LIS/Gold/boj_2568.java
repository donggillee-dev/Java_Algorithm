package BOJ.LIS.Gold;

import java.io.*;
import java.util.*;

public class boj_2568 {
    private static int N = 0, cnt = 0;
    private static int[] DP;
    private static Pair[] tracking;
    private static ArrayList<Inf> An = new ArrayList<Inf>();
    private static Stack<Integer> Rm = new Stack<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int idx = 1, lis = 0;
        DP = new int[N];
        tracking = new Pair[N];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            An.add(new Inf(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        Collections.sort(An); //정렬
        DP[0] = An.get(0).Right; //초기값 세팅
        tracking[0] = new Pair(lis, An.get(0).Left);

        while(idx < N) {
            int left = An.get(idx).Left;
            int right = An.get(idx).Right;
            //lis에서 이분탐색을 하지 않을경우 시간복잡도는 n^2
            //할경우 logn
            //이 알고리즘은 잘라야하는 대상들의 값도 필요하기에 추적부분도 추가
            //lis값은 자르지 말아야하는 전깃줄의 개수이고 cnt는 잘라야하는 전깃줄의 개수이다
            if(DP[lis] < right) {//오름차순으로 B전깃줄이 탐색되어지는 경우
                DP[++lis] = right;//DP배열에 해당 값 넣어주고
                tracking[idx] = new Pair(lis, left); //tracking배열에도 현재의 정보 추가

            } else {
                int ret = lower_bound(lis, right);//오름차순이 아닌 해당 전깃줄이 원래 있어야하는 위치 탐색

                DP[ret] = right; //DP배열의 원래 위치에 삽입해줌
                tracking[idx] = new Pair(ret, left);
                //얘와 같은 idx값을 가지는 전깃줄은 자르는 대상 그 이유 : idx값이 같은 이전의 전깃줄은 이 전깃줄보다 높은곳에 존재(한마디로 오름차순이 아님)
                //얘는 원래 있어야하는 위치로의 idx값을 가지게
                cnt++; //잘라야하는 개수 증가

            }
            idx++;
        }

        for(int i = N - 1; i >= 0; i--) {//tracking배열 탐새
            if(lis == tracking[i].idx) {//lis값을 줄여가면서 일치하는 애들은 자르지 말아햐하는 애들
                lis--;
            } else {
                Rm.push(tracking[i].value);//lis값이 중복되는 애들은 잘라야하는 애들
            }
        }

        sb.append(cnt).append("\n");

        while(!Rm.isEmpty()) {//잘라야하는 전깃줄들을 스택에서 순서대로 꺼내온다.
            sb.append(Rm.pop()).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static int lower_bound(int end, int value) { //lis에서 중요한 이분탐색 함수
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(DP[mid] < value) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
    private static class Inf implements Comparable<Inf>{//정렬을 위한 Comparable 인터페이스 상속
        int Left;
        int Right;

        public Inf(int left, int right) {
            this.Left = left;
            this.Right = right;
        }

        @Override
        public int compareTo(Inf inf) { //compareTo 메소드 오버라이드
            if(this.Left > inf.Left) {
                return 1;
            } else if(this.Left < inf.Left) {
                return -1;
            }
            return 0;
        }
    }

    private static class Pair{
        int idx;
        int value;

        public Pair(int i, int v) {
            this.idx = i;
            this.value = v;
        }
    }
}