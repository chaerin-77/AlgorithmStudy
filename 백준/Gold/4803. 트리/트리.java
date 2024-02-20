import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, count, edge, vertex;
	static List<Integer>[] trees;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Case = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break; // 0 0 입력 시 종료
			
			trees = new List[N + 1];
			visited = new boolean[N + 1];
			
			for (int i = 0; i <= N; i++) {
				trees[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 양방향 간선 처리
				trees[from].add(to);
				trees[to].add(from);
			}
			
			count = 0;
			for (int i = 1; i <= N; i++) {
				if(visited[i]) continue; // 방문 했던 정점일 경우 다음 탐색
				/*
				 * 트리는 정점 N개, 간선 수 N-1 개 라고 했기 때문에
				 * 정점과 간선의 갯수를 초기화하면서 dfs 탐색
				 */
				edge = 0;
				vertex = 1;
				visited[i] = true;
				dfs(i);
				
				if(edge / 2 == vertex - 1) count++;
			}
			if (count == 0) sb.append("Case " + Case++ + ": No trees.\n");
			else if (count == 1) sb.append("Case " + Case++ + ": There is one tree.\n");
			else sb.append("Case " + Case++ + ": A forest of " + count + " trees.\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int v) {
		for (int to : trees[v]) {
			edge++; // 간선의 개수는 먼저 count
			if (visited[to]) continue;
			// 방문하지 않았던 정점일 경우에만 정점의 개수 증가
			vertex++;
			visited[to] = true;
			dfs(to);
		}
	}
}