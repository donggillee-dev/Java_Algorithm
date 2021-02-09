package SWEA.D4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ValidityCheck {
    private static final int TestCase = 10;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer stk;
            ArrayList<String> list;
            boolean isPossible = true;
            for(int i = 0; i < N; i++) {
                list = new ArrayList<>();
                stk = new StringTokenizer(br.readLine());
                while(stk.hasMoreTokens()) list.add(stk.nextToken());
                if(!isPossible) continue;

                String root = list.get(1);
                if(list.size() == 2) {
                    if (root.equals("+") || root.equals("-") || root.equals("*") || root.equals("/")) {
                        isPossible = false;
                    }
                } else if(list.size() == 4) {
                    if(!(root.equals("+") || root.equals("-") || root.equals("*") || root.equals("/"))) {
                        isPossible = false;
                    }
                } else if(list.size() == 3) {
                    isPossible = false;
                }
            }
            sb.append("#"+tc+" ");
            if(isPossible) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
