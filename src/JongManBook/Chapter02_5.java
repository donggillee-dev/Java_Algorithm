package JongManBook;

import java.io.*;

public class Chapter02_5 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] A = {-6, -5, -4, -2, -1, 0, 1, 5, 6, 7, 10, 16};
        int x = Integer.parseInt(br.readLine());
        int ans = BinarySearch(A, x);

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static int BinarySearch(int [] A, int val) {
        int n = A.length - 1, lo = 0, hi = n;

        while(lo + 1 < hi) {
            System.out.println(lo + " " + hi);
            int mid = (lo + hi) / 2;
            if(mid < val)
                lo = mid;
            else
                hi = mid;
        }
        return hi;
    }
}
