import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, Ncnt = 0, Bcnt = 0;
	static char[][] image;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		image = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				image[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					Ncnt++;
				}

				// 적록 색약이 아닌 사람 계산 후 적록 색약 계산을 위해 같은 색으로 변경
				if (image[i][j] == 'G') image[i][j] = 'R';
			}
		}

		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					Bcnt++;
				}
			}
		}
		System.out.println(Ncnt + " " + Bcnt);
	}

	private static void dfs(int r, int c) {
		char col = image[r][c];
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 경계 체크
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			// 방문 여부, 색 동일 여부 체크
			if (visited[nr][nc] || image[nr][nc] != col) continue;

			dfs(nr, nc);
		}
	}
}