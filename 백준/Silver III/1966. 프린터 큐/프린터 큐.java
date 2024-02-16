import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 개수
			int M = Integer.parseInt(st.nextToken()); // 궁금한 문서의 순서
			int answer = 1;
			Deque<Integer> print = new ArrayDeque<Integer>();
			PriorityQueue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				print.offer(temp);
			}
			priority.addAll(print);
			
			while(!print.isEmpty()) {
				if (M == 0 && print.peek() == priority.peek()) {
					System.out.println(answer);
					break;
				}
				
				if (print.peek() < priority.peek()) {
					if(M == 0) M = print.size();
					print.offer(print.poll());
				} else {
					print.remove();
					priority.remove();
					answer++;
				}
				M--;
			}
		}
	}
}