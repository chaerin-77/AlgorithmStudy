import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			boolean[][] horizon = new boolean[9][10];
			boolean[][] vertical = new boolean[9][10];
			boolean[][] box = new boolean[9][10];
			int snum = 0, num = 0, answer = 1;
			A: for (int i = 0; i < 9; i++) {
				if (i > 0 && i % 3 == 0) snum += 3;
				num = snum;
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (j > 0 && j % 3 == 0) num++;
					if (horizon[i][temp] || vertical[j][temp] || box[num][temp]) {
						answer = 0;
					}
					horizon[i][temp] = true;
					vertical[j][temp] = true;
					box[num][temp] = true;
				}
			}
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
}
