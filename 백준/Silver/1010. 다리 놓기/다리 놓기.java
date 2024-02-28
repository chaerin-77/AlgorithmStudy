import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 동쪽 사이트에서 서쪽 사이트 개수 만큼의 사이트를 선택하면 됨
 * - nCk와 동일
 * 2. 해당 식을 코드로 구현
 */

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			long answer = 1;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int k = 1;
			for (int n = M; n > M - N; n--) {
				answer *= n;
				answer /= k;
				k++;
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}