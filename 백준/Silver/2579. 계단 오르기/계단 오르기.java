import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[301];
		int[] dp = new int[301];
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		// 첫 번째 계단의 최대 값은 첫 번째 계단을 밟는 것
		dp[1] = input[1];
		// 두 번째 계단의 최대 값은 1, 2 계단을 모두 밟거나 2 계단만 밟는 것
		dp[2] = Math.max(input[1], input[1] + input[2]);
		// 세 번재 계단의 초대 값은 1, 3 계단을 밟거나 2, 3 계단을 밟는 것
		dp[3] = Math.max(input[1] + input[3], input[2] + input[3]);
		for (int i = 4; i <= N; i++) {
			// 같은 원리로 직전 계단을 밟고 한 칸 더 떨어진 곳의 최대 값 이거나
			// 한 칸 떨어진 곳의 최댓값 
			dp[i] = Math.max(input[i - 1] + dp[i - 3], dp[i - 2]) + input[i];
		}
		System.out.println(dp[N]);
	}
}