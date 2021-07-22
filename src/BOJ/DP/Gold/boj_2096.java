package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//dp를 이용해서 간단히 풀 수 있는 문제
//해당 위치의 값은 이전값까지의 최선의 선택에 영향을 받기에 최소, 최대 배열을 만들어서 점화식 생성
//블로그에서 더 효율적인 메모리 사용을 보여주는 풀이를 보게됨
//맨 아래 주석처리 되어있음
//풀이 시간 : 16분

public class boj_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] min = new int[n][3];
        int[][] max = new int[n][3];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
                if(i == 0) {
                    min[i][j] = arr[i][j];
                    max[i][j] = arr[i][j];
                }
            }
        }

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < 3; j++) {
                int num = arr[i + 1][j];
                if(j == 0) {
                    min[i + 1][j] = Math.min(min[i][0] + num, min[i][1] + num);
                    max[i + 1][j] = Math.max(max[i][0] + num, max[i][1] + num);
                } else if(j == 1) {
                    min[i + 1][j] = Math.min(min[i][0] + num, Math.min(min[i][1] + num, min[i][2] + num));
                    max[i + 1][j] = Math.max(max[i][0] + num, Math.max(max[i][1] + num, max[i][2] + num));
                } else {
                    min[i + 1][j] = Math.min(min[i][1] + num, min[i][2] + num);
                    max[i + 1][j] = Math.max(max[i][1] + num, max[i][2] + num);
                }
            }
        }
        int maxAns = -987654321;
        int minAns = 987654321;
        for(int i = 0; i < 3; i++) {
            if(min[n - 1][i] < minAns) minAns = min[n - 1][i];
            if(max[n - 1][i] > maxAns) maxAns = max[n - 1][i];
        }

        System.out.println(maxAns + " " + minAns);
    }
}

/*
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] maxDp = new int[3];
		int[] minDp = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());

			if (i == 0) {
				maxDp[0] = minDp[0] = x1;
				maxDp[1] = minDp[1] = x2;
				maxDp[2] = minDp[2] = x3;
			} else {
				// 최댓값을 구하는 dp 배열
				int beforeMaxDp_0 = maxDp[0], beforeMaxDp_2 = maxDp[2];
				maxDp[0] = Math.max(maxDp[0], maxDp[1]) + x1;
				maxDp[2] = Math.max(maxDp[1], maxDp[2]) + x3;
				maxDp[1] = Math.max(Math.max(beforeMaxDp_0, maxDp[1]), beforeMaxDp_2) + x2;

				// 최솟값을 구하는 dp 배열
				int beforeMinDp_0 = minDp[0], beforeMinDp_2 = minDp[2];
				minDp[0] = Math.min(minDp[0], minDp[1]) + x1;
				minDp[2] = Math.min(minDp[1], minDp[2]) + x3;
				minDp[1] = Math.min(Math.min(beforeMinDp_0, minDp[1]), beforeMinDp_2) + x2;
			}
		}

		bw.write(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])) + " ");
		bw.write(Math.min(minDp[0], Math.min(minDp[1], minDp[2])) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
 */