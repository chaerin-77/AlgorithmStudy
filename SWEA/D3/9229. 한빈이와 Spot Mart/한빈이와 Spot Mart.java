import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, max;
	static int[] snack;
	static int[] picked;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = 0;
			snack = new int[N];
			picked = new int[2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0);
			if (max == 0) max = -1;
			System.out.println(String.format("#%d %d", t, max));
		}
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == 2) {
			int temp = picked[0] + picked[1];
			if (temp > max && temp <= M) max = temp;
			return;
		}
		for (int i = start; i < N; i++) {
			picked[cnt] = snack[i];
			comb(cnt + 1, i + 1);
		}
	}
}