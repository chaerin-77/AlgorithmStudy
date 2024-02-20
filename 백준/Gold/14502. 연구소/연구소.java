import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 조합을 통해 벽 설치
 * 2. 벽을 설치하면서 bfs를 하여 안전 구역 계산
 * -> 여기서 안전구역은 "전체 - (벽 + 바이러스)"로 계산한다
 */

public class Main {
	static int N, M, virus, fvirus = 0, answer = 0;
	static int[][] map, setMap;
	static int[] picked;
	static Deque<int[]> q = new ArrayDeque<int[]>();
	static Deque<int[]> tq = new ArrayDeque<int[]>();
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // 원본 지도를 저장할 배열
		setMap = new int[N][M]; // 변경하여 계산할 배열
		picked = new int[3]; // 벽을 설치할 인덱스를 저장할 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				setMap[i][j] = map[i][j];
				if(map[i][j] == 1) fvirus++; // 벽의 갯수를 저장
				else if (map[i][j] == 2) q.offer(new int[] {i, j}); // 바이러스 bfs 큐에 미리 저장
			}
		}
		virus = fvirus + 3; // 벽을 새로 3개 설치할 것이기 때문에 3 증가하여 초기화
		combi(0, 0);
		System.out.println(answer);
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == 3) {
			for(int i = 0; i < 3; i++) {
				int r = picked[i] / M;
				int c = picked[i] % M;
				setMap[r][c] = 1; // 해당 인덱스에 벽 설치
			}
			bfs();
			// 벽과 바이러스의 갯수를 저장했기 때문에 전체 크기에서 해당 수를 빼서 안전구역을 구함
			answer = Math.max(answer, N*M - virus);
			
			// 사용한 지도, virus값 초기화
			virus = fvirus + 3;
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, setMap[i], 0, M);
			}
			return;
		}
		
		for (int i = start; i < N*M; i++) {
			int r = i / M;
			int c = i % M;
			// 지도가 0인 곳만 벽 설치 가능함
			if (setMap[r][c] != 0) continue;
			picked[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
	
	private static void bfs() {
		tq.addAll(q); // 초기 시작 위치들 복사
		while(!tq.isEmpty()) {
			int[] temp = tq.poll();
			int r = temp[0];
			int c = temp[1];
			virus++; // virus값 증가
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (setMap[nr][nc] != 0) continue;
				
				setMap[nr][nc] = 2;
				tq.offer(new int[] {nr, nc});
			}
		}
	}
}