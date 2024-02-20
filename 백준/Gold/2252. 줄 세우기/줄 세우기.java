import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] degrees;
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
			student[A].add(B);
			degrees[B]++;
		}
		
		ordering();
		System.out.println(sb);
	}
	
	private static void ordering() {
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0) order.offer(i);
		}
		
		while(!order.isEmpty()) {
			int pre = order.poll();
			sb.append(pre + " ");
			
			for (int last : student[pre]) {
				degrees[last]--;
				if(degrees[last] == 0) order.offer(last);
			}
		}
	}
}