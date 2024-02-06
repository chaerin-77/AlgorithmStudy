import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[][] arr;
	static int[] dr = { 0, 1, 0, -1 }; // RDLT
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int s = 0; s < Math.min(N, M) / 2; s++) {
			for (int r = 0; r < R; r++) {
				// 시작 위치에 있는 원소 임시 저장
				int temp = arr[s][s];
				// 방향, 현재 위치 초기화
				int dir = 0, cr = s, cc = s;
				while (true) {
					// 다음 위치 계산
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					
					// 시작 위치에 도달하면 while문 break;
					if (nr == s && nc == s)
						break;

					// 범위를 벗어나면 방향을 전환
					if (nr < s || nc < s || nr > N - s - 1 || nc > M - s - 1) {
						dir = (dir + 1) % 4;
						continue;
					}
					
					// 다음 위치의 원소를 현재 위치에 저장 (당김)
					arr[cr][cc] = arr[nr][nc];
					
					// 현재 위치 변경
					cr = nr;
					cc = nc;
				}
				// 첫 번째 원소 마지막 위치에 저장
				arr[cr][cc] = temp;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}