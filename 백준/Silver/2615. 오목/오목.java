import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간: 80ms, 메모리: 11,616KB
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[19][19];
		int[] dr = {-1, 0, 1, 1}; // 우상단, 우, 우하단, 하
		int[] dc = {1, 1, 1, 0};
		int winner = 0, R = 0, C = 0;
		
		// 입력
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				// 입력값이 0이면 continue;
				if (map[i][j] == 0) continue;
				
				int col = map[i][j];
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					int pr = i - dr[d];
					int pc = j - dc[d];
					
					if (pr >= 0 && pc >= 0 && pr < 19 && pc < 19 && map[pr][pc] == col) continue;
					int count = 1;
					
					while (true) {
						// 값, 범위 체크
						if (nr < 0 || nc < 0 || nr >= 19 || nc >= 19) break;
						if (map[nr][nc] == 0 || col != map[nr][nc]) break;
						
						count ++;
						nr += dr[d];
						nc += dc[d];
					}
					
					// 6목 체크
					if (count == 5) {
						winner = col;
						R = i + 1;
						C = j + 1;
					}
				}
			}
		}
		System.out.println(winner);
		if (winner != 0) System.out.println(R + " " + C);
	}
}