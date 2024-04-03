import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, answer, cnt;
	static List<Integer>[] students;
	static List<Integer>[] reverse;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			answer = 0;
			students = new List[N+1];
			reverse = new List[N+1];
			for (int i = 1; i <= N; i++) {
				students[i] = new ArrayList<>();
				reverse[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				students[a].add(b);
				reverse[b].add(a);
			}
			
			for (int a = 1; a <= N; a++) {
				cnt = 0;
				visited = new boolean[N+1];
				findParents(a);
				findChildren(a);
//				System.out.println(Arrays.toString(visited));
				if (cnt == N-1) answer++;
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void findChildren(int a) {
		visited[a] = true;
		for (int b : reverse[a]) {
			if (visited[b]) continue;
			cnt++;
			findChildren(b);
		}
	}

	private static void findParents(int a) {
		visited[a] = true;
		for (int b : students[a]) {
			if (visited[b]) continue;
			cnt++;
			findParents(b);
		}
	}
}