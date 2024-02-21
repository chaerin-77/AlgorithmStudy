import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int answer = 0, linkedCnt = 0;
	static char[][] seating = new char[5][5];
	static boolean[] visited = new boolean[7];
	static int[] picked = new int[7];
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				seating[i][j] = s.charAt(j);
			}
		}
		combi(0, 0, 0);
		
		System.out.println(answer);
	}
	
	private static void combi(int cnt, int start, int y) {
		if (y > 3) return;
		
		if (cnt == 7) {
			visited = new boolean[7];
			visited[0] = true;
			linkedCnt = 0;
			findSeat(0);
			if (linkedCnt == 7) answer++;
			return;
		}
		
		for (int i = start; i < 25; i++) {
			int r = i / 5;
			int c = i % 5;
			int d = (seating[r][c] == 'Y' ? 1 : 0);
			
			picked[cnt] = i;
			combi(cnt + 1, i + 1, d + y);
		}
	}
	
	public static void findSeat(int idx) {		
		int r = picked[idx] / 5;
		int c = picked[idx] % 5;
		
		linkedCnt++;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
			
			for (int i = 0; i < 7; i++) {
				if (visited[i] || (nr * 5 + nc) != picked[i]) continue;
				
				visited[i] = true;
				findSeat(i);
			}
		}
	}
}