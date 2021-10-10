package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//사람 순서를 조합으로 만들고 그 순서에 대한 사람들의 수행하는 카드 번호를 매칭시켜준다
//매칭된 카드 순열을 가지고 돌려서 TreeSet에 저장
//TreeSet이기에 오름차순으로 자동 정렬됨

//다시 풀어야함

public class boj_21776 {
    private static int N, C;
    private static String[] cards;
    private static TreeSet<String> ansSet = new TreeSet<>();
    private static int[] personalCards;
    private static int[] personPerm, commandPerm;
    private static ArrayList<Integer>[] order;

    private static String print() {
        StringBuilder sb = new StringBuilder();

        for (String str : ansSet) {
            sb.append(str).append("\n");
        }

        return sb.toString();
    }

    private static void operation() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < C; i++) {
            int cur = commandPerm[i];

            String[] ops = cards[cur].split(",");
            for (int j = 0; j < ops.length; j++) {
                StringTokenizer stk = new StringTokenizer(ops[j]);
                if (stk.nextToken().equals("ADD")) {
                    sb.append(stk.nextToken());
                } else {
                    try {
                        sb.deleteCharAt(Integer.parseInt(stk.nextToken()));
                    } catch (Exception e) {
                        ansSet.add("ERROR");
                        return;
                    }
                }
            }
        }

        if (sb.length() == 0) {
            ansSet.add("EMPTY");
        } else {
            ansSet.add(sb.toString());
        }
    }

    private static void permutation(int personIdx, int cardCnt) {
        if (personIdx == N + 1) {
            int[] cardIdx = new int[N + 1];

            for (int i = 0; i < C; i++) {
                int person = personPerm[i];
                commandPerm[i] = order[person].get(cardIdx[person]++);
            }

            operation();
            return;
        }

        if (personalCards[personIdx] == 0) {
            permutation(personIdx + 1, 0);
        } else {
            personalCards[personIdx]--;
            for (int i = cardCnt; i < C; i++) {
                if (personPerm[i] < 0) {
                    personPerm[i] = personIdx;
                    permutation(personIdx, i + 1);
                    personPerm[i] = -1;
                }
            }
            personalCards[personIdx]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        order = new ArrayList[N + 1];
        cards = new String[C + 1];
        personalCards = new int[N + 1];
        personPerm = new int[C + 1];
        commandPerm = new int[C];
        Arrays.fill(personPerm, -1);

        for (int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(stk.nextToken());
            personalCards[i] = cnt;

            order[i] = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                order[i].add(Integer.parseInt(stk.nextToken()));
            }
        }

        for (int i = 1; i <= C; i++) {
            cards[i] = br.readLine();
        }

        permutation(1, 0);

        System.out.println(print());
    }
}
