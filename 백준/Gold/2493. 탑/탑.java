import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> check = new Stack<>();
		int[] top = new int[N + 1];
		int[] reception = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		check.push(1);
		for (int i = 2; i <= N; i++) {
			while (!check.empty()) {
				if (top[i] <= top[check.peek()]) {
					reception[i] = check.peek();
					break;
				}
				check.pop();
			}
			check.push(i);
			
		}
		for (int i = 1; i <= N; i++)
			System.out.print(reception[i] + " ");
	}
}