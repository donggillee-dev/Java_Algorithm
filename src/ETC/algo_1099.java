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
        Strange = br.readLine();
        N = Integer.parseInt(br.readLine());
        Arr = new String[N];

        for(int i = 0; i < N; i++) {
            Arr[i] = br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
