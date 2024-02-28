import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 100까지의 최소 주사위 수 -> bfs
 * 2. 주사위를 돌리는 횟수에 따라 방문 할 수 있는 곳이 달라지기 때문에 주사위 수만큼 증가 시키며 큐에 넣어야 함
 * - 100이 되었다면 함수 종료 (해당 주사위 수가 최소)
 * - visited로 이미 최소인 좌표를 재탐색 하지 않도록 (이전 방문이 무조건 최소였을 것이기 때문)
 * 3. 뱀이거나 사다리거나 모두 이동해봐야 함
 */

public class Main {
	static int N, M, dice = 0;
	static int[] board = new int[101]; // 사다리와 뱀을 표시할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y; // 해당 인덱스의 값을 변경
		}
		
		play();
		System.out.println(dice);
	}

	private static void play() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101]; // 이미 확인된 곳을 표시할 배열
		q.offer(new int[] {1, 0}); // 처음 시작 좌표 표시
		
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int idx = temp[0];
			int cnt = temp[1];
			
			// 100에 도달했다면 해당 값 갱신 후 break;
			if (idx == 100) {
				dice = cnt;
				break;
			}
			
			// 주사위 수 만큼 현재 인덱스에서 늘려감
			for (int i = 1; i <= 6; i++) {
				int next = idx + i;
				
				if (next > 100) break; // 100 보다 커졌다면 for문 종료
				if (visited[next]) continue; // 이미 방문한 곳이라면 최소값이 아닐것이므로 다음 탐색
				if (board[next] != 0) next = board[next]; // 뱀이나 사다리 값이 있다면 해당 좌표로 이동
				
				// 해당 좌표를 true로 표시 후 큐에 삽입
				visited[next] = true; 
				q.offer(new int[] {next, cnt + 1});
			}
		}
	}
}