import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int idx, weight;

		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
	}
	static List<Node>[] vertex;
	static int V, E, start;
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		vertex = new List[V + 1];
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		for (int i = 1; i <= V; i++) {
			vertex[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			vertex[from].add(new Node(to, weight));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((w1, w2) -> w1.weight - w2.weight);
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for (Node temp : vertex[current.idx]) {
				int d = dist[current.idx] + temp.weight;
				if (d < dist[temp.idx]) {
					dist[temp.idx] = d;
					pq.offer(new Node(temp.idx, d));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			int temp = dist[i];
			if (temp == INF) sb.append("INF").append("\n");
			else sb.append(temp).append("\n");
		}
		System.out.println(sb);
	}
}