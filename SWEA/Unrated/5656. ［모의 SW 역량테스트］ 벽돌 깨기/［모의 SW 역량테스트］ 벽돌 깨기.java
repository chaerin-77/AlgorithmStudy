import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, bbrick, brick, answer;
	static int[][] map, copymap;
	static int[] picked;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copymap = new int[H][W];
			picked = new int[N];
			brick = 0;
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0)
						brick++;
				}
			}
			bbrick = brick;
			findLoc(0);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void findLoc(int cnt) {
		if (cnt == N) {
			brick = bbrick;
			for (int i = 0; i < H; i++) {
				System.arraycopy(map[i], 0, copymap[i], 0, W);
			}

			A: for (int i = 0; i < N; i++) {
				int marbleR = 0;
				int marbleC = picked[i];

				// 구슬을 떨어트릴 블록 찾기
				while (copymap[marbleR][marbleC] == 0) {
					marbleR++;
					if (marbleR >= H)
						continue A; // 해당 열에 블록이 없다면 continue;
				}
				Game(marbleR, marbleC);
				findUpBrick();
			}
			answer = Math.min(answer, brick);
			return;
		}

		for (int i = 0; i < W; i++) {
			picked[cnt] = i;
			findLoc(cnt + 1);
		}
	}

	private static void Game(int r, int c) {
		int range = copymap[r][c] - 1;
		copymap[r][c] = 0;
		brick--;
		if (brick == 0) return;

		for (int d = 0; d < 4; d++) {
			for (int ra = 1; ra <= range; ra++) {
				int nr = r + dr[d] * ra;
				int nc = c + dc[d] * ra;

				if (!inRange(nr, nc)) continue;

				Game(nr, nc);
			}
		}
	}

	private static void findUpBrick() {
		for (int i = H-1; i >= 0; i--) {
			for (int j = W-1; j >= 0; j--) {
				if (copymap[i][j] != 0) continue;
				
				int nr = i - 1;
				while (nr >= 0) {
					if (copymap[nr][j] != 0) { // 벽돌이면
						copymap[i][j] = copymap[nr][j];
						copymap[nr][j] = 0;
						break;
					}
					nr--;
				}
			}
		}
		
	}

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= H || c >= W || copymap[r][c] == 0)
			return false;
		return true;
	}
}