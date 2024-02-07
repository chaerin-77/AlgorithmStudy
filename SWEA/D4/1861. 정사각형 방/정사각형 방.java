import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, max, ans;
	static Deque<int[]> room;
	static int[][] nums;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N][N];
			max = 0;
			ans = 0;
			room = new ArrayDeque<int[]>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					nums[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 좌표별로 bfs 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			
			System.out.println(String.format("#%d %d %d", t, ans, max));
		}
	}
	
	static void bfs(int r, int c) {
		// 배열 초기화 후 첫 좌표 offer
		room.clear();
		room.offer(new int[] {r, c});
		
		// 방문 배열 생성하여 중복 탐색 방지
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		
		// 방문 가능한 방의 갯수 저장 변수
		int count = 1;
		while (!room.isEmpty()) {
			int[] temp = room.poll();
			int cr = temp[0];
			int cc = temp[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				// 경계 체크
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				// 방문하지 않았고 1 증가했다면 방문체크 후 큐에 offer, count값 증가
				if (nums[nr][nc] == nums[cr][cc] + 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					room.offer(new int[] {nr, nc});
					count ++;
				}
			}
		}
		
		// 만약 최댓값이 count보다 작다면 최댓값 교체 후 최대 방문 가능 좌표 변경
		if (max < count) {
			max = count;
			ans = nums[r][c];
		}
		// 최댓값과 같다면 더 작은 좌표로 변경
		else if (max == count) {
			ans = Math.min(ans, nums[r][c]);
		}
	}
}