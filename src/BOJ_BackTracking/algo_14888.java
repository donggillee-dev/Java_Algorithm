package BOJ_BackTracking;

import java.io.*;
import java.util.*;

public class algo_14888 {
    static int N;
    static int[] An;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static LinkedList<OpInfo> Operators = new LinkedList<OpInfo>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());//입력받을 숫자들의 개수 입력
        An = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());//An입력 받아와서

        for(int i = 0; i < N; i++) {//배열에 A1부터 An까지 저장
            An[i] = Integer.parseInt(stk.nextToken());
        }
        stk = new StringTokenizer(br.readLine());//주어진 연산자 개수 토크나이저
        Operators.add(new OpInfo('+', Integer.parseInt(stk.nextToken())));
        Operators.add(new OpInfo('-', Integer.parseInt(stk.nextToken())));
        Operators.add(new OpInfo('*', Integer.parseInt(stk.nextToken())));
        Operators.add(new OpInfo('/', Integer.parseInt(stk.nextToken())));

        dfs(0, An[0]);

        sb.append(max).append("\n").append(min).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void dfs(int idx, int res) {
        if(idx == N - 1) {
            if(res >= max) max = res;
            if(res <= min) min = res;
        } else {
            for(int i = 0; i < Operators.size(); i++) {
                OpInfo tmp = Operators.get(i);
                if(tmp.cnt != 0) {
                    tmp.cnt -= 1;
                    Operators.set(i, tmp);
                    if(tmp.Operator == '+')  {
                        dfs(idx + 1, res + An[idx + 1]);
                    } else if (tmp.Operator == '-') {
                        dfs(idx + 1, res - An[idx + 1]);
                    } else if (tmp.Operator == '*') {
                        dfs(idx + 1, res * An[idx + 1]);
                    } else {
                        dfs(idx + 1, res / An[idx + 1]);
                    }
                    tmp.cnt += 1;
                    Operators.set(i, tmp);
                }
            }
        }
    }
}

class OpInfo {
    char Operator;
    int cnt;

    OpInfo(char ch, int n) {
        this.Operator = ch;
        this.cnt = n;
    }
}
