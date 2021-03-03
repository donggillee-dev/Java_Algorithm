package BOJ.Queue.Gold;

import java.io.*;
import java.util.*;
//Logic

//오름차순, 내림차순 큐를 가지고 hash에다가 각 값의 키를 가지고 갯수를 카운팅해서 존재하는지 아닌지 처리, => 큐의 맨 뒤를 처리해주는 느낌
public class boj_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());

        while(T --> 0) {
            PriorityQueue<Integer> Q1 = new PriorityQueue<>();
            PriorityQueue<Integer> Q2 = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> Map = new HashMap<>();
            int K = Integer.parseInt(br.readLine());
            StringTokenizer stk;
            while(K --> 0) {
                stk = new StringTokenizer(br.readLine());
                char ch = stk.nextToken().charAt(0);
                int num = Integer.parseInt(stk.nextToken());

                if(ch == 'I') {
                    Map.put(num, Map.getOrDefault(num, 0) + 1);
                    Q1.add(num);
                    Q2.add(num);
                } else {
                    PriorityQueue<Integer> pointer;
                    if(num == -1) {
                        pointer = Q1;
                    } else {
                        pointer = Q2;
                    }
                    if(pointer.size() == 0) continue;
                    remover(Map, pointer);
                }
            }

            if(Map.size() == 0) {
                sb.append("EMPTY").append("\n");
            } else {
                int max = remover(Map, Q2);
                int min = max;
                if(Map.size() > 0) min = remover(Map, Q1);
                sb.append(max).append(" ").append(min).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int remover(HashMap<Integer, Integer> map, PriorityQueue<Integer> Q) {
        int num = 0;
        while(!Q.isEmpty()) {
            num = Q.poll();

            if(map.get(num) == null) continue;

            if(map.get(num) == 0) {
                map.remove(num);
                continue;
            }

            if(map.get(num) > 0) {
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0) map.remove(num);
                break;
            }
        }
        return num;
    }
}
