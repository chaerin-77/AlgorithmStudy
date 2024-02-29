import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 원래의 도시로 돌아오는 순회를 해야함 -> 무조건 사이클 -> 다익스트라 불가
 * 2. 도시를 순서대로 방문하는 것이기 때문에 순열
 * 3. 순열을 만든 후 해당 도시와의 길이 없으면 return; 아니면 합산 후 최소값으로 갱신
 */

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