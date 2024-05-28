import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. M원을 만들 수 있는 경우의 수를 저장하는 dp 배열 생성
 * 2. 이후 그 값을 증가시키면서 마지막 M번째 dp 배열 값이 정답이다
 * 3. 입력 받은 동전의 값을 확인하면서 금액과 동전의 값 차이를 확인하여 갑 합산
 */

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] coin = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (j - coin[i] > 0) {
                        dp[j] = dp[j] + dp[j - coin[i]];
                    } else if (j - coin[i] == 0) {
                        dp[j] ++;
                    }
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}