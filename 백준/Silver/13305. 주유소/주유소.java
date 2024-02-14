import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] distance = new int[N];
		int[] oil = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
		}
		int o = oil[0];
		int answer = oil[0] * distance[0];
		for (int i = 1; i < N - 1; i++) {
			o = Math.min(o, oil[i]);
			answer += o * distance[i];
		}
		System.out.println(answer);
	}
}