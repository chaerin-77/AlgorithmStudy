import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int H, W, N;
	static char[][] map;
	static String User;
	static Car c;
	// TBLR 순으로 0123
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Car {
		int X, Y, dir;
		char[] show = {'^', 'v', '<', '>'};
		public Car(int x, int y, int d) {
			X = x;
			Y = y;
			dir = d;
		}
		
		public char Show (int d) {
			return show[d];
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '^') c = new Car(i, j, 0);
					else if (map[i][j] == 'v') c = new Car(i, j, 1);
					else if (map[i][j] == '<') c = new Car(i, j, 2);
					else if (map[i][j] == '>') c = new Car(i, j, 3);
					else continue;
					map[i][j] = '.'; // 전차 기존 위치 평지로 설정
				}
			}
			N = Integer.parseInt(br.readLine());
			User = br.readLine();
			
			for (int i = 0; i < N; i++) {
				if (User.charAt(i) == 'S') shooting(c.X, c.Y);
				else move(c.X, c.Y, User.charAt(i));
			}
			map[c.X][c.Y] = c.Show(c.dir);
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	
	private static void move (int x, int y, char d) {
		if (d == 'U') c.dir = 0;
		else if (d == 'D') c.dir = 1;
		else if (d == 'L') c.dir = 2;
		else if (d == 'R') c.dir = 3;
		
		int nx = c.X + dr[c.dir];
		int ny = c.Y + dc[c.dir];
		
		if (nx >= H || ny >= W || nx < 0 || ny < 0) return;
		if (map[nx][ny] == '#' || map[nx][ny] == '*' || map[nx][ny] == '-') return;
		
		c.X = nx;
		c.Y = ny;
	}
	
	private static void shooting (int x, int y) {
		int nx = x, ny = y;
		while (true) {
			nx += dr[c.dir];
			ny += dc[c.dir];
			
			if (nx >= H || ny >= W || nx < 0 || ny < 0) return;
			if (map[nx][ny] == '*' || map[nx][ny] == '#') break;
		}
		if (map[nx][ny] == '*') map[nx][ny] = '.';
	}
}