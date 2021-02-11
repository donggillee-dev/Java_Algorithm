package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class algo_2346 {
    private static class BallonInfo {
        int idx;
        int move;
        public BallonInfo(int idx, int move) {
            this.idx = idx;
            this.move = move;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<BallonInfo> list = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            list.add(new BallonInfo(i, Integer.parseInt(stk.nextToken())));
        }

        int cur_idx = 0;

        while(list.size() > 0) {
            BallonInfo curBallon = list.get(cur_idx);
            int move = curBallon.move;
            int idx = curBallon.idx;

            sb.append(idx).append(" ");
            list.remove(cur_idx);

            if(list.size() == 0) break;

            //cur_idx + (move % list.size() - 1);
            if(move < 0) {
                cur_idx = cur_idx + (move % list.size());
            } else {
                cur_idx = cur_idx + (move % list.size()) - 1;
            }
            if(cur_idx >= list.size()) {
                cur_idx %= list.size();
            } else if(cur_idx < 0) {
                cur_idx = list.size() + cur_idx;
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
