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
					// 0이 아니고 방문하지 않았을 경우 bfs
					if(map[i][j] != 0 && !visited[i][j]) {
						iceBerg(i, j);
						// bfs로 나왔다면 집합이 있는 것이기 때문에 하나 증가
						sep++;
					}
				}
			}
			//그대로 0이라면 없는 것이기 때문에 0으로 세팅
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
		// 첫 좌표 삽입 후 방문 표시
		melting.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!melting.isEmpty()) {
			int[] temp = melting.poll();
			int cr = temp[0];
			int cc = temp[1];
			int count = 0;
			
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if (visited[nr][nc]) continue;
				// 0이라면 count 증가
				if (map[nr][nc] == 0) count++;
				// 0이 아니고 방문하지 않았다면 방문 표시 후 큐에 추가
				else if (!visited[nr][nc]) {
					melting.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			// 0까지만 빼주기 위함
			tempmap[cr][cc] = Math.max(0, tempmap[cr][cc] - count);
		}
		map = tempmap;
	}
}