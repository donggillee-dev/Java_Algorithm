package BOJ.Sort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//볼을 무게순으로 정렬하여 가장 큰 볼부터 누적합된 무게에 대해서 처리, color동일한 것들에 대해서도 처리
//그 후에 동일한 무게를 처리해준다 => 동일한 무게이면서 컬러가 다른 경우에 대해서만 기존 정답 값에서 빼주어야함
//왜냐하면 누적합에 대해 처리해줄때 동일한 color들의 볼들에 대해서는 예외를 이미 처리해줬기 때문

public class boj_10800 {
    private static class Info {
        int idx, color, weight;

        public Info(int idx, int color, int weight) {
            this.idx = idx;
            this.color = color;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine()), tot_weight = 0;
        int[] ans_arr = new int[n + 1];
        int[] color_arr = new int[n + 1];
        ArrayList<Info> ballList = new ArrayList<>();

        //각 컬러 번호를 인덱스로 이용해서 컬러별 무게 합과 총합을 더해놓는다.
        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());

            color_arr[color] += weight;
            tot_weight += weight;
            ballList.add(new Info(i, color, weight));
        }

        //공들을 무게 순으로 정렬하여준다
        Collections.sort(ballList, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.weight - o1.weight;
            }
        });

        //각 볼별로 먹을 수 있는 공들을 다 더해줌 => 하지만 자신과 같은 사이즈의 볼도 더해줌
        for(Info ball : ballList) {
            tot_weight -= ball.weight;
            color_arr[ball.color] -= ball.weight;

            int ans = tot_weight - color_arr[ball.color];

            ans_arr[ball.idx] = ans;
        }

        //ballList는 무게순으로 정렬되어있으므로 자신과 같은 무게를 갖는 볼에 대해서 현재 값에 대해서 빼줌, 대신에 컬러가 달라야함
        for(int i = 0; i < n - 1; i++) {
            Info ball = ballList.get(i);
            for(int j = i + 1; j < n; j++) {
                Info targetBall = ballList.get(j);
                if(targetBall.weight != ball.weight) break;
                else if(targetBall.color != ball.color){
                    ans_arr[ball.idx] -= targetBall.weight;
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            sb.append(ans_arr[i]).append("\n");
        }
        System.out.print(sb);
    }
}
