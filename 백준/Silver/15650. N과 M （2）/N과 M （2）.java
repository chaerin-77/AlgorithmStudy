import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] numbers;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		
		rec(0, 1);
		System.out.println(sb);
	}
	
	private static void rec(int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			rec(cnt + 1, i + 1);
		}
	}
}