import java.util.Scanner;

public class Main {
	static int cnt, N, R, C, answer = -1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		int mapSize = (int) Math.pow(2, N);
		cnt = 0;
		int size = mapSize / 2;
		
		rec(0, 0, size);
		System.out.println(answer);
	}
	
	private static void rec(int startR, int startC, int size) {
		for (int r = startR; r < startR + size * 2; r = r + size) {
			for (int c = startC; c <startC + size * 2; c = c + size) {
				if (r == R  && c == C) {
					answer = cnt;
					return;
				}
				
				if (size != 0 && r + size > R && c + size > C) {
					size = size / 2;
					rec(r, c, size);
				}
				if (answer != -1) return;
				cnt += size * size;
			}
		}
	}
}