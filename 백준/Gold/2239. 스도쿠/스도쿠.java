import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 문제 해결 프로세스
 * 1. 숫자를 하나씩 선택하면서 진행
 * 2. 이 때 재귀를 이용해서 다음 선택은 다음으로 넘김
 * - 재귀 시 넘길 인자가 애매하기 때문에 0의 위치를 담은 리스트 생성 후 해당 인덱스 넘기기
 * 3. 매 재귀 시 숫자 선정할 때마다 해당 숫자가 가능한지 여부 체크
 */

public class Main {
	static int[][] map = new int[9][9];
	static List<int[]> blank = new ArrayList<>();
	static boolean[][] box = new boolean[9][10];
	static boolean[][] row = new boolean[9][10];
	static boolean[][] col = new boolean[9][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				int num = s.charAt(j) - '0';
				map[i][j] = num;
				if (num == 0) {
					blank.add(new int[] {i, j});
				} else {
					box[(i / 3) * 3 + j / 3][num] = true;
					row[i][num] = true;
					col[j][num] = true;
				}
			}
		}
		sudoku(0);
	}

	public static void sudoku(int idx) {
		if (blank.size() == idx) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.printf("%d", map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		int[] temp = blank.get(idx);
		int r = temp[0];
		int c = temp[1];
		for (int num = 1; num < 10; num++) {
			if (!check(r, c, num)) continue;
			map[r][c] = num;
			box[(r / 3) * 3 + c / 3][num] = true;
			row[r][num] = true;
			col[c][num] = true;
			
			sudoku(idx + 1);
			
			map[r][c] = 0;
			box[(r / 3) * 3 + c / 3][num] = false;
			row[r][num] = false;
			col[c][num] = false;
		}

	}

	public static boolean check(int r, int c, int num) {
		if (box[(r / 3) * 3 + c / 3][num]) return false;
		if (row[r][num]) return false;
		if (col[c][num]) return false;
		
		return true;
	}
}