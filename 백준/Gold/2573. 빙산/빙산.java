import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, sep, year = 0;
	static int[][] map, tempmap;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tempmap = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tempmap[i][j] = map[i][j];
			}
		}
		
		while(true) {
			sep = 0;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						iceBerg(i, j);
						sep++;
					}
				}
			}
			if (sep == 0) {
				year = 0;
				break;
			}
			if (sep > 1) break;
			year++;
		}
		System.out.println(year);
	}
	
	public static void iceBerg(int r, int c) {
		Deque<int[]> melting = new ArrayDeque<int[]>();
		melting.offer(new int[] {r, c});
		
		while(!melting.isEmpty()) {
			int[] temp = melting.poll();
			int cr = temp[0];
			int cc = temp[1];
			int count = 0;
			
			if (visited[cr][cc]) continue;
			visited[cr][cc] = true;
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				// 다음 좌표 범위 체크
				if (visited[nr][nc]) continue;

				if (map[nr][nc] == 0) count++;
				else if (!visited[nr][nc]) melting.offer(new int[] {nr, nc});
			}
			tempmap[cr][cc] = Math.max(0, tempmap[cr][cc] - count);
		}
		map = tempmap;
	}
}