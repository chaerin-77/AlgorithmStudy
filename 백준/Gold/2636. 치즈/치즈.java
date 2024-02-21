import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 공기를 기준으로 bfs
 * - 주변이 공기일 땐 큐에 넣음
 * - 치즈를 만났을 경우 녹임
 */

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int R, C, time = 0, precheese = 0, cheese = 0;
	static Deque<int[]> air = new ArrayDeque<int[]>();
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if (temp == 1) cheese++;
			}
		}
		
		precheese = cheese;
		while(true) {
			if (cheese == 0) break;
			
			visited = new boolean[R][C];
			time++;
			precheese = cheese;
			bfs();
		}
		System.out.println(time);
		System.out.println(precheese);
	}
	
	private static void bfs() {
		air.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!air.isEmpty()) {
			int[] temp = air.poll();
			int r = temp[0];
			int c = temp[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				
				if (map[nr][nc] == 0) {
					air.offer(new int[] {nr, nc});
				} else {
					map[nr][nc] = 0;
					cheese--;
				}
			}
		}
	}
}