import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] A;
	static int[] B;
	static boolean[] isSelected;
	static int[] picked;
	static int win, lose;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			win = 0; lose = 0;
			A = new int[9];
			B = new int[9];
			picked = new int[9];
			isSelected = new boolean[9];
			boolean[] check = new boolean[19];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				check[A[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (check[i]) continue;
				B[idx++] = i;
			}
			permu(0);
			System.out.println(String.format("#%d %d %d", t, win, lose));
		}
	}
	
	private static void permu(int cnt) {
		if (cnt == 9) {
			int Acnt = 0, Bcnt = 0;
			for (int i = 0; i < 9; i++) {
				if (A[i] > picked[i]) Acnt += A[i] + picked[i];
				else Bcnt += A[i] + picked[i];
			}
			if (Acnt > Bcnt) win++;
			else if (Acnt < Bcnt) lose++;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			picked[cnt] = B[i];
			permu(cnt + 1);
			isSelected[i] = false;
		}
	}
}