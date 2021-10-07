package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//next -> 아무 일도 안 일어남
//acquire n -> n 자원카드를 가져오려고 한다
//만약 공용 공간에 있으면 가져온다
//다른 사람의 공간에 있는 경우에는 이 카드 안버리고 다음 턴에 또 사용
//release n -> n 자원카드를 공용 공간에 반납

//규칙
//모든 연산,자원 카드들은 공용 공간에 존재
//각 턴을 수행하는 사람은 1명
//자신의 턴에 다음과 같이 행동
//연산 카드 없는 경우 -> 더미의 맨 위에 있는 연산 카드를 뽑고 해당 카드에 있는 연산 즉시 수행
//연산 카드 있는 경우 -> 들고 있는 연산 카드의 연산을 즉시 수행

//자원의 크기가 크므로 배열보다는 Set, HashMap을 사용하는 것이 좋다

public class boj_21775 {
    private static class Card {
        String oper;
        int id, n;

        public Card(int id, String oper) {
            this.id = id;
            this.oper = oper;
        }

        public Card(int id, String oper, int n) {
            this.id = id;
            this.oper = oper;
            this.n = n;
        }
    }

    private static String simulation(Queue<Card>[] perQ, Queue<Integer> turnQ, Queue<Card> operQ) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Boolean> usingMap = new HashMap<>();

        while (!turnQ.isEmpty()) {
            int num = turnQ.poll();

            if (perQ[num].isEmpty()) {
                Card cd = operQ.poll();

                sb.append(cd.id).append("\n");

                if(cd.oper.equals("acquire")) {
                    if(usingMap.get(cd.n) == null || usingMap.get(cd.n) == false) {
                        usingMap.put(cd.n, true);
                    } else {
                        perQ[num].add(cd);
                    }
                } else if(cd.oper.equals("release")) {
                    usingMap.put(cd.n, false);
                }
            } else {
                int id = perQ[num].peek().id;
                int n = perQ[num].peek().n;

                if(!usingMap.get(n)) {
                    usingMap.put(n, true);
                    perQ[num].poll();
                }

                sb.append(id).append("\n");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()), T = Integer.parseInt(stk.nextToken());
        Queue<Card>[] perQ = new LinkedList[N + 1];
        Queue<Integer> turnQ = new LinkedList<>();
        Queue<Card> operQ = new LinkedList<>();

        stk = new StringTokenizer(br.readLine());

        while (stk.hasMoreTokens()) {
            turnQ.add(Integer.parseInt(stk.nextToken()));
        }

        for (int i = 1; i <= N; i++) {
            perQ[i] = new LinkedList<>();
        }

        for (int i = 0; i < T; i++) {
            stk = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(stk.nextToken());
            String op = stk.nextToken();

            if (op.equals("next")) {
                operQ.add(new Card(id, "next"));
            } else {
                int n = Integer.parseInt(stk.nextToken());
                operQ.add(new Card(id, op, n));
            }
        }

        System.out.println(simulation(perQ, turnQ, operQ));
    }
}
