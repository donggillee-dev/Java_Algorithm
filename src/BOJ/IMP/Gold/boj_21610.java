package BOJ.IMP.Gold;

//Logic
//1. 구름 이동(이때 경계를 넘어가는 애들에 대해서 처리 필요)
//2. 이동된 구름들에 대해서 비를 내려주고 구름은 사라진다(이때 사라진 위치 체크)
//3. 비가 내린 위치에 애들 중에서 대각선에 존재하는 물이 차있는 바구니의 개수만큼 물복사 버그 일어남
//4. 전체에서 2 이상 물 차있는 애들 중에 2에서 구름이 사라진 위치가 아닌애들에서 2만큼 증발하여 구름 생

import java.io.*;
import java.util.*;

public class boj_21610 {
	private static int N, M;
	private static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	private static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	private static class Order {
		int dir, dist;
		
		public Order(int dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
	}
	
	private static class Pos {
		int row, col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static boolean[][] rainAndCopy(int[][] A, Order cmd, Queue<Pos> cloudQ) {
		boolean[][] wasCloud = new boolean[N][N];
		Queue<Pos> moveCloudQ = new LinkedList<>();
		
		//move & rain
		while(!cloudQ.isEmpty()) {
			Pos p = cloudQ.poll();
			
			int nr = (p.row + dr[cmd.dir] * cmd.dist) % N;
			int nc = (p.col + dc[cmd.dir] * cmd.dist) % N;
			
			if(nr < 0) nr += N;
			if(nc < 0) nc += N;
			
			A[nr][nc]++;
			wasCloud[nr][nc] = true;
			moveCloudQ.add(new Pos(nr, nc));
		}
		
		//copy
		while(!moveCloudQ.isEmpty()) {
			Pos p = moveCloudQ.poll();
			
			int cnt = 0;
			for(int dir = 1; dir < 8; dir += 2) {
				int nr = p.row + dr[dir];
				int nc = p.col + dc[dir];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(A[nr][nc] > 0) cnt++;
			}
			
			A[p.row][p.col] += cnt; 
		}
		
		return wasCloud;
	}
	
	private static int simulation(int[][] A, Queue<Order> cmdQ) {
		int ans = 0;
		Queue<Pos> cloudQ = new LinkedList<>();
		//Init first cloud
		cloudQ.add(new Pos(N - 1, 0));
		cloudQ.add(new Pos(N - 1, 1));
		cloudQ.add(new Pos(N - 2, 0));
		cloudQ.add(new Pos(N - 2, 1));
		
		//Process command
		while(!cmdQ.isEmpty()) {
			Order cmd = cmdQ.poll();
			
			boolean[][] wasCloud = rainAndCopy(A, cmd, cloudQ);
			
			//makeCloud
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(!wasCloud[row][col] && A[row][col] >= 2) {
						cloudQ.add(new Pos(row, col));
						A[row][col] -= 2; //evaporate
					}
				}
			}
		}
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				ans += A[row][col];
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		int[][] A = new int[N][N];
		Queue<Order> cmdQ = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(stk.nextToken()) - 1;
			int dist = Integer.parseInt(stk.nextToken());
			cmdQ.add(new Order(dir, dist));
		}
		
		System.out.println(simulation(A, cmdQ));
	}
}
