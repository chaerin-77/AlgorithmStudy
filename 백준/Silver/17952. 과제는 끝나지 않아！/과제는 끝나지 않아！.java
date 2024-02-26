import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, score = 0;
	static Stack<int[]> s = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 0) {
				if (s.isEmpty()) continue;
				int[] temp = s.pop();
				int A = temp[0];
				int T = temp[1];
				
				if (--T == 0) score += A;
				else s.push(new int[] {A, T});
			}
			else {
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken()) - 1;
				if (T == 0) score += A;
				else s.push(new int[] {A, T});
			}
		}
		System.out.println(score);
	}
}