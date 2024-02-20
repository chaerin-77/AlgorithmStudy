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
			priority.addAll(print); // pq에도 값들 저장
			
			while(!print.isEmpty()) {
				// 만약 맨 앞 순서가 중요도가 낮다면
				if (print.peek() < priority.peek()) {
					// 근데 그게 내가 궁금했던 순서였다면 맨 뒤로 인덱스 변경
					if (M == 0) M = print.size();
					
					// 맨 앞 순서를 맨 뒤로 보냄
					print.offer(print.poll());
				} else { // 중요도가 높다면
					if (M == 0) { // 내가 궁금한 순서였을 경우 답 출력 후 종료
						System.out.println(answer);
						break;
					}
					// 인쇄 후 인쇄 순서 증가
					print.remove();
					priority.remove();
					answer++;
				}
				// 나의 순서 감소
				M--;
			}
		}
	}
}