import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int T = Integer.parseInt(sc.nextLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(sc.nextLine());
			int[][] map = new int[N][N];
			boolean[][] check = new boolean[N][N];
			int dir = 0, num = 1;
			int nr = 0;
			int nc = 0;

			map[0][0] = num++;
			check[0][0] = true;
			while (true) {
				nr += dr[dir];
				nc += dc[dir];
				
				if (num > N*N)
					break;
				if (nr >= N || nc >= N || nr < 0 || nc < 0 || check[nr][nc]) {
					nr -= dr[dir];
					nc -= dc[dir];
					dir = (dir + 1) % 4;
					continue;
				}
				map[nr][nc] = num++;
				check[nr][nc] = true;
			}

			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}