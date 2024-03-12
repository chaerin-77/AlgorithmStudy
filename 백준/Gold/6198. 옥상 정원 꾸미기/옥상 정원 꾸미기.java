import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] check = new int[N];
		int[] building = new int[N];
		
		for (int i = 0; i < N; i++) {
			building[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> s = new Stack<>();
		s.push(N - 1);
		for (int i = N - 2; i >= 0; i--) {
			while(!s.isEmpty()) {
				// 현재 빌딩이 더 낮다면 관찰 불가
				if (building[i] <= building[s.peek()]) {
					break;
				}
				check[i]++;
				check[i] += check[s.pop()];
			}
			s.push(i);
		}
		
		long answer = 0;
		for (int i = 0; i < N; i++) {
			answer += check[i];
		}
		System.out.println(answer);
	}
}