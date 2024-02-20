import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] dfsvisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		dfsvisited = new boolean[N + 1];
		adjList = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());			
			int idx = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[idx].add(to);
			adjList[to].add(idx);			
		}
		
		for (int i = 1; i <= N; i++)
			Collections.sort(adjList[i]);
		
		dfsvisited[start] = true;
		dfs(start);
		sb.append("\n");
		bfs(start);
		System.out.println(sb);
	}
	
	private static void bfs(int start) {
		Deque<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			sb.append(idx + " ");
			
			for (int num : adjList[idx]) {
				if (!visited[num]) {
					visited[num] = true;
					q.offer(num);
				}
			}
		}
	}
	
	private static void dfs(int start) {
		sb.append(start + " ");
		for (int num : adjList[start]) {
			if (!dfsvisited[num]) {
				dfsvisited[num] = true;
				dfs(num);
			}
		}
	}
}