import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] picked;
	static int[][] W;
	static int N, answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		picked = new int[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permu(0, 0);
		System.out.println(answer);
	}
	
	private static void permu (int cnt, int flag) {
		if (cnt == N) {
			picked[N] = picked[0];
			int cost = 0;
			for (int i = 0; i < N; i++) {
				int temp = W[picked[i]][picked[i+1]];
				if (temp == 0) return;
				cost += temp;
			}
			answer = Math.min(answer, cost);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) != 0) continue;
			picked[cnt] = i;
			permu(cnt + 1, flag | (1 << i));
		}
	}
}