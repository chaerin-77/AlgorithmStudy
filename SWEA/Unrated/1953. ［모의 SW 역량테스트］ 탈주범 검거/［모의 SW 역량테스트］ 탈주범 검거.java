import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, answer, startR, startC, time;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dr = {
			{},
			{0, -1, 0, 1},
			{0, -1, 0, 1},
			{0, 0, 0, 0},
			{0, -1, 0, 0},
			{0, 0, 0, 1},
			{0, 0, 0, 1},
			{0, -1, 0, 0}
	};
	static int[][] dc = {
			{},
			{-1, 0, 1, 0},
			{0, 0, 0, 0},
			{-1, 0, 1, 0},
			{0, 0, 1, 0},
			{0, 0, 1, 0},
			{-1, 0, 0, 0},
			{-1, 0, 0, 0}
	};
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			escape();
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void escape() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		for (int t = 0; t < time; t++) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				int r = temp[0];
				int c = temp[1];
				int num = map[r][c]; // 터널 번호
				answer++;
//				System.out.println(r+ " " + c + " " + num);
				
				for (int d = 0; d < 4; d++) {
					// 해당 방향으로 이동 불가 시 continue;
					if (dr[num][d] == 0 && dc[num][d] == 0) continue;
					
					int nr = r + dr[num][d];
					int nc = c + dc[num][d];
					
					// 1. 범위, 방문 체크
					if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;
					// 2. 해당 위치에 터널 존재 여부 체크
					if (map[nr][nc] == 0) continue;
					// 3. 해당 위치 터널이 연결되어 있는지 여부 체크
					int connect = map[nr][nc];
					if (dr[connect][(d+2)%4] == 0 && dc[connect][(d+2)%4] == 0) continue;
					
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
//			System.out.println();
		}
	}
}