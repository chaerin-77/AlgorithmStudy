import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		rec(0, 0);
		System.out.println(sb);
	}
	
	public static void rec(int cnt, int num) {
		if (cnt == N) {
			sb.append(num + "\n");
			return;
		}
		
		int start = 1;
		if (cnt == 0) start = 2;
		A: for (int i = start; i < 10; i++) {
			int temp = num * 10 + i;
			for (int j = 2; j <= temp / 2; j++) {
				if(temp % j == 0) continue A;
			}
			rec(cnt + 1, temp);
		}
	}
}