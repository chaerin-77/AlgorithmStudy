import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 집의 좌표를 받아 저장
 * 2. 모든 좌표를 기준으로 k값을 증가하여 범위를 늘려가며 확인
 * 3. k는 N+1까지 확인해야 모든 범위를 포함할 수 있다.
 */

public class Solution {
	static class House {
		int r, c;

		public House(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public int Dist(int sr, int sc) {
			return Math.abs(this.r - sr) + Math.abs(this.c - sc);
		}
	}
	
	static int N, M;
	static List<House> city;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			city = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					// 집의 좌표 저장
					if (temp == 1) {
						city.add(new House(i, j));
					}
				}
			}
			
			int answer = 0;
			// k를 늘려가면서 모든 좌표 확인 (N+1)까지 확인해야 모든 도시를 다 볼 수 있음
			for (int k = 1; k <= N + 1; k++) {
				int cost = k*k + (k-1)*(k-1); // 운영 비용 계산
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int money = 0, cnt = 0;
						
						for (int h = 0; h < city.size(); h++) {
							// 범위 내에 있을 경우 수익 증가, 포함되는 집 개수 증가
							if (city.get(h).Dist(i, j) < k) {
								money += M;
								cnt ++;
							}
						}
						// 수익이 나지 않았다면 continue;
						if (money - cost < 0) continue;
						
						// 수익이 났다면 집 개수 max값으로 변경
						answer = Math.max(answer, cnt);
					}
				}
			}
			sb.append("#" + t + " " + answer).append("\n");
		}
		System.out.println(sb);
	}
}