import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 
 */

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64]; // 열쇠 소지 여부를 비트마스킹으로 표시 -> a~f: 64
		int sr = 0, sc = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					sr = i;
					sc = j;
					map[i][j] = '.';
				}
			}
		}
		System.out.println(bfs(sr, sc));
	}
	private static int bfs(int sr, int sc) {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {sr, sc, 0}); // r, c, 열쇠 소지 여부
		visited[sr][sc][0] =true;
		
		int depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] temp = q.poll();
				int r = temp[0];
				int c = temp[1];
				int key = temp[2];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위, 벽, 방문 체크
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
					if (visited[nr][nc][key] || map[nr][nc] == '#') continue;
					
					// 출구에 도착하면 return;
					if (map[nr][nc] == '1') return depth + 1;
					
					if (map[nr][nc] == '.') {
						q.offer(new int[] {nr, nc, key});
						visited[nr][nc][key] = true;
						continue;
					}
					
					// 해당 위치가 문이라면
					if (map[nr][nc] - 'a' < 0) {
						// 열쇠가 없을 경우는 다음 탐색
						if ((key & (1 << (map[nr][nc] - 'A'))) == 0) continue;
						
						// 열쇠가 있을 경우엔 큐에 추가
						q.offer(new int[] {nr, nc, key});
						visited[nr][nc][key] = true;
					} else {
						// 열쇠 정보 저장
						int k = key | (1 << (map[nr][nc] - 'a'));
						q.offer(new int[] {nr, nc, k});
						visited[nr][nc][k] = true;
					}
				}
			}
			depth++;
		}
		return -1;
	}
}