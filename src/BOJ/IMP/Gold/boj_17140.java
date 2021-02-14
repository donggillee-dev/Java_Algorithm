package BOJ.IMP.Gold;
import java.io.*;
import java.util.*;

public class boj_17140 {
    static private class Info {
        int val, frq;
        public Info(int v, int f) {
            this.val = v;
            this.frq = f;
        }
    }
    static private class Compare implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            if(o1.frq > o2.frq) {
                return 1;
            } else if(o1.frq == o2.frq) {
                if(o1.val > o2.val) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
    static int Row = 3, Column = 3;
    static int R, C, K;
    static int ans = 0;
    static int[][] arr = new int[101][101];
    static int[] cnt_arr = new int[300];
    static LinkedList<Info> List = new LinkedList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        loop1:for(int i = 1; i <= 3; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                int val = Integer.parseInt(stk.nextToken());
                arr[i][j] = val;
            }
        }
        while(ans <= 100) {
            CntRow();
            CntCol();
            if(arr[R][C] == K) break;
            if(Row >= Column) R_cal();
            else if(Row < Column)C_cal();
            ans++;
        }
        if(ans > 100) ans = -1;
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void R_cal() {
        for(int i = 1; i <= 100; i++) {
            for(int j = 1; j <= 100; j++) {//행에 있는 값들에 대해 나타나는 빈도수 체크
                if(arr[i][j] != 0) {
                    cnt_arr[arr[i][j]]++;
                }
            }
            for(int j = 1; j < 300; j++) {//해당 값의 빈도수를 List에 넣어줌
                if(cnt_arr[j] != 0) {
                    List.add(new Info(j, cnt_arr[j]));
                }
            }
            Collections.sort(List, new Compare());//정렬 기준으로 List 정렬
            Info tmp;
            Arrays.fill(arr[i], 0);//기존의 행을 초기화해줌
            int k = 1;
            for(Iterator<Info> it = List.iterator(); it.hasNext();) {//배열에 값 , 빈도수 이렇게 채워넣어줌
                tmp = it.next();
                arr[i][k] = tmp.val;
                arr[i][k + 1] = tmp.frq;
                k += 2;
                it.remove();
            }
            Arrays.fill(cnt_arr, 0);
        }
    }
    private static void C_cal() {
        for(int i = 1; i <= 100; i++) {
            for(int j = 1; j <= 100; j++) {//열에 대해 값, 빈도수 체크 위한 cnt_arr 구성
                if(arr[j][i] != 0) {
                    cnt_arr[arr[j][i]]++;
                }
            }
            for(int j = 1; j < 300; j++) {
                if(cnt_arr[j] != 0) {//cnt_arr을 통해 List에 값, 빈도수 넣어줌
                    List.add(new Info(j, cnt_arr[j]));
                }
            }
            Collections.sort(List, new Compare());//정렬 규칙대로 정렬
            for(int j = 1; j <= Row; j++) {//기존의 열을 초기화 -> 100의 for문을 돌면 시간초과의 위험 -> 최대 행(값이 존재하는 최대 행 idx)만틈
                arr[j][i] = 0;
            }
            Info tmp;
            int k = 1;
            for(Iterator<Info> it = List.iterator(); it.hasNext();) {//열에 대해 다시 값 세팅
                tmp = it.next();
                arr[k][i] = tmp.val;
                arr[k + 1][i] = tmp.frq;
                k += 2;
                it.remove();
            }
            Arrays.fill(cnt_arr, 0);
        }
    }
    private static void CntRow() {
        int cnt = 0;
        for(int i = 1; i <= 100; i++) {
            int k = 0;
            for(int j = 1; j  <= 100; j++) {
                if(arr[j][i] != 0) {
                    k++;
                }
            }
            cnt = Math.max(cnt, k);
        }
        Row = Math.max(cnt, Row);
    }
    private static void CntCol() {
        int cnt = 0;
        for(int i = 1; i <= 100; i++) {
            int k = 0;
            for(int j = 1; j  <= 100; j++) {
                if(arr[i][j] != 0) {
                    k++;
                }
            }
            cnt = Math.max(cnt, k);
        }
        Column = Math.max(cnt, Column);
    }
}