package Binary_Search;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        ArrayList<Integer> Ans = new ArrayList<>();

        Ans.add(0);

        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(stk.nextToken());

            if(value > Ans.get(Ans.size() - 1)) Ans.add(value);
            else {
                int start = 0;
                int end = Ans.size() - 1;
                while(start < end) {
                    int mid = (start + end) / 2;

                    if(value <= Ans.get(mid)) {
                        end = mid;
                    } else {
                        start = mid + 1;
                    }
                }
                Ans.set(end, value);
            }
        }
        sb.append(Ans.size() - 1).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

