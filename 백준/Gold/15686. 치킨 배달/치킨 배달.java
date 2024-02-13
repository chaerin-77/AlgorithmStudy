import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer = Integer.MAX_VALUE;
	static int[] picked;
	static List<int[]> homes = new ArrayList<int[]>();
	static List<int[]> chicken = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		picked = new int[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) homes.add(new int[] {i, j});
				else if (temp == 2) chicken.add(new int[] {i, j});
			}
		}
		comb(0, 0);
		System.out.println(answer);
	}
	
	public static void comb (int cnt, int start) {
		if (cnt == M) {
			int dist = 0;
			for (int i = 0; i < homes.size(); i++) {
				int temp = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					temp = Math.min(temp, distance(homes.get(i), chicken.get(picked[j])));
				}
				dist += temp;
			}
			answer = Math.min(answer, dist);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			picked[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
	public static int distance (int[] a, int[]b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}