import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] media;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		media = new int[N][N];
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				media[i][j] = s.charAt(j) - '0';
				if (media[0][0] != media[i][j]) flag = true;
			}
		}
		if (!flag) {
			System.out.println(media[0][0]);
			return;
		}
		rec(0, 0, N / 2);
		System.out.println(sb);
	}
	
	public static void rec (int startR, int startC, int size) {
		sb.append("(");
		for (int r = startR; r < startR + size * 2; r += size ) {
			for (int c = startC; c < startC + size * 2; c += size) {
				int num = media[r][c];
				boolean flag = false;
				A: for (int i = r; i < r + size; i++) {
					for (int j = c; j < c + size; j++) {
						if (media[i][j] != num) {
							flag = true;
							break A;
						}
					}
				}
				if (flag) rec(r, c, size / 2);
				else sb.append(num);
			}
		}
		sb.append(")");
	}
}