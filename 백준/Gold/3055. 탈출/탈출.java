import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Location {
		int r, c;

		public Location(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int R, C, answer = 0;
	static char[][] map;
	static boolean[][] visited;
	static Location beaver;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static Deque<Location> water = new ArrayDeque<Location>();
	static Deque<Location> move = new ArrayDeque<Location>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') move.offer(new Location(i, j));
				else if (map[i][j] == 'D') beaver = new Location(i, j);
				else if (map[i][j] == '*') water.offer(new Location(i, j));
			}
		}
		
		boolean flag = escape();
		if (flag) System.out.println(answer);
		else System.out.println("KAKTUS");
	}

	private static boolean escape() {
		while (true) {
			if (water.isEmpty() && move.isEmpty()) return false;
			
			int waterSize = water.size();
			for (int i = 0; i < waterSize; i++) {
				Location wtemp = water.poll();
				int wr = wtemp.r;
				int wc = wtemp.c;
				
				for (int d = 0; d < 4; d++) {
					int nwr = wr + dr[d];
					int nwc = wc + dc[d];
					
					if (nwr < 0 || nwc < 0 || nwr >= R || nwc >= C) continue;
					if (map[nwr][nwc] == 'D' || map[nwr][nwc] == '*' || map[nwr][nwc] == 'X') continue;
					
					map[nwr][nwc] = '*';
					water.offer(new Location(nwr, nwc));
				}
			}
			
			int moveSize = move.size();
			for (int i = 0; i < moveSize; i++) {
				Location temp = move.poll();
				int r = temp.r;
				int c = temp.c;
				
				if (r == beaver.r && c == beaver.c) return true;
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
					if (map[nr][nc] == '*' || map[nr][nc] == 'X' || visited[nr][nc]) continue;
					
					map[nr][nc] = 'S';
					visited[nr][nc] = true;
					move.offer(new Location(nr, nc));
				}
			}
			answer++;
		}
	}
}