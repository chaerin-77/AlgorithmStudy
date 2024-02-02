import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
등장하는 전차는 사용자의 전차 하나 (적, 아군 X)
맵 밖일 경우 이동 X
포탄 발사 -> 포탄은 벽돌, 강철 벽에 충돌 | 맵 밖까지 직진
                벽에 부딪히면 포탄 소멸
	     벽돌: 파괴, 강철: X
입력
T
H(높이), W(너비) -> H * W 격자판
H줄에 W길이의 문자열
N(사용자 입력)
길이가 N인 문자열

출력
#t 모든 입출력 후의 게임 맵

*문제 해결 프로세스*
1.  U, D, L, R 함수는 하나에 넣어서 (int x, int y)
	if *, #이 아니라면 해당 방향으로 직진
2. S일 경우
	while (*, #을 만나기 전까지) 해당 방향으로 직진후 멈춤
		if * -> 해당 위치 . 으로 변경
 */
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
				if (User.charAt(i) == 'S') shooting();
				else move(User.charAt(i));
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
	
	private static void move (char d) {
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
	
	private static void shooting () {
		int nx = c.X, ny = c.Y;
		while (true) {
			nx += dr[c.dir];
			ny += dc[c.dir];
			
			if (nx >= H || ny >= W || nx < 0 || ny < 0) return;
			if (map[nx][ny] == '*' || map[nx][ny] == '#') break;
		}
		if (map[nx][ny] == '*') map[nx][ny] = '.';
	}
}
// 시간: 185ms, 메모리: 33,408KB