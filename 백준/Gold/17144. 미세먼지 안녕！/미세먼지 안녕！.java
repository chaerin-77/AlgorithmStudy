import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, copymap;
	static List<int[]> cleaner = new ArrayList<int[]>();
	static int R, C, T;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		copymap = new int[R][C];
		for (int i = 0; i < R; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) cleaner.add(new int[] {i, j});
				copymap[i][j] = map[i][j];
			}
		}
		
		for (int t = 0; t < T; t++) {
			spread();
			airClean();
		}
		
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (copymap[i][j] == -1) continue;
				answer += copymap[i][j];
			}
		}
		System.out.println(answer);
	}
	
	private static void spread() {
		// 사용할 맵 복사
		for (int i = 0; i < R; i++) {
			System.arraycopy(copymap[i], 0, map[i], 0, C);
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] < 2) continue; // 0, 1일 경우 확산 불가
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == -1) continue;
					
					cnt++;
					copymap[nr][nc] += map[r][c] / 5;
				}
				copymap[r][c] -= (map[r][c] / 5) * cnt;
			}
		}
	}
	
	private static void airClean() {
		// 위쪽 회전
		int sr = cleaner.get(0)[0];
		int sc = cleaner.get(0)[1];
		int d = 0, cr = sr - 1, cc = sc;
		
		while (true) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			
			if (nr == sr && nc == sc) break;
			
			if (nr < 0 || nc < 0 || nr > sr || nc >= C) {
				d = (d+1) % 4;
				continue;
			}
			copymap[cr][cc] = copymap[nr][nc];
			cr = nr;
			cc = nc;
		}
		copymap[cr][cc] = 0;
		
		// 아래쪽 회전
		sr = cleaner.get(1)[0];
		sc = cleaner.get(1)[1];
		d = 2;
		cr = sr + 1; 
		cc = sc;
		
		while (true) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			
			if (nr == sr && nc == sc) break;
			
			if (nr < sr || nc < 0 || nr >= R || nc >= C) {
				d = (d + 3) % 4;
				continue;
			}
			copymap[cr][cc] = copymap[nr][nc];
			cr = nr;
			cc = nc;
		}
		copymap[cr][cc] = 0;
	}
}