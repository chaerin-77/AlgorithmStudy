import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static Deque<int[]> q = new ArrayDeque<int[]>();
	static int N, M, minDist = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
	
	private static void bfs() {
		q.offer(new int[] {0, 0, 1, 0});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			int dist = temp[2];
			int flag = temp[3];
			
			if (r == N-1 && c == M-1) {
				minDist = Math.min(minDist, dist);
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				
				if (map[nr][nc] == 1 && flag == 0 && !visited[nr][nc][1]) {
					q.offer(new int[] {nr, nc, dist + 1, 1});
					visited[nr][nc][1] = true;
				} 
				
				if (map[nr][nc] == 0) {
					if (flag == 1 && !visited[nr][nc][1]) {
						q.offer(new int[] {nr, nc, dist + 1, 1});
						visited[nr][nc][1] = true;
					} 
					
					else if (flag == 0 && !visited[nr][nc][0]) {
						q.offer(new int[] {nr, nc, dist + 1, 0});
						visited[nr][nc][0] = true;
					}
				}
			}
		}
	}
}