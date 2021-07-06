package BOJ.Queue.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//앞의 순회강연 문제와 비슷한 문제
//사용 가능한 pc를 while문을 통해 다 처리해줘야하는 것을 생각 못해서 오래걸림
//풀이 시간 : 45분

public class boj_12764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq1 = new PriorityQueue<>(new Comparator<int[]>() { //시작시간 오름차순 우선순위 큐
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> pq2 = new PriorityQueue<>(new Comparator<int[]>() { //끝나는시간 오름차순 우선순위 큐
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> seat = new PriorityQueue<>(); //남아있는 피씨자리 우선순위 큐
        int[] pc = new int[100000]; //각 피씨별 사용 횟수 배열
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) { //사용하는 인원들의 시작 시간, 종료 시간 우선순위 큐에 담아줌
            stk = new StringTokenizer(br.readLine());
            pq1.add(new int[]{Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())});
        }

        //pc 최대 대수 번호
        int num = 0;

        while(!pq1.isEmpty()) {
            int[] elem = pq1.poll();

            if(pq2.isEmpty()) {//초기에 아예 아무도 사용안하고 있을때 => pc 할당 받은 적 조차 없을때
                pq2.add(new int[]{elem[1], num});
                pc[num]++;
                num++;
            } else { //그 이후의 경우
                while(!pq2.isEmpty() && elem[0] > pq2.peek()[0]) { //사용하고 있는 사람들중에 현재 들어가려는 사람의 시작시간보다 끝시간이 빠른 사람들 처리 => 사용시간 종료처리
                    seat.add(pq2.peek()[1]);
                    pq2.poll();
                }

                if(seat.isEmpty()) { //사용 가능한 pc자리가 없다면
                    pq2.add(new int[]{elem[1], num});
                    pc[num]++;
                    num++;
                } else { //사용 가능한 pc자리가 있다면 뽑아와서 자리 배정
                    int tmp = seat.poll();
                    pc[tmp]++;
                    pq2.add(new int[]{elem[1], tmp});
                }
            }
        }

        sb.append(num).append("\n");

        for(int i = 0; i < num; i++) {
            sb.append(pc[i]).append(" ");
        }

        System.out.print(sb);
    }
}
