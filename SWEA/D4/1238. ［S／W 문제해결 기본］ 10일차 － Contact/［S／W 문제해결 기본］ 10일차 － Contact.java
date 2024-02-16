import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer>[] emergency;
	static int num, last;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			num = 0;
			last = 0;
			emergency = new List[101];
			for (int i = 1; i < 101; i++) {
				emergency[i] = new ArrayList<Integer>();
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < L; i += 2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				emergency[from].add(to);
			}
			bfs(start);
			System.out.println(String.format("#%d %d", t, num));
		}
	}
	
	private static void bfs(int start) {
		Deque<int[]> q = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[101];
		q.offer(new int[] {start, 0});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int from = temp[0];
			int isLast = temp[1];
			
			if (isLast >= last) {
				num = Math.max(num, from);
			}
			
			for (int to : emergency[from]) {
				if(!visited[to]) {
					visited[to] = true;
					q.offer(new int[] {to, isLast + 1});
					last = isLast + 1;
					num = 0;
				}
			}
		}
	}
}