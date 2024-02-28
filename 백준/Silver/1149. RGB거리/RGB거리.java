import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N+1][3];
		int[][] dp = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
			input[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + input[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + input[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + input[i][2];
		}
		
		int answer = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		System.out.println(answer);
	}
}