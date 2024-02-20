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
				trees[from].add(to);
				trees[to].add(from);
			}
			
			count = 0;
			for (int i = 1; i <= N; i++) {
				if(visited[i]) continue;
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
			edge++;
			if (visited[to]) continue;
			visited[to] = true;
			vertex++;
			dfs(to);
		}
	}
}