import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int K;
	static int[] set;
	static int[] picked;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			sb = new StringBuilder();
			K = sc.nextInt();
			if (K == 0) break;
			
			set = new int[K];
			picked = new int[6];
			for (int i = 0; i < K; i++) {
				set[i] = sc.nextInt();
			}
			sc.nextLine();
			rec(0, 0);
			System.out.println(sb);
		}
	}
	
	private static void rec(int cnt, int start) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(picked[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < set.length; i++) {
			picked[cnt] = set[i];
			rec(cnt + 1, i + 1);
		}
	}
}