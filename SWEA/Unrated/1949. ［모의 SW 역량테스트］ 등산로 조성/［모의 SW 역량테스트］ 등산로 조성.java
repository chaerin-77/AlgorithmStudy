import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 가장 높은 봉우리를 리스트 형태로 저장 -> 이후 모든 봉우리를 기준으로 시도
 * 2. dfs 시 visited 배열을 3차원으로 하여 공사를 한 경우, 안 한 경우로 나누어 진행
 * 3. 현재 높이보다 1만큼만 작게 하는 것이 최선의 선택이기 때문에
 * - 현재 높이보다 작을 경우 그대로 dfs
 * - 현재 높이와 같거나 높을 경우 현재 높이에서 -1만큼 깎을 수 있는지 확인 후 dfs
 */

public class Solution {
	static int N, K, answer;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			answer = 0;
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			List<int[]> peeks = new ArrayList<>();
			map = new int[N][N];
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (max <= map[i][j]) {
						if (max < map[i][j]) {
							peeks.clear(); // 더 큰 값이 있을 경우 리스트 초기화
							max = map[i][j];
						}
						peeks.add(new int[] {i, j}); // 리스트에 봉우리 추가
					}
				}
			}
			for (int i = 0; i < peeks.size(); i++) {
				int[] peek = peeks.get(i);
				visited = new boolean[N][N][2];
				visited[peek[0]][peek[1]][0] = true;
				dfs(peek[0], peek[1], 0, 1);
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int flag, int length) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위, 방문 체크
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc][flag]) continue;
			
			int height = map[nr][nc];
			// 다음 봉우리가 더 낮을 경우 다음 탐색
			if (height < map[r][c]) {
				visited[nr][nc][flag] = true;
				dfs(nr, nc, flag, length + 1);
				visited[nr][nc][flag] = false;
			} else {
				// 이미 공사를 한 경우 다음 탐색
				if (flag == 1) continue;
				
				// 깊게 파도 안될 경우 다음 탐색
				if (height - (map[r][c] - 1) > K) continue;
				
				visited[nr][nc][1] = true;
				map[nr][nc] = map[r][c] - 1;
				dfs(nr, nc, 1, length + 1);
				map[nr][nc] = height;
				visited[nr][nc][1] = false;
			}
		}
		// 4방향 탐색 종료 후 더 이상 탐색이 안될 경우 값 갱신
		answer = Math.max(answer, length);
	}
}