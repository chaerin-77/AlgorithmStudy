import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. CCTV의 좌표 입력 받기
 * 2. CCTV를 기준으로 dfs를 돈다
 * - 이 때 매 depth마다 배열을 생성하여 현재 까지의 시도를 저장해놓고 방향 회전 후 저장해놓은 배열로 다시 원위치 시킨다.
 * 3. depth가 CCTV의 개수와 같아지면 사각지대의 개수를 세어 최소값으로 갱신
 */

// 시간: 280ms, 메모리: 31,300KB
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
	static int N, M, blindSpot, result;
	static List<CCTV> observe = new ArrayList<>();
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int[] rotate = {0, 4, 2, 4, 4, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		blindSpot = 0;
		result = N*M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					observe.add(new CCTV(map[i][j], i, j));
				}
				if (map[i][j] == 0) blindSpot++;
			}
		}
		observe(0);
		System.out.println(result);
	}

	private static void observe(int cnt) {
		if (cnt == observe.size()) {
			result = Math.min(blindSpot, result);
			return;
		}
		
		// 이번 탐색에서 사용할 배열 생성
		int[][] map_set = new int[N][M];
		int backup = blindSpot;
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, map_set[i], 0, M);
		}
		
		CCTV cur = observe.get(cnt);
		for (int d = 0; d < rotate[cur.num]; d++) {
			
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
			blindSpot = backup;
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
			blindSpot--;
		}
	}
}