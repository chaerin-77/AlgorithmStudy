import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int[][] arr;

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
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int calc = Integer.parseInt(st.nextToken());
			if (calc == 1) calc1();
			else if (calc == 2) calc2();
			else if (calc == 3) calc3();
			else if (calc == 4) calc4();
			else if (calc == 5) calc5();
			else calc6();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 상하 반전
	static void calc1() {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = arr[N - 1 - i][j];
			}
		}
		arr = temp;
	}
	
	// 좌우 반전
	static void calc2() {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = arr[i][M - 1 - j];
			}
		}
		arr = temp;
	}
	
	// 오른쪽 90도 회전
	static void calc3() {
		int[][] temp = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = arr[N - 1 -j][i];
			}
		}
		int x = N;
		N = M;
		M = x;
		arr = temp;
	}
	
	// 왼쪽 90도 회전
	static void calc4() {
		int[][] temp = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = arr[j][M - 1 - i];
			}
		}
		int x = N;
		N = M;
		M = x;
		arr = temp;
	}
	
	// 부분 배열 생성후 재배치 1 -> 2 -> 3 -> 4 -> 1
	static void calc5() {
		int[][] temp = new int[N][M];
		int[] dr = {N/2, 0, 0, -N/2};
		int[] dc = {0, -M/2, M/2, 0};
		int sdir = 0, dir = 0;
		for (int i = 0; i < N; i++) {
			if (i == N/2) sdir += 2;
			dir = sdir;
			for (int j = 0; j < M; j++) {
				if (j == M/2) dir++;
				int nr = i + dr[dir];
				int nc = j + dc[dir];
				temp[i][j] = arr[nr][nc];
			}
		}
		arr = temp;
	}
	
	// 부분 배열 생성후 재배치 1 -> 4 -> 3 -> 2 -> 1
		static void calc6() {
			int[][] temp = new int[N][M];
			int[] dr = {0, N/2, -N/2, 0};
		    int[] dc = {M/2, 0, 0, -M/2};
			int sdir = 0, dir = 0;
			for (int i = 0; i < N; i++) {
				if (i == N/2) sdir += 2;
				dir = sdir;
				for (int j = 0; j < M; j++) {
					if (j == M/2) dir++;
					int nr = i + dr[dir];
					int nc = j + dc[dir];
					temp[i][j] = arr[nr][nc];
				}
			}
			arr = temp;
		}
}