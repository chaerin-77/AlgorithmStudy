import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int weight, nextR, nextC;

		public Node(int nextR, int nextC, int weight) {
			super();
			this.nextR = nextR;
			this.nextC = nextC;
			this.weight = weight;
		}
	}
	
	static int N, minCost;
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int num = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			map = new int[N][N];
			minCost = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findRoute();
			sb.append("Problem " + (num++) + ": " + minCost).append("\n");
		}
		System.out.println(sb);
	}
	private static void findRoute() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		boolean[][] visited = new boolean[N][N];
		pq.offer(new Node(0, 0, map[0][0]));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int r = node.nextR;
			int c = node.nextC;
			int curWeight = node.weight;
			
			if (r == N-1 && c == N-1) {
				minCost = curWeight;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
				
				pq.offer(new Node(nr, nc, curWeight + map[nr][nc]));
				visited[nr][nc] = true;
			}
		}
	}
}