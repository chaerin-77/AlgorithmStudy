import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static Deque<Integer> q = new ArrayDeque<Integer>();
	static List<int[]> location;
	static boolean[][] connect;
	static boolean[] visited;
	static int[] house;
	static int[] festival;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			q.clear();
			connect = new boolean[n + 1][n + 1];
			visited = new boolean[n + 1];
			house = new int[2];
			location = new ArrayList<int[]>();
			st = new StringTokenizer(br.readLine());
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				location.add(new int[] {r, c});
			}
			
			for (int i = 0; i < n; i++) {
				int[] start = location.get(i);
				for (int j = i + 1; j <= n; j++) {
					int[] end = location.get(j);
					int dist = Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
					if (dist <= 1000) {
						connect[i][j] = true;
						connect[j][i] = true;
					}
				}
			}
			boolean flag = bfs();
			if (flag) System.out.println("happy");
			else System.out.println("sad");
		}
	}
	private static boolean bfs() {
		for (int i = 0; i <= n; i++) {
			int[] conv = location.get(i);
			int dist = Math.abs(conv[0] - house[0]) + Math.abs(conv[1] - house[1]);
			if (dist <= 1000) q.offer(i);
		}
		
		while (!q.isEmpty()) {
			int conv = q.poll();
			if (conv == n) return true;
			for (int i = 0; i <= n; i++) {
				if (connect[conv][i] && !visited[i]) {
					visited[conv] = true;
					q.offer(i);
				}
			}
		}
		return false;
	}
}