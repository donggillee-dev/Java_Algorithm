package BOJ.BOJ_Greedy;

import java.io.*;
import java.util.*;

public class algo_1931 {
    static int N, ans = 1;
    static ArrayList<Agenda> AgendaList = new ArrayList<Agenda>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int tmp = 0;

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            AgendaList.add(new Agenda(start, end));
        }
        Collections.sort(AgendaList);
        tmp = AgendaList.get(0).end;

        for(int i = 1; i < N; i++) {
            if(tmp <= AgendaList.get(i).start) {
                tmp = AgendaList.get(i).end;
                ans++;
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class Agenda implements Comparable<Agenda>{
    int start;
    int end;

    public Agenda(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public int compareTo(Agenda agenda) {
        if(this.end < agenda.end) return -1;
        else if(this.end == agenda.end) {
            if(this.start < agenda.start) return -1;
            else if(this.start > agenda.start) return 1;
            else return 0;
        }
        else return 1;
    }
}