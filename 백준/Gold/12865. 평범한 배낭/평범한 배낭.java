import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] weight = new int[N+1];
		int[] value = new int[N+1];
		int[][] dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= K; w++) {
				if (weight[n] > w) dp[n][w] = dp[n - 1][w];
				else {
					dp[n][w] = Math.max(dp[n - 1][w], dp[n - 1][w - weight[n]] + value[n]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}