package BOJ.IMP.Gold;

//3아웃이면 이닝 끝남
//타순이 정해져있고 이 타순은 순환
//이전 이닝에서 6번쨰 타자까지 쳤다면 그 다음 공격때 7번째 타자부터
//안타 : 타자와 모든 주자 1루 전진 (1)
//2루타 : 타자와 모든 주자 2루 전진 (2)
//3루타 : 타자와 모든 주자가 3루 전진 (3)
//홈런 : 타자와 모든 주자 홈으로 인 (4)
//아웃 : 주자는 진루 못하고 아웃 1증가 (0)

//1번 선수를 4번 타자로 지정
//각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 암

//선수들의 순서를 구하는 dfs구현
//dfs 완료시 모든 이닝을 돌아보는 구현부분 필요

import java.io.*;
import java.util.*;

public class boj_17281 {
	private static int N, ans = -987654321;
	private static int[][] infoArr;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static int getScore(int[] order) {
		int score = 0;
		
		for(int inning = 0; inning < N; inning++) {
			int strikeCnt = 0, curOrder = 0;
			
			while(strikeCnt < 3) {
				
				curOrder = (curOrder + 1) % 9;
			}
		}
		
		return score;
	}
	
	private static void dfs(int depth, boolean[] visited, int[] order) {
		if(depth >= 9) {
			ans = Math.max(ans, getScore(order));
			return;
		}
		
		if(depth == 3) {
			dfs(depth + 1, visited, order);
		} else {
			for(int i = 0; i < 9; i++) {
				if(!visited[i]) {
					visited[i] = true;
					order[depth] = i;
					dfs(depth + 1, visited, order);
					order[depth] = -1;
					visited[i] = false;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		N = stoi(br.readLine());
		infoArr = new int[N][9];
		int[] order = {-1, -1, -1, 0, -1, -1, -1, -1, -1};
		boolean[] visited = {true, false, false, false, false, false, false, false, false};
		
		for(int inning = 0; inning < N; inning++) {
			stk = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < 9; idx++) {
				infoArr[inning][idx] = stoi(stk.nextToken());
			}
		}
		
		dfs(0, visited, order);
	}
}
