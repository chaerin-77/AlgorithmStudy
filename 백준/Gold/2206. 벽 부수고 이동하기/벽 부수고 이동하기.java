import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 뚫을 벽을 먼저 지정할 경우 -> 시간초과
 * 2. bfs를 탐색하면서 벽을 뚫고 지나가고 있는지 flag를 통해 확인하여 경로 확인 & 벽 뚫기 동시에 진행
 * 	- 벽을 뚫고 지나간 경로와 뚫지 않고 지나간 경로를 따로 저장
 * 		-> 벽을 뚫지 않고 지나간 경로와 구분해야 하는 이유
 * 			: 현재는 최단 경로가 아니지만 앞으로 이후에 벽을 뚫고 지나갈 경우 최단 경로가 될 수 있기 때문
 */

public class Main {
	static Deque<int[]> q = new ArrayDeque<int[]>();
	static int N, M, minDist = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		
		// 초기 값에서 변경이 없을 경우 -1, 그렇지 않으면 최단 거리 출력
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
	
	private static void bfs() {
		// 큐에는 {r, c, 현재 거리, 벽을 뚫었는 지 여부}
		q.offer(new int[] {0, 0, 1, 0});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			int dist = temp[2];
			int flag = temp[3];
			
			// 만약 끝까지 도달했으면 최단 거리 갱신
			if (r == N-1 && c == M-1) minDist = Math.min(minDist, dist);
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 체크
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				// 1. 현재 벽이고, 벽을 뚫고 지나오지 않았고, 벽을 뚫고 지나온 적이 없음 (지금이 첫 도달)
				if (map[nr][nc] == 1 && flag == 0 && !visited[nr][nc][1]) {
					// 1-1. 다음 경로와 1 증가한 거리, 벽을 뚫었다는 flag 큐에 추가
					q.offer(new int[] {nr, nc, dist + 1, 1});
					
					// 1-2. 벽을 뚫고 지나온 경로들의 visited 배열에 표시
					visited[nr][nc][1] = true;
				} 
				
				// 2. 현재 벽이 아님
				if (map[nr][nc] == 0) {
					// 2-1. 현재 벽이 아니고, 벽을 뚫고 지나왔으며, 벽을 뚫고 지나온 경로가 없을 경우 (첫 도달)
					if (flag == 1 && !visited[nr][nc][1]) {
						// 2-1-1. 다음 경로와 1 증가한 거리, 현재 flag(1) 큐에 추가
						q.offer(new int[] {nr, nc, dist + 1, 1});
						
						// 2-1-2. 벽을 뚫고 지나온 경로들의 visited 배열에 표시
						visited[nr][nc][1] = true;
					} 
					
					// 2-2. 현재 벽이 아니고, 벽을 뚫고 지나온 적이 없고, 벽을 뚫지 않고 지나온 경로가 없을 경우 (첫 도달)
					else if (flag == 0 && !visited[nr][nc][0]) {
						// 2-2-1. 다음 경로와 1 증가한 거리, 현재 flag(0) 큐에 추가
						q.offer(new int[] {nr, nc, dist + 1, 0});
						
						// 2-2-2. 벽을 뚫지 않고 지나온 경로들의 visited 배열에 표시
						visited[nr][nc][0] = true;
					}
				}
			}
		}
	}
}