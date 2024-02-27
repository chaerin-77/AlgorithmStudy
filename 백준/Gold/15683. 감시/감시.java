import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class CCTV {
		int num, r, c;

		public CCTV(int num, int r, int c) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, blindSpot;
	static List<CCTV> observe = new ArrayList<>();
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		blindSpot = N*M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					observe.add(new CCTV(map[i][j], i, j));
				}
			}
		}
		observe(0);
		System.out.println(blindSpot);
	}

	private static void observe(int cnt) {
		if (cnt == observe.size()) {
			// 사각지대 count;
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) continue;
					count++;
				}
			}
			blindSpot = Math.min(blindSpot, count);
			return;
		}
		
		// 이번 탐색에서 사용할 배열 생성
		int[][] map_set = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, map_set[i], 0, M);
		}
		
		CCTV cur = observe.get(cnt);
		for (int d = 0; d < 4; d++) {
			switch(cur.num) {
			case 1:
				check(cur.r, cur.c, d % 4);
				break;
			case 2:
				check(cur.r, cur.c, d % 4);
				check(cur.r, cur.c, (d + 2) % 4);
				break;
			case 3:
				check(cur.r, cur.c, d % 4);
				check(cur.r, cur.c, (d + 1) % 4);
				break;
			case 4:
				check(cur.r, cur.c, d % 4);
				check(cur.r, cur.c, (d + 1) % 4);
				check(cur.r, cur.c, (d + 2) % 4);
				break;
			case 5:
				check(cur.r, cur.c, d % 4);
				check(cur.r, cur.c, (d + 1) % 4);
				check(cur.r, cur.c, (d + 2) % 4);
				check(cur.r, cur.c, (d + 3) % 4);
				break;
			}
			observe(cnt + 1);
			
			// 사용한 좌표 초기화
			for (int i = 0; i < N; i++) {
				System.arraycopy(map_set[i], 0, map[i], 0, M);
			}
		}
		
	}

	private static void check(int r, int c, int d) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			// 범위를 벗어나거나 벽일 경우 함수 종료
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 6) return;
			// 다른 CCTV를 만났거나 이미 감시되는 구역일 경우 다음 좌표 탐색
			if (map[nr][nc] != 0) continue;
			map[nr][nc] = 8;
		}
	}
}