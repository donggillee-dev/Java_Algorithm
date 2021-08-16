package BOJ.Hash.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

//Logic
//HashMap을 이용하여 풀 수 있는 문제
//다만 백분율을 소수점 넷째자리까지 무조건 출력해야하기때문에 String.format을 사용해야함

//풀이 시간 : 20분

public class boj_4358 {
    private static class Info implements Comparable<Info> {
        String name;
        double per;

        public Info(String name, double per) {
            this.name = name;
            this.per = per;
        }

        @Override
        public int compareTo(Info o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> hash = new HashMap<>();
        PriorityQueue<Info> pq = new PriorityQueue<>();

        int cnt = 0;

        while (true) {
            String str = br.readLine();

            if (str == null || str.length() == 0) break;

            hash.put(str, hash.getOrDefault(str, 0) + 1);
            cnt++;
        }

        for(String key : hash.keySet()) {
            double per = Math.round(((double)hash.get(key) / cnt) * 100 * 10000);
            per /= 10000;

            pq.add(new Info(key, per));
        }

        while(!pq.isEmpty()) {
            Info elem = pq.poll();

            sb.append(elem.name + " " + String.format("%.4f",elem.per)).append("\n");
        }

        System.out.print(sb);
    }
}
