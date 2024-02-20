import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] Ti = new int[17];
        int[] Pi = new int[17];
        int[] dp = new int[17];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Ti[i] = Integer.parseInt(st.nextToken());
            Pi[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            // 현재 날짜에서 상담 날짜를 더했을 때 총 일수를 초과한다면 해당 일자의 상담 못함
            // 즉, 다음날의 최댓값과 같음
            if (Ti[i] + i > N + 1) dp[i] = dp[i + 1];
            else {
                // 현재 일자의 보상을 더한 값과 오늘 상담을 하지 않았을 경우의 최댓값 계산
                dp[i] = Math.max(Pi[i] + dp[i + Ti[i]], dp[i + 1]);
            }
        }

        System.out.println(dp[1]);
    }
}