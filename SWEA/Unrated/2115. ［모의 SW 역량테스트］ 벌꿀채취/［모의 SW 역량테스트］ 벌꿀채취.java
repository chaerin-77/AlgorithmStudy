import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 벌통 선택 시 시작 인덱스를 활용하여 선택
 * - N-M 값을 이용하여 뒤의 벌통은 선택하지 않도록 함
 * 2. 선택한 벌통들 중 벌꿀 값이 최대가 되도록 부분 조합을 이용하여 선택
 * - 이 때 return 값이 있는 부분조합 함수를 사용하여 계산
 * - 각 재귀에서 해당 벌통을 선택|비선택의 값 중 최댓값을 return 하게 됨
 */

public class Solution {
	static int N, M, C, answer = 0;
	static int[][] honey;
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			honey = new int[N][N];
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N*N; i++) {
				int r1 = i/N;
				int c1 = i%N;
				// 앞의 인덱스를 기준으로 뒤 M개를 선택할 것이기 때문에 뒤의 M개에서 시작하지 않도록 함
				if (c1 > N - M) continue; 
				
				// 해당 벌꿀을 선택했을 경우의 최댓값 계산
				int revenue1 = getMaxRevenue(r1, c1, 0, 0, 0);
				
				// i에서 M만큼 뒤의 값부터 선택 가능
				for (int j = i+M; j < N*N; j++) {
					int r2 = j/N;
					int c2 = j%N;
					if (c2 > N - M) continue;
					
					// 해당 벌꿀을 선택했을 경우의 최댓값 계산
					int revenue2 = getMaxRevenue(r2, c2, 0, 0, 0);
					
					answer = Math.max(answer, revenue1 + revenue2);
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	private static int getMaxRevenue(int r, int c, int idx, int sum, int revenue) {
		// 벌꿀의 합이 C보다 값이 크다면 0 return
		if (sum > C) return 0;
		
		// M개를 모두 선택했다면 현재 값 return
		if (idx == M) return revenue;
		
		int H = honey[r][c+idx]; // 현재 꿀의 값 꺼냄
		int A = getMaxRevenue(r, c, idx + 1, sum + H, revenue + H*H); // 현재 꿀 선택 
		int B = getMaxRevenue(r, c, idx + 1, sum, revenue); // 현재 꿀 선택 X
		
		// 각 지점에서 해당 꿀을 담을 경우, 담지 않을 경우 중 최댓값으로 return;
		return Math.max(A, B);
	}
}
