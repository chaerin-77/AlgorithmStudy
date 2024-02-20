import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, virus, fvirus = 0, answer = 0;
	static int[][] map, setMap;
	static int[] picked;
	static Deque<int[]> q = new ArrayDeque<int[]>();
	static Deque<int[]> tq = new ArrayDeque<int[]>();
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		setMap = new int[N][M];
		picked = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				setMap[i][j] = map[i][j];
				if(map[i][j] == 1) fvirus++;
				else if (map[i][j] == 2) q.offer(new int[] {i, j});
			}
		}
		virus = fvirus + 3;
		combi(0, 0);
		System.out.println(answer);
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == 3) {
			for(int i = 0; i < 3; i++) {
				int r = picked[i] / M;
				int c = picked[i] % M;
				setMap[r][c] = 1;
			}
			bfs();
			answer = Math.max(answer, N*M - virus);
			virus = fvirus + 3;
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, setMap[i], 0, M);
			}
			return;
		}
		
		for (int i = start; i < N*M; i++) {
			int r = i / M;
			int c = i % M;
			if (setMap[r][c] != 0) continue;
			picked[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
	
	private static void bfs() {
		tq.addAll(q);
		while(!tq.isEmpty()) {
			int[] temp = tq.poll();
			int r = temp[0];
			int c = temp[1];
			virus++;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (setMap[nr][nc] != 0) continue;
				
				setMap[nr][nc] = 2;
				tq.offer(new int[] {nr, nc});
			}
		}
	}
}