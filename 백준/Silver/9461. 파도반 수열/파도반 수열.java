import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            long[] dp = new long[101];

            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;

            for (int i = 6; i <= N; i++) {
                dp[i] = dp[i - 1] + dp[i - 5];
            }

            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}