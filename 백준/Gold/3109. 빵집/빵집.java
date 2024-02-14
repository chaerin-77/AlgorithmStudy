import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, answer = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	static boolean check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char temp =s.charAt(j);
				if (temp == '.') map[i][j] = 0;
				else map[i][j] = 1;
			}
		}
		for (int i = 0; i < R; i++) {
			check = false;
			dfs(i, 0);
		}
		System.out.println(answer);
	}
	
	public static void dfs (int r, int c) {
		visited[r][c] = true;
		
		if (c == C - 1) {
			answer++;
			check = true;
			return;
		}
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 체크 및 값 체크 (건물이 있을 경우 continue)
			if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 1) continue;
			
			if (!visited[nr][nc]) {
				dfs(nr, nc);
				if (check) return;
			}
		}
	}
}