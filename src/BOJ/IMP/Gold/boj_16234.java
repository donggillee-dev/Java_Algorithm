package BOJ.IMP.Gold;

//Logic
//1. bfs를 이용해서 isOpen 배열을 조건에 맞게 true로 설정
//2. true로 설정된 연합에 대해서 인구수 계산해서 특정 큐에 넣어준다
//3. 큐를 뽑아서 해당 위치부터 bfs 돌면서 인구 세팅
//4. 다시 1로 돌아가

import java.io.*;
import java.util.*;

public class boj_16234 {
	private static class Pos {
		int row, col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class UnionPos extends Pos {
		int num;
		
		public UnionPos(int row, int col, int num) {
			super(row, col);
			this.num = num;
		}
	}
	
	private static class avgPop extends Pos {
		int population;
		
		public avgPop(int row, int col, int population) {
			super(row, col);
			this.population = population;
		}
	}
	
	private static int N, L, R;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, -1, 0, 1};
	
	private static boolean isRange(int row, int col) {
		if(row < 0 || col < 0 || row >= N || col >= N) return false;
		return true;
	}
	
	private static boolean openPossGates(int[][] map, int[][] isUnion) {
		boolean ret = false;
		
		int num = 0;
		
		boolean[][] visited = new boolean[N][N];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				boolean flag = false;
				if(isUnion[row][col] != 0) continue;
				for(int dir = 0; dir < 4; dir++) {
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					
					if(isRange(nr, nc)) {
						int diff = Math.abs(map[nr][nc] - map[row][col]);
						
						if(L <= diff && diff <= R) {
							ret = true;
							flag = true;
						}
					}
				}
				
				if(flag) {
					num++;
					Queue<UnionPos> unionQ = new LinkedList<>();
					visited[row][col] = true;
					isUnion[row][col] = num;
					unionQ.add(new UnionPos(row, col, num));
					
					while(!unionQ.isEmpty()) {
						UnionPos p = unionQ.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nr = p.row + dr[dir];
							int nc = p.col + dc[dir];
							
							if(isRange(nr, nc) && !visited[nr][nc]) {
								int diff = Math.abs(map[nr][nc] - map[p.row][p.col]);
								
								if(L <= diff && diff <= R) {
									visited[nr][nc] = true;
									isUnion[nr][nc] = num;
									unionQ.add(new UnionPos(nr, nc, num));
								}
							}
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	private static Queue<avgPop> calcAvgPop(int[][] map, int[][] isUnion) {
		boolean[][] visited = new boolean[N][N];
		Queue<Pos> q = new LinkedList<>();
		Queue<avgPop> avgQ = new LinkedList<>();
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if(isUnion[row][col] != 0 && !visited[row][col]) {
					int cnt = 0, sum = 0;
					q.add(new Pos(row, col));
					visited[row][col] = true;
					
					while(!q.isEmpty()) {
						Pos p = q.poll();
						
						cnt++;
						sum += map[p.row][p.col];
						
						for(int dir = 0; dir < 4; dir++) {
							int nr = p.row + dr[dir];
							int nc = p.col + dc[dir];
							
							if(isRange(nr, nc) && isUnion[nr][nc] == isUnion[row][col] && !visited[nr][nc]) {
								visited[nr][nc] = true;
								
								q.add(new Pos(nr, nc));
							}
						}
					}
					
					avgQ.add(new avgPop(row, col, sum / cnt));
				}
			}
		}
		
		return avgQ;
	}
	
	private static void move(int[][] map, int[][] isUnion, Queue<avgPop> avgQ) {
		boolean[][] visited = new boolean[N][N];
		
		while(!avgQ.isEmpty()) {
			avgPop inf = avgQ.poll();
			visited[inf.row][inf.col] = true;
			map[inf.row][inf.col]= inf.population;
			
			for(int dir = 0; dir < 4; dir++) {
				int nr = inf.row + dr[dir];
				int nc = inf.col + dc[dir];
				
				if(isRange(nr, nc) && isUnion[nr][nc] == isUnion[inf.row][inf.col] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					map[nr][nc] = inf.population;
					avgQ.add(new avgPop(nr, nc, inf.population));
				}
			}
		}
	}
	
	private static int simulation(int[][] map) {
		int day = 0;
		
		while(true) {
			int[][] isUnion = new int[N][N];
			
			if(!openPossGates(map, isUnion)) break;
			
			Queue<avgPop> avgQ = calcAvgPop(map, isUnion);
			
			move(map, isUnion, avgQ);
			
			day++;
		}
		
		return day;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int row = 0; row < N; row++) {
			stk = new StringTokenizer(br.readLine());
			for(int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(stk.nextToken());
			}
		}
		
		System.out.println(simulation(map));
	}
}
