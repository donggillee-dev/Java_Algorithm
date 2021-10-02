package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

//Logic
//처음에는 그때그때 다음 주유소가 가격이 더 비싸면 다음꺼만 처리하는 방식 채택
//힌트를 얻어보니 현재 주유소 가격보다 더 싼 주유소 나오기 전까지의 간선은
//현재 주유소에서 다 처리하는게 확실한 정답

public class boj_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        Queue<Integer> edge = new LinkedList<>();
        Queue<Integer> vertex = new LinkedList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n - 1; i++) {
            vertex.add(Integer.parseInt(stk.nextToken()));
        }

        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            edge.add(Integer.parseInt(stk.nextToken()));
        }

        int cur = edge.poll();
        long verSum = vertex.poll();

        while(!edge.isEmpty()) {
            if(edge.peek() >= cur) {
                if(edge.size() == 1) {
                    sum += verSum * cur;
                } else {
                    verSum += vertex.poll();
                }
                edge.poll();
            } else {
                sum += verSum * cur;
                cur = edge.poll();
                if(vertex.isEmpty()) {
                    verSum = 0;
                    break;
                }
                else verSum = vertex.poll();
            }
        }

        System.out.println(sum);
    }
}
