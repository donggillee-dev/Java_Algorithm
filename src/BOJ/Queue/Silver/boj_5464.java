package BOJ.Queue.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//자료구조 놓은지 오래돼서 정말 헤맨 문제
//풀이 시간 : 40분
public class boj_5464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //주자창번호 담을 우선순위큐
        Queue<Integer> q = new LinkedList<>(); //명령어 담아있는 큐
        Queue<Integer> wait = new LinkedList<>(); //주차장 들어가는거 대기하는 큐
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int ans = 0;
        int[] park = new int[n + 1]; //각 주차장별
        int[] pos = new int[m + 1]; //해당 차가 몊번째 주차장에 들어가는지 번호
        int[] car = new int[m + 1]; //차의 무게 배열

        for(int i = 1; i <= n; i++) {
            park[i] = Integer.parseInt(br.readLine());
            pq.add(i);
        }

        for(int i = 1; i <= m; i++) {
            car[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < 2 * m; i++) { //명령어 큐에 모든 차의 input/output 명령 순차적으로 넣어줌
            int num = Integer.parseInt(br.readLine());
            q.add(num);
        }

        while(!q.isEmpty()) { //명령큐 순회
            int num = q.poll();
            if(num < 0) { //차가 빠지는 경우
                ans += ( car[-num] * park[pos[-num]] ); //빼면서 요금 정산
                pq.add(pos[-num]); //남은 주차공간 복원

                if(!wait.isEmpty()) { //뺌과 동시에 대기 큐에 존재하는 차가 있는지 확인 후 처리
                    int loc = pq.poll();
                    pos[wait.poll()] = loc;
                }
            } else { //차가 들어오려는 경우
                if(pq.isEmpty()) { //비어있는 주차공간이 없다면
                    wait.add(num); //해당 차 대기큐에 넣어줌
                } else { //그렇지 않다면 해당 공간에 주차
                    int loc = pq.poll();
                    pos[num] = loc;
                }
            }
        }

        System.out.println(ans);
    }
}
