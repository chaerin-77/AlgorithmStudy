import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, dice = 0;
	static int[] board = new int[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}
		
		play();
		
		System.out.println(dice);
	}

	private static void play() {
		Deque<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		q.offer(new int[] {1, 0});
		
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int idx = temp[0];
			int cnt = temp[1];
			
			if (idx == 100) {
				dice = cnt;
				break;
			}
			
			for (int i = 1; i <= 6; i++) {
				int next = idx + i;
				
				if (next > 100) break;
				if (visited[next]) continue;
				if (board[next] != 0) next = board[next];
				
				visited[next] = true;
				q.offer(new int[] {next, cnt + 1});
			}
		}
	}
}