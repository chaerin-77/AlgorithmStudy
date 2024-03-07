import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Cloud {
		int r, c;

		public Cloud(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public void Next(int d, int dist) {
			dist %= N;
			// 1번 행과 N번 행, 1번 열과 N번 열은 연결
			r = (r + dr[d]*dist + N) % N;
			c = (c + dc[d]*dist + N) % N;
		}
	}
	static int N, M, answer = 0;
	static int[][] map;
	static int[][] move;
	static boolean[][] check;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static Deque<Cloud> cloud = new ArrayDeque<>();
	static Deque<Cloud> water = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		move = new int[M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 1) water.offer(new Cloud(i, j));
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = Integer.parseInt(st.nextToken()) - 1;
			move[i][1] = Integer.parseInt(st.nextToken());
		}
		// 초기 구름 삽입
		cloud.offer(new Cloud(N - 1, 0));
		cloud.offer(new Cloud(N - 1, 1));
		cloud.offer(new Cloud(N - 2, 0));
		cloud.offer(new Cloud(N - 2, 1));
		
		// 구름 이동 후 비내리기
		for (int i = 0; i < M; i++) {
			check = new boolean[N][N];
			RainDance(move[i]);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}
	
	private static void RainDance(int[] move) {
		int dir = move[0];
		int dist = move[1];
		
		int csize = cloud.size();
		int wsize = water.size();
		for (int i = 0; i < csize; i++) {
			cloud.offer(cloud.peek());
			Cloud temp = cloud.poll();
			
			temp.Next(dir, dist); // 구름 이동
			
			int r = temp.r;
			int c = temp.c;
			
			map[r][c]++;
			check[r][c] = true; // 같은 순서에 구름이 또 생기는 것을 막기 위해 체크
		}
		
		for (int i = 0; i < csize; i++) {
			Cloud temp = cloud.poll();
			int r = temp.r;
			int c = temp.c;
			
			for (int d = 1; d < 8; d += 2) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 대각선을 체크할 땐 범위를 넘어가면 안되기 때문에 체크
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (map[nr][nc] == 0) continue;
				map[r][c]++; // 대각선에 물이 있다면 물 증가
			}
			// 물이 2 이상이면 다음에 구름이 생겨야 하기 때문에 저장
			if (map[r][c] > 1) water.offer(new Cloud(r, c));
		}
		
		for (int i = 0; i < wsize; i++) {
			Cloud temp = water.poll();
			int r = temp.r;
			int c = temp.c;
			
			if (check[r][c]) continue;
			map[r][c] = map[r][c] - 2 < 0 ? 0 : map[r][c] - 2;
			cloud.offer(new Cloud(r, c));
			if (map[r][c] > 1) water.offer(new Cloud(r, c));
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.printf("%d ", map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
}