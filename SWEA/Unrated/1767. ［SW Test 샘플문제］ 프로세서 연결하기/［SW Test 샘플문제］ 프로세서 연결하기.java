import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 연결되지 않은 코어들의 좌표를 미리 입력 -> 코어들을 기준으로 dfs
 * 2. 감시와 비슷하게 4방향에 대해 모두 탐색 -> 탐색 후 이전 시도 버전으로 모든 값 되돌리기
 * 3. 4방향에 대해 탐색 시 시간이 오래걸림 -> 전선 설치 전 연결 가능 여부 먼저 확인 후 연결
 * - 이때 4방향이 모두 되지 않아 코어가 선택이 안될 경우 dfs가 돌지 않기 때문에 해당 경우를 위한 탐색도 해줘야함!!!
 */

// 시간: 867ms, 메모리: 108,884KB
public class Solution {
	static class Cell {
		int r, c;

		public Cell(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int N, maxCell, minWire;
	static int[][] maxinos;
	static List<Cell> cells;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			cells = new ArrayList<>();
			maxinos = new int[N][N];
			maxCell = 0;
			minWire = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maxinos[i][j] = Integer.parseInt(st.nextToken());
					
					if (maxinos[i][j] == 0) continue; // 코어가 없을 경우 다음 입력
					if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; // 이미 연결된 코어들은 탐색 X
					cells.add(new Cell(i, j)); // 연결되지 않은 코어들은 리스트에 추가
				}
			}
			connect(0, 0, 0);
			sb.append("#").append(t).append(" ").append(minWire).append("\n");
		}
		System.out.println(sb);
	}

	private static void connect(int cnt, int curCell, int Wire) {
		if (cnt == cells.size()) {
			// 연결된 코어의 값이 늘어났다면 값 변경
			if (curCell > maxCell) {
				minWire = Wire;
				maxCell = curCell;
			}
			// 동일하다면 더 적은 전선의 값으로 변경
			else if (curCell == maxCell) minWire = Math.min(minWire, Wire);
			return;
		}
		
		connect(cnt + 1, curCell, Wire); // 코어를 선택하지 않았을 경우의 dfs 탐색
		
		Cell cur = cells.get(cnt);
		for (int d = 0; d < 4; d++) {
			int r = cur.r;
			int c = cur.c;
			
			// 연결 전 해당 방향 연결 가능 여부 먼저 체크
			int length = checkWire(r, c, d);
			if (length == 0) continue;
			
			setWire(r, c, d, length, 1);
			connect(cnt + 1, curCell + 1, Wire + length);
			
			// 사용한 값 초기화
			setWire(r, c, d, length, 0);
		}
	}
	
	private static int checkWire(int r, int c, int d) {
		int nr = r, nc = c, length = 0;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			// 연결 가능하다면 true 반환
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) return length;
			// 연결 불가능하다면 false 반환
			if (maxinos[nr][nc] == 1) return 0;
			
			length++;
		}
	}

	private static void setWire(int r, int c, int d, int length, int num) {
		int nr = r, nc = c;
		
		for (int i = 0; i < length; i++) {
			nr += dr[d];
			nc += dc[d];
			
			maxinos[nr][nc] = num;
		}
	}
}