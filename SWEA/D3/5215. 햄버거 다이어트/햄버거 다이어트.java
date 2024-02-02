import java.util.Scanner;

public class Solution {
	static int N, L, answer;
	static int[][] ingredient;
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			L = sc.nextInt();
			isSelected = new boolean[N];
			ingredient = new int[N][2];
			answer = 0;
			for (int i = 0; i < N; i++) {
				ingredient[i][0] = sc.nextInt();
				ingredient[i][1] = sc.nextInt();
			}
			calory(0);
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	private static void calory (int cnt) {
		if (cnt == N) {
			int Ssum = 0, Csum = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) continue;
				Ssum += ingredient[i][0];
				Csum += ingredient[i][1];
			}
			if (Csum <= L) answer = Math.max(answer, Ssum);
			return;
		}
		isSelected[cnt] = true;
		calory(cnt + 1);
		isSelected[cnt] = false;
		calory(cnt + 1);
	}
}