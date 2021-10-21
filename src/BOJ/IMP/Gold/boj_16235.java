package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//r, c는 1부터 시작
//각 칸의 초기 양분은 다 5씩
//1. 봄 (pq로 처리)
//	1-1.나무는 자신의 나이만큼 양분 먹고 나이 1 증가(자신이 있는 1x1 칸의 양분만 먹을 수 있음)
//	1-2. 하나의 칸에 여러 나무가 있으면 나이가 어린애부터 머금
//	1-3. 만약 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 먹지 못하고 즉사
//2. 여름 (위의 봄 로직에서 pq순회 다하고 죽은애들 값을 합해서 / 2하고 더해준다
//	2-1. 봄에 죽은 나무가 양분으로 변함
//	2-2. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가(소수점 아래는 버림)
//3. 가을 (그냥 순차탐색하면서 하면 됨)
//	3-1. 나무가 번식. 나이가 5의 배수인 나무들만 번식
//	3-2. 인접한 8개의 칸에 나이가 1인 나무가 생김
//4. 겨울
//	4-1. S2D2가 돌아다니면서 땅에 양분 추가 -> 입력으로 주어

// 문제대로 구현하자
// 죽은 나무들의 나이 합 / 2가 아니라 그때그때 죽은 나무들의 나이 / 2 해야함

public class boj_16235 {
	private static int totalCnt;
	private static int N, M, K;
	private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}

	private static boolean isRange(int row, int col) {
		if(row < 1 || col < 1 || row > N || col > N) return false;
		return true;
	}
	
	private static void initData(int[][] map, int[][] A, Deque<Integer>[][] treeMap, BufferedReader br) throws IOException {
		StringTokenizer stk;
		
		for(int row = 1; row <= N; row++) {
			stk = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; col++) {
				map[row][col] = 5;
				A[row][col] = stoi(stk.nextToken());
				treeMap[row][col] = new LinkedList<>();
			}
		}
	}
	
	private static void process(int[][] map, int[][] A, Deque<Integer>[][] treeMap) {
		//Spring & Summer & Winter
		for(int row = 1; row <= N; row++) {
			for(int col = 1; col <= N; col++) {
				int deadSum = 0;
				int size = treeMap[row][col].size();
				
				while(size --> 0) {
					int age = treeMap[row][col].pollFirst();
					
					//if can eat
					if(age <= map[row][col]) {
						map[row][col] -= age;
						age++;
						treeMap[row][col].addLast(age);
					} else { //cannot eat it will die
						deadSum += (age / 2);
						totalCnt--;
					}
				}
				
				map[row][col] += A[row][col];
				map[row][col] += deadSum;
			}
		}
		
		//Fall
		for(int row = 1; row <= N; row++) {
			for(int col = 1; col <= N; col++) {
				for(Integer age : treeMap[row][col]) {
					if(age % 5 == 0) {
						for(int dir = 0; dir < 8; dir++) {
							int nr = row + dr[dir];
							int nc = col + dc[dir];
							
							if(isRange(nr, nc)) {
								treeMap[nr][nc].addFirst(1);
								totalCnt++;
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = stoi(stk.nextToken());
		totalCnt = M = stoi(stk.nextToken());
		K = stoi(stk.nextToken());
		int[][] map = new int[N + 1][N + 1];
		int[][] A = new int[N + 1][N + 1];
		Deque<Integer>[][] treeMap = new Deque[N + 1][N + 1];
		
		initData(map, A, treeMap, br); //input A & init Data
		
		//input tree data
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = stoi(stk.nextToken());
			int y = stoi(stk.nextToken());
			int z = stoi(stk.nextToken());
			
			treeMap[x][y].add(z);
		}
		
		while(K --> 0) {
			process(map, A, treeMap);
			
			if(totalCnt == 0) break;
		}
		
		System.out.println(totalCnt);
	}
}