import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 시간: 3340ms, 메모리: 178,340KB
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> check = new Stack<>(); // 높이를 확인할 스택
		int[] top = new int[N + 1]; // 값을 입력 받을 배열
		int[] reception = new int[N + 1]; // 정답을 저장할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			// 배열에 입력 값 저장
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 번째 요소의 인덱스는 먼저 push
		check.push(1);
		for (int i = 2; i <= N; i++) {
			while (!check.empty()) {
				// 만약 스택에 push된 인덱스로 접근한 값이 크거나 같다면 break;
				if (top[i] <= top[check.peek()]) {
					reception[i] = check.peek();
					break;
				}
				// 그게 아니라면 pop
				check.pop();
			}
			// 다음에 어떤 높이가 들어올지 모르기 때문에 현재 값 push
			check.push(i);
			
		}
		for (int i = 1; i <= N; i++)
			System.out.print(reception[i] + " ");
	}
}