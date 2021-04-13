package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//Logic
//너무 어렵게 풀었던 문제...
//사실상 어차피 가장 높은 카드 기준으로 다 레벨이 변경될 것이기때문에 정렬해서 O(N)으로 순차적으로 바꿔주면 됨

//풀이 시간 : 50분
public class boj_12845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        boolean[] isUpgrade = new boolean[n];
        stk = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            pq.add(new int[]{arr[i], i});
        }

        int gold = 0;

        while (!pq.isEmpty()) {
            int[] inf = pq.poll();
            int idx = inf[1];

            if (isUpgrade[idx]) {
                if (idx == 0) {
                    if (!isUpgrade[1]) {
                        gold += arr[idx];
                        gold += arr[idx + 1];
                        arr[idx + 1] = arr[idx];
                        isUpgrade[idx + 1] = true;
                        pq.add(new int[]{arr[idx + 1], idx + 1});
                    }
                } else if (idx == n - 1) {
                    if (!isUpgrade[idx - 1]) {
                        gold += arr[idx];
                        gold += arr[idx - 1];
                        arr[idx - 1] = arr[idx];
                        isUpgrade[idx - 1] = true;
                        pq.add(new int[]{arr[idx - 1], idx - 1});
                    }
                } else {
                    if (!isUpgrade[idx + 1] && !isUpgrade[idx - 1]) {
                        if (arr[idx + 1] < arr[idx - 1]) {
                            gold += arr[idx];
                            gold += arr[idx - 1];
                            arr[idx - 1] = arr[idx];
                            isUpgrade[idx - 1] = true;
                            pq.add(new int[]{arr[idx + 1], idx + 1});

                        } else {
                            gold += arr[idx];
                            gold += arr[idx + 1];
                            arr[idx + 1] = arr[idx];
                            isUpgrade[idx + 1] = true;
                            pq.add(new int[]{arr[idx + 1], idx + 1});
                        }
                    } else if (!isUpgrade[idx + 1]) {
                        gold += arr[idx];
                        gold += arr[idx + 1];
                        arr[idx + 1] = arr[idx];
                        isUpgrade[idx + 1] = true;
                        pq.add(new int[]{arr[idx + 1], idx + 1});
                    } else if (!isUpgrade[idx - 1]) {
                        gold += arr[idx];
                        gold += arr[idx - 1];
                        arr[idx - 1] = arr[idx];
                        isUpgrade[idx - 1] = true;
                        pq.add(new int[]{arr[idx - 1], idx - 1});
                    }
                }
            } else {
                isUpgrade[idx] = true;
                if (idx == 0) {
                    if(!isUpgrade[1]) {
                        gold += arr[idx];
                        gold += arr[idx + 1];
                        arr[idx + 1] = arr[idx];
                        isUpgrade[idx + 1] = true;
                        pq.add(new int[]{arr[idx + 1], idx + 1});
                    }
                } else if (idx == n - 1) {
                    if(isUpgrade[n - 2]) {
                        gold += arr[idx];
                        gold += arr[idx - 1];
                        arr[idx - 1] = arr[idx];
                        isUpgrade[idx - 1] = true;
                        pq.add(new int[]{arr[idx - 1], idx - 1});
                    }
                } else {
                    if (!isUpgrade[idx + 1] && !isUpgrade[idx - 1]) {
                        if (arr[idx + 1] < arr[idx - 1]) {
                            gold += arr[idx];
                            gold += arr[idx - 1];
                            arr[idx - 1] = arr[idx];
                            isUpgrade[idx - 1] = true;
                            pq.add(new int[]{arr[idx - 1], idx - 1});
                        } else {
                            gold += arr[idx];
                            gold += arr[idx + 1];
                            arr[idx + 1] = arr[idx];
                            isUpgrade[idx + 1] = true;
                            pq.add(new int[]{arr[idx + 1], idx + 1});
                        }
                    } else if (!isUpgrade[idx + 1]) {
                        gold += arr[idx];
                        gold += arr[idx + 1];
                        arr[idx + 1] = arr[idx];
                        isUpgrade[idx + 1] = true;
                        pq.add(new int[]{arr[idx + 1], idx + 1});
                    } else if (!isUpgrade[idx - 1]) {
                        gold += arr[idx];
                        gold += arr[idx - 1];
                        arr[idx - 1] = arr[idx];
                        isUpgrade[idx - 1] = true;
                        pq.add(new int[]{arr[idx - 1], idx - 1});
                    }
                }
                pq.add(new int[]{arr[idx], idx});
            }
        }
        System.out.println(gold);
    }
}
