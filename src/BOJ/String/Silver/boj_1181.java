package BOJ.String.Silver;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
//Logic
//중복을 제외해야하므로 Set으로 데이터들을 넣고 정렬하여 iterator로 출력

public class boj_1181 {
    public static void main(String[] args) throws IOException {
        TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }

                return o1.length() - o2.length();
            }
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        while(N-->0) {
            set.add(br.readLine());
        }

        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            bw.write(iter.next().toString());
            bw.newLine();
        }
        bw.flush();
    }
}
