import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 양의 가중치, 싸이클 존재 -> 플로이드 워샬 사용
 * 2. 이 때 최댓값은 1001로 저장
 * 3. 연산 시 열과 행이 같은, 즉 나 자신으로 돌아오는 경로는 skip 하도록 해야 함
 */

public class Solution {
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] net = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					net[i][j] = Integer.parseInt(st.nextToken());
					if (net[i][j] == 0) net[i][j] = 1001;
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (i == j) continue;
						// 직접 연결된 경로 | k 노드를 거쳐서 연결된 경로
						net[i][j] = Math.min(net[i][j], net[i][k] + net[k][j]);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					sum += net[i][j];
				}
				min = Math.min(min, sum);
			}
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
}