import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			makeSet();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int calc = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 0일 경우 union, 1일 경우 같은 집합인지 find
				if (calc == 0) union(a, b);
				else {
					if (find(a) == find(b)) sb.append(1); // 부모가 같으면 1
					else sb.append(0); // 아니면 0
				}
			}
			System.out.println("#" + t + " " + sb);
		}
	}
	
	private static void makeSet() {
		parents = new int[N + 1];
		
		// 배열 생성 후 자기 자신을 부모로 설정
		for (int i = 1; i <= N; i++) 
			parents[i] = i;
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parents[rootA] = rootB; // b를 a의 부모로 설정
	}
	
	private static int find(int a) {
		// 자기 자신이 부모라면 return
		if(parents[a] == a) return a;
		
		// 아니면 a의 부모로 재귀 탐색
		return parents[a] = find(parents[a]);
	}
}
