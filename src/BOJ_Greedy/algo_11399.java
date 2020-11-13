package BOJ_Greedy;
import java.io.*;
import java.util.*;

public class algo_11399 {
    static ArrayList<P> People = new ArrayList<P>();
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            People.add(new P(Integer.parseInt(stk.nextToken())));
        }
        Collections.sort(People);
        People.get(0).belongs = People.get(0).time;

        for(int i = 1; i < N; i++) {
            People.get(i).belongs = People.get(i - 1).belongs + People.get(i).time;
        }

        for(int i = 0; i < N; i++) {
            ans += People.get(i).belongs;
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class P implements Comparable<P> {
    int time;
    int belongs = 0;
    public P(int input) {
        this.time = input;
    }

    @Override
    public int compareTo(P p) {
        if(this.time < p.time) return -1;
        else if(this.time > p.time) return 1;
        else return 0;
    }
}
