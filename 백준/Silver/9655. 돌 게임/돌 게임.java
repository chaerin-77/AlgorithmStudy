import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 1]) + 1;
        }

        if (dp[N] % 2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}