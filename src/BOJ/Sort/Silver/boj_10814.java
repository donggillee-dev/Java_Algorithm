package BOJ.Sort.Silver;
import java.io.*;
import java.util.*;

public class boj_10814 {
    private static class Info implements Comparable<Info> {
        int age;
        int cnt;
        String name;

        public Info(int age, int cnt, String name) {
            this.age = age;
            this.cnt = cnt;
            this.name = name;
        }

        @Override
        public int compareTo(Info obj) {
            if(this.age == obj.age) return this.cnt - obj.cnt;

            return this.age - obj.age;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        ArrayList<Info> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        while(n --> 0) {
            stk = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(stk.nextToken());
            String name = stk.nextToken();
            cnt++;

            list.add(new Info(age, cnt, name));
        }

        Collections.sort(list);

        for(Info elem : list) {
            sb.append(elem.age).append(" ").append(elem.name).append("\n");
        }

        System.out.print(sb);
    }
}
