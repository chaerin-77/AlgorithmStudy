import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, Ncnt = 0, Bcnt = 0;
	static char[][] image;
	static boolean[][] visitedN, visitedB;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		image = new char[N][N];
		visitedN = new boolean[N][N];
		visitedB = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				image[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitedN[i][j]) {
					Nbfs(i, j);
					Ncnt++;
				}
				if (!visitedB[i][j]) {
					Bbfs(i, j);
					Bcnt++;
				}
			}
		}
		System.out.println(Ncnt + " " + Bcnt);
	}
	
	private static void Nbfs(int startR, int startC) {
		Deque<int[]> q = new ArrayDeque<>();
		visitedN[startR][startC] = true;
		q.offer(new int[] {startR, startC});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			char col = image[r][c];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visitedN[nr][nc] || image[nr][nc] != col) continue;
				
				visitedN[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
	}
	
	private static void Bbfs(int startR, int startC) {
		Deque<int[]> q = new ArrayDeque<>();
		visitedB[startR][startC] = true;
		q.offer(new int[] {startR, startC});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			char col = image[r][c];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visitedB[nr][nc]) continue;
				
				if (col == 'R' || col == 'G') {
					if (image[nr][nc] != 'R' && image[nr][nc] != 'G') continue;
				}
				else if (image[nr][nc] != col) continue;
				
				visitedB[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
	}
}