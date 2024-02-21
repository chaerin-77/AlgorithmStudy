import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] friends;
	static boolean[] check;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		friends = new List[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			friends[A].add(B);
			friends[B].add(A);
		}
		
		for (int i = 0; i < N; i++) {
			check[i] = true;
			dfs(i, 1);
			check[i] = false;
		}
		
		System.out.println(0);
	}
	
	private static void dfs(int student, int cnt) {
		if (cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}
		
		for (int f : friends[student]) {
			if (check[f]) continue;
			check[f] = true;
			dfs(f, cnt + 1);
			check[f] = false;
		}
	}
}