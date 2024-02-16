import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, day = 0;
	static int[][] tomato;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int[] start = new int[2];
	static Deque<int[]> q = new ArrayDeque<int[]>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		tomato = new int[N][M];
		visited = new boolean[N][M];
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				// 전부 익은 토마토인지 확인
				if (tomato[i][j] == 0 && !flag) flag = true;
				
				// 익은 토마토 큐에 추가
				if (tomato[i][j] == 1) {
					q.offer(new int[] {i, j, 0});
				}
			}
		}
		
		// 전부 익은 토마토였다면 0 출력 후 종료
		if (!flag) {
			System.out.println(0);
			return;
		}
		
		bfs();
		
		// 전부 익었는지 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(day);
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			int date = temp[2];
			
			// 탐색 중 소요된 시간 계산
			day = Math.max(day, date);
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (tomato[nr][nc] == -1 || tomato[nr][nc] == 1) continue;
				
				tomato[nr][nc] = 1;
				q.offer(new int[] {nr, nc, date + 1});
			}
		}
	}
}