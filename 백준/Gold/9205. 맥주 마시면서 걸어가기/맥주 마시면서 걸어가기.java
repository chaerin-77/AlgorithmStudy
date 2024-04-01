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
 * 1. 편의점 하나를 기준으로 bfs
 * 2. 입력 받은 후 모든 지점이 방문 가능한지 여부를 boolean 배열로 저장
 * - 거리가 1000 이하이면 방문 가능
 * 3. bfs를 돌며 페스티벌 장소에 도착했을 경우 true를 return 하여 happy 출력
 */

public class Main {
	static Deque<Integer> q = new ArrayDeque<Integer>();
	static List<int[]> location;
	static boolean[][] connect;
	static boolean[] visited;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			q.clear();
			connect = new boolean[n + 2][n + 2];
			visited = new boolean[n + 2];
			location = new ArrayList<int[]>();
			for (int i = 0; i <= n + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				location.add(new int[] {r, c});
			}
			
			visited[0] = true; // 시작 지점 방문 처리
			for (int i = 0; i <= n; i++) {
				int[] start = location.get(i);
				for (int j = i + 1; j <= n + 1; j++) {
					int[] end = location.get(j);
					int dist = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
					if (dist <= 1000) {
						connect[i][j] = true;
						connect[j][i] = true;
					}
					if (connect[i][j] && i == 0) {
						visited[j] = true;
						q.offer(j);
					}
				}
			}
			boolean flag = bfs();
			if (flag) System.out.println("happy");
			else System.out.println("sad");
		}
	}
	private static boolean bfs() {
		while (!q.isEmpty()) {
			int conv = q.poll();
			if (conv == n + 1) return true;
			for (int i = 0; i <= n + 1; i++) {
				if (connect[conv][i] && !visited[i]) {
					visited[conv] = true;
					q.offer(i);
				}
			}
		}
		return false;
	}
}