import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 초기 세팅은 이미 (0,0)에 되어있기 때문에 1초부터 시작
 * 2. 해당 초까지 이동을 해야하므로 현재 시간 <= 방향 전환 시간 까지 이동 -> 이동 후 방향 전환
 * 	- 방향 전환은 왼쪽 = -1, 오른쪽 = 1로 하여 "(현재 방향 + 4 + 전환할 방향) % 4" 로 계산
 * 3. 마지막은 벽이나 자기 자신에 부딪힐 때까지 무한 반복 해야하므로 적절히 처리 -> 최댓값으로 저장
 */

public class Main {
	static int N, K, L, endTime = 1;
	static int[][] board, move;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		board[0][0] = 1; // 초기 뱀의 위치는 1로 설정
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 2; // 사과의 위치는 2로 표시
		}
		
		L = Integer.parseInt(br.readLine());
		move = new int[L + 1][2]; // 마지막 이동은 무한 반복을 하기 위해 0 하나 추가
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			move[i][0] = sec;
			move[i][1] = (dir == 'D' ? 1 : -1); // 오른쪽일 경우 1, 왼쪽일 경우 -1로 저장
		}
		Dummy();
		System.out.println(endTime);
	}
	
	private static void Dummy() {
		Deque<int[]> snake = new ArrayDeque<int[]>();
		snake.offer(new int[] {0, 0});
		int dir = 0, r = 0, c = 0;
		
		for (int i = 0; i <= L; i++) {
			int time = move[i][0];
			if (time == 0) time = 10_000; // 마지막 이동은 무한 반복을 하기 위함
			
			while (endTime <= time) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				// 벽이에 부딪혔을 경우 함수 종료
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) return;
				if (board[nr][nc] == 1) return;
				
				// 사과가 없을 경우 현재 좌표 0으로 표시 (몸 길이 줄임)
				if (board[nr][nc] != 2) {
					int[] tail = snake.poll();
					int tr = tail[0];
					int tc = tail[1];
					board[tr][tc] = 0;
				}
				
				snake.offer(new int[] {nr, nc});
				board[nr][nc] = 1;
				
				endTime++; // 이동 후 게임 시간 증가
				
				r = nr;
				c = nc;
			}
			
			// 해당 시간 종료 후 방향 전환
			dir = (dir + move[i][1] + 4) % 4;
		}
	}
}