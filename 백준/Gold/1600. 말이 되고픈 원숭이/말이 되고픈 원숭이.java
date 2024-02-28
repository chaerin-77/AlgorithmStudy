import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Location {
		int r, c, hcnt, cnt;

		public Location(int r, int c, int hcnt, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.hcnt = hcnt;
			this.cnt = cnt;
		}
	}
	
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	
	static int[] dr = {0, -1, 0, 1, -1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(move());
	}

	private static int move() {
		Deque<Location> q = new ArrayDeque<>();
		q.offer(new Location(0, 0, 0, 0));
		for (int k = 0; k <= K; k++) visited[0][0][k] = true;
		
		while(!q.isEmpty()) {
			Location temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int hcnt = temp.hcnt;
			int cnt = temp.cnt;
			
			if (r == H-1 && c == W-1) return cnt;
			
			for (int d = 0; d < 12; d++) {
				// 말처럼 K번 움직였으면 원숭이처럼만 이동 후 종료
				if (hcnt == K && d == 4) break;
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				int horse = hcnt;
				
				if (d > 3) horse = hcnt + 1;
				
				if (nr < 0 || nc < 0 || nr >= H || nc >= W || map[nr][nc] == 1) continue;
				if (visited[nr][nc][horse]) continue;
				
				visited[nr][nc][horse] = true;
				q.offer(new Location(nr, nc, horse, cnt + 1));
			}
		}
		
		return -1;
	}
}