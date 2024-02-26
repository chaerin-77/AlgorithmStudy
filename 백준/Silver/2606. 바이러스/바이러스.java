import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, E, answer = 0;
	static boolean[] connect;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		connect = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		connect[1] = true;
		Check(1);
		
		for (int i = 2; i <= N; i++) {
			if (connect[i]) answer++;
		}
		System.out.println(answer);
	}
	
	private static void Check(int a) {
		for(int b : graph[a]) {
			if (connect[b]) continue;
			connect[b] = true;
			Check(b);
		}
	}
}