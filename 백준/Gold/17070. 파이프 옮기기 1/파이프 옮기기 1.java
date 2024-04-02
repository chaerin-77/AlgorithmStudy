import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		int[][][] dp = new int[N+1][N+1][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][2][0] = 1;
		for (int j = 3; j <= N; j++) { // (0, 2)부터 (0, N-1)까지 체크 
			if (map[1][j] == 1) continue;
			dp[1][j][0] = dp[1][j - 1][0]; // 가로열은 가로로밖에 못 감
		}
			
		for (int i = 2; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (map[i][j] == 1) continue;
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
				dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				if (map[i-1][j] == 0 && map[i][j-1] == 0) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}
}