import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
		static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int N, M;
	static boolean visited[][];
	
	public int solution(int[][] maps) {
		N = maps.length;
		M = maps[0].length;
		visited = new boolean[N][M];
		
		return bfs(0, 0, maps);
	}
	
	private int bfs(int r, int c, int[][] maps) {
		Deque<int[]> location = new ArrayDeque<>();
		int answer = -1;
		
		location.offer(new int[] {r, c, 1});
		visited[0][0] = true;
		
		while(!location.isEmpty()) {
			// 큐에 들어있는 요소 하나 꺼내서 사용
			int[] temp = location.poll();
			r = temp[0];
			c = temp[1];
			int count = temp[2];
			
			// 적 진영에 도달했다면 답 변경 후 break;
			if (r == N - 1 && c == M - 1) {
				answer = count;
				break;
			}
			
			// 큐에서 꺼낸 좌표에서 4방향 확인
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위를 벗어나거나, 벽이거나, 방문 했을 경우 continue;
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (maps[nr][nc] == 0) continue;
				if (!visited[nr][nc]) {
					// 좌표를 큐에 추가하고 방문 표시
					visited[nr][nc] = true;
					location.offer(new int[] {nr, nc, count + 1});
				}				
			}
		}
		return answer;
	}
}