package BOJ.BinarySearch.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//(a, b), (c, d)이렇게 두 그룹으로 묶어서 각 그룹별 나올 수 있는 무게의 합 경우의 수를 다 구하고
//이 두 그룹을 가지고 도출하려는 값의 조건에 맞을때까지 이분 탐색 하면 된다

public class boj_9007 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(stk.nextToken());
            int n = Integer.parseInt(stk.nextToken());
            int[] ab = new int[n * n];
            int[] cd = new int[n * n];
            int[][] arr = new int[4][n];

            for(int i = 0; i < 4; i++) {
                stk = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    ab[i * n + j] = arr[0][i] + arr[1][j];
                    cd[i * n + j] = arr[2][i] + arr[3][j];
                }
            }

            Arrays.sort(ab);
            Arrays.sort(cd);

            int answer = 987654321;
            loop:for(int i = 0; i < n * n; i++) {
                int left = 0, right = n * n - 1;
                int tmp_ans = 987654321;

                while(left <= right) {
                    int mid = (left + right) / 2;
                    int weight = ab[i] + cd[mid];

                    if(k == weight) {
                        answer = weight;
                        break loop;
                    } else if(k < weight) {
                        right = mid - 1;
                    } else if(k > weight) {
                        left = mid + 1;
                    }

                    if(Math.abs(tmp_ans - k) > Math.abs(weight - k)) {
                        tmp_ans = weight;
                    } else if(Math.abs(tmp_ans - k) == Math.abs(weight - k)) {
                        tmp_ans = Math.min(tmp_ans, weight);
                    }
                }

                if(Math.abs(tmp_ans - k) < Math.abs(answer - k)) {
                    answer = tmp_ans;
                } else if(Math.abs(tmp_ans - k) == Math.abs(answer - k)) {
                    answer = Math.min(answer, tmp_ans);
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
