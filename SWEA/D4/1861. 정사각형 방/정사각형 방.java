import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, max;
	static Deque<int[]> room;
	static int[][] nums;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			room = new ArrayDeque<int[]>();
			nums = new int[N][N];
			max = 0;
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					nums[i][j] = temp;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			System.out.println(String.format("#%d %d %d", t, ans, max));
		}
	}
	
	static void bfs(int x, int y) {
		room.clear();
		room.offer(new int[] {x, y});
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		int count = 1;
		while (!room.isEmpty()) {
			int[] temp = room.poll();
			int r = temp[0];
			int c = temp[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (nums[nr][nc] == nums[r][c] + 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					room.offer(new int[] {nr, nc});
					count ++;
				}
			}
		}
		if (max < count) {
			max = count;
			ans = nums[x][y];
		} else if (max == count) {
			ans = Math.min(ans, nums[x][y]);
		}
	}
}
