import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. bfs로 탐색을 할 경우 같은 레벨의 같은 알파벳을 확인 못함 -> dfs
 * 2. 탐색 시마다 최대 방문 가능한 count 갯수 갱신
 */

public class Main {
	static int R, C, count = 0;
	static int[][] map;
	static boolean[] check = new boolean[26];
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j) - 'A'; // 문자를 숫자로 변환하여 저장
			}
		}
		check[map[0][0]] = true; // 첫 시작 알파벳 방문 체크
		dfs(0, 0, 1);
		System.out.println(count);
	}
	
	private static void dfs(int r, int c, int cnt) {
		count = Math.max(count, cnt); // 최대 방문 갯수 저장
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
			if(check[map[nr][nc]]) continue;
			
			check[map[nr][nc]] = true;
			dfs(nr, nc, cnt+1);
			check[map[nr][nc]] = false;
		}
	}
}