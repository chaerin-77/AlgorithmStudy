import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] vertex;
	static boolean[] visited;
	static int N, M, answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		vertex = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) 
			vertex[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			vertex[from].add(to);
			vertex[to].add(from);
		}
		
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			find(i);
			answer++;
		}
		System.out.println(answer);
	}

	private static void find(int a) {
		visited[a] = true;
		
		for (int b : vertex[a]) {
			if (visited[b]) continue;
			find(b);
		}
	}
}