import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, max = 0;
	static int[] cards;
	static int[] picked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];
		picked = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		pick(0, 0);
		System.out.println(max);
	}
	
	public static void pick (int cnt, int start) {
		if (cnt == 3) {
			int temp = 0;
			for (int i = 0; i < 3; i++) {
				temp += picked[i];
				if (temp > M) return;
			}
			max = Math.max(max, temp);
			return;
		}
		
		for (int i = start; i < N; i++) {
			picked[cnt] = cards[i];
			pick(cnt + 1, i + 1);
		}
	}
}