import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 선행 조건이 있다 -> 위상 정렬
 * 2. 나보다 선행되어야 하는 조건의 수 세기 (진입 차수)
 * 3. 선행 조건이 없는 요소들 부터 큐에 추가한 후 해당 요소를 선행으로 가지는 요소의 진입차수 감소
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] degrees; // 나보다 앞에 서야하는 학생의 수
	static List<Integer>[] student;
	static Deque<Integer> order = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		student = new List[N + 1];
		degrees = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			student[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			student[A].add(B); // A의 뒤에 서야하는 B 추가
			degrees[B]++; // B보다 앞에 서야하는 학생 수 증가
		}
		
		ordering();
		System.out.println(sb);
	}
	
	private static void ordering() {
		for (int i = 1; i <= N; i++) {
			// 진입 차수가 0인 학생들 큐에 추가
			if (degrees[i] == 0) order.offer(i);
		}
		
		while(!order.isEmpty()) {
			int pre = order.poll();
			sb.append(pre + " "); // 큐에서 제거하고 출력
			
			for (int last : student[pre]) {
				degrees[last]--; // 해당 학생의 진입 차수 감소
				if(degrees[last] == 0) order.offer(last); // 진입 차수가 0이 되면 큐에 추가
			}
		}
	}
}