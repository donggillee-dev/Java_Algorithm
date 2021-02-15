package BOJ.Queue.Silver;

import java.io.*;
import java.util.LinkedList;

public class boj_10845 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> list = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        String input;
        int num, size = 0;

        while(N-->0) {
            input = br.readLine();

            switch(input.charAt(0)) {
                case 'p':
                    if(input.charAt(1) == 'u') {
                        num = Integer.parseInt(input.substring(5));
                        list.add(num);
                        size++;
                    } else {
                        if(size == 0) {
                            sb.append(-1).append("\n");
                        } else {
                            sb.append(list.get(0)).append("\n");
                            list.remove(0);
                            size--;
                        }
                    }
                    break;
                case 's':
                    sb.append(size).append("\n");
                    break;
                case 'e':
                    if(size == 0) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                    sb.append("\n");
                    break;
                case 'f':
                    if(size == 0) {
                        sb.append(-1);
                    } else {
                        sb.append(list.get(0));
                    }
                    sb.append("\n");
                    break;
                case 'b':
                    if(size == 0) {
                        sb.append(-1);
                    } else {
                        sb.append(list.get(size - 1));
                    }
                    sb.append("\n");
                    break;
            }
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
