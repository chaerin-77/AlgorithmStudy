import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N, answer = 0;
	static int[] board;
	static boolean[] Ccheck;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N];
			Ccheck = new boolean[N];
			answer = 0;

			dfs(0);
			System.out.println(String.format("#%d %d", t, answer));
		}
	}

	public static void dfs(int r) {
		if (r == N) {
			answer++;
			return;
		}
		
		A: for (int i = 0; i < N; i++) {
			if (Ccheck[i]) continue; // 해당 열에 값이 있으면 continue
			board[r] = i; // r행의 i 열에 값이 있다는 의미
			for (int j = 0; j < r; j++) {
				// 대각선 체크 (기울기)
				if (Math.abs(board[j] - board[r]) == Math.abs(j - r)) continue A;
			}
			// 열의 값을 true로 하고 dfs 후 다시 원상 복구
			Ccheck[i] = true;
			dfs(r + 1);
			Ccheck[i] = false;
		}
	}
}