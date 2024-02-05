import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (N == 1) {
			System.out.println("<" + 1 + ">");
			return;
		}
		Queue<Integer> arr = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) arr.offer(i);
		
		sb.append("<");
		while(!arr.isEmpty()) {
			for (int i = 1; i < K; i++) {
				arr.offer(arr.poll());
			}
			sb.append(arr.poll());
			if (arr.size() != 0) sb.append(", ");
		}
		System.out.println(sb + ">");
	}
}