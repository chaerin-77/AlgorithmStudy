import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1; // 자기 자신은 팰린드롬
            if (arr[i - 1] == arr[i]) dp[i - 1][i] = 1; // 앞뒤가 같다면 팰린드롬
        }

        for (int i = N - 1; i > 0; i--) {
            for (int j = i + 2; j <= N; j++) {
                if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) dp[i][j] = 1;
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(dp[S][E]).append("\n");
        }
        System.out.println(sb);
    }
}