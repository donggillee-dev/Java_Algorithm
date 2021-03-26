package BOJ.LIS.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//Logic
//전형적인 LIS문제이다
//이분탐색을 추가시켜 O(NlogN)으로 시간복잡도를 줄이고
//역 추적을 위해 idx, 즉 최장 증가 수열의 길이를 역추적 배열에 그때그때 넣어준다
//최종적으로 만들어진 역추적 배열을 끝에서부터 탐색해서 값을 추적해 스택으로 뒤집어서 출력해주면 되는 문제

public class boj_14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine()), idx = 0;
        int[] arr = new int[N], traceIdx = new int[N], lis = new int[N];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            traceIdx[i] = i;
        }

        lis[idx++] = arr[0];

        for(int i = 1; i < N; i++) {
            if(lis[idx - 1] < arr[i]) {
                traceIdx[i] = idx;
                lis[idx++] = arr[i];
            } else {
                int left = 0, right = idx - 1;

                while(left <= right) {
                    int mid = (left + right) / 2;

                    if(lis[mid] < arr[i]) left = mid + 1;
                    else right = mid - 1;
                }
                traceIdx[i] = left;
                lis[left] = arr[i];
            }
        }
        sb.append(idx).append("\n");

        int tmp = idx - 1;
        Stack<Integer> S = new Stack<>();

        for(int i = N - 1; i >= 0; i--) {
            if(tmp < 0) break;
            if(traceIdx[i] == tmp) {
                S.push(arr[i]);
                tmp--;
            }
        }

        while(!S.isEmpty()) {
            sb.append(S.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
