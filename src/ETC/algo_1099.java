package ETC;
import java.io.*;
import java.util.*;

public class algo_1099 {
    static int N, ans = 0;
    static String Strange;
    static String[] Arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int S_idx= 0;
        Strange = br.readLine();
        N = Integer.parseInt(br.readLine());
        Arr = new String[N];

        for(int i = 0; i < N; i++) {
            Arr[i] = br.readLine();
        }
        while(S_idx < Strange.length()) {
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < N; i++) {
                int length = Arr[i].length();
                int tmp = S_idx;
                int start_idx = 0;
                int end_idx = 0;
                for(int j = 0; j < length; j++) {
                    if(Strange.charAt(tmp) == Arr[i].charAt(j)) {
                        start_idx = j;
                        tmp++;
                        break;
                    }
                }
                for(int j = start_idx + 1; j < length; j++) {
                    if(Strange.charAt(tmp) != Arr[i].charAt(j)) {
                        end_idx = j;
                    } else tmp++;
                }
                if(max < (end_idx - start_idx)) max = (end_idx - start_idx);
            }
            S_idx += max;
            ans++;
        }
        System.out.println(ans);
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
