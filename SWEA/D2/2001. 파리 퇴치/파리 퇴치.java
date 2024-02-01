import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());;
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = 0;
			int[][] map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					int value = Integer.parseInt(st.nextToken());
					map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + value;
				}
			}
			
			for (int i = N; i >= M; i--) {
				for (int j = N; j >= M; j--) {
					int temp = 0;
					temp = map[i][j] - map[i - M][j] - map[i][j - M] + map[i - M][j - M];
					if (temp > max) max = temp;
				}
			}
			
			System.out.println(String.format("#%d %d", t, max));
		}
	}
}
