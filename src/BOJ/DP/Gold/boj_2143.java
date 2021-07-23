package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//음수의 부반합이 존재할 수 있으므로 부분합의 값을 인덱싱으로 사용하는 방법은 안됨 => ArrayIndexOutOfBound
//그렇다면 각 부분합을 배열의 인덱스로 접근하지말고 어레이리스트에 다 저장해놓고 투 포인터를 이용해서 해보자

//접근 방식은 좋았으나 인덱싱에 대해서 생각이 부족해 많이 틀린 문제

//풀이 시간 : 45분

public class boj_2143 {
    //각 배열의 나올 수 있는 부분합을 다 구해서 오름차순으로 정렬해 ArrayList 반환
    private static ArrayList<Integer> getPartSum(int[] arr, int size) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= size - 1; i++) {
            for (int j = i + 1; j <= size; j++) {
                pq.add(arr[j] - arr[i]);
            }
        }

        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }

        return list;
    }

    //두 배열의 나올 수 있는 부분합 경우의 수를 다 구해서 => 부분합 값
    //두 배열의 부분합에 대해 투 포인터를 사용해 합이 t와 같아지는지, 커지는지, 작아지는지에 따라 포인터 이동
    //같으면 경우의 수 구하는 거니까 a의 값개수 * b의 값개수를 더해준다
    private static long sol(int t, ArrayList<Integer> sumA, ArrayList<Integer> sumB) {
        int aSize = sumA.size();
        int bSize = sumB.size();
        int pointA = 0;
        int pointB = bSize - 1;
        long ans = 0;

        while (pointA < aSize && pointB >= 0) {
            int numA = sumA.get(pointA);
            int numB = sumB.get(pointB);

            if (numA + numB == t) { //두 값의 합이 같은 경우
                long cntA = 0, cntB = 0;

                while (pointA < aSize && numA == sumA.get(pointA)) { //중복값이 존재하기에 갯수 세어주어야함
                    pointA++;
                    cntA++;
                }

                while (pointB >= 0 && numB == sumB.get(pointB)) {
                    pointB--;
                    cntB++;
                }
                ans += cntA * cntB;
            } else if (numA + numB > t) { //크면 b를 낮춘다
                pointB--;
            } else {//작으면 a를 놏인다
                pointA++;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine()), m = 0;
        int[] A = new int[n + 1], B;
        ArrayList<Integer> sumA, sumB;

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(stk.nextToken());

            A[i] += A[i - 1];
        }

        m = Integer.parseInt(br.readLine());
        B = new int[m + 1];
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = Integer.parseInt(stk.nextToken());

            B[i] += B[i - 1];
        }

        sumA = getPartSum(A, n);
        sumB = getPartSum(B, m);


        System.out.println(sol(t, sumA, sumB));
    }
}
