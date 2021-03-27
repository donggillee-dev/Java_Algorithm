package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//투 포인터를 사용해서 한쪽은 왼쪽에서 시작, 다른 한쪽은 오른쪽에서 시작, 그리고 합의 절대값을 이용해서 적절한 용액을 선택
//그 중에 합이 0이 되는 두 용액이 존재하면 더이상 탐색할 필요가 없으므로 break

public class boj_2467 {
    private static int abs(int num) {
        return num < 0 ? -num : num;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int left = 0;
        int diff = Integer.MAX_VALUE;
        int answer_left = arr[left], answer_right = arr[N - 1];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        N--;
        while(left != N) {
            int sum = arr[N] + arr[left];

            if(abs(sum) < diff) {
                answer_left = arr[left];
                answer_right = arr[N];
                diff = abs(sum);
            }

            if(sum < 0) {
                left++;
            } else if(sum > 0){
                N--;
            } else {
                answer_left = arr[left];
                answer_right = arr[N];
                break;
            }
        }

        System.out.printf("%d %d", answer_left, answer_right);
    }
}
