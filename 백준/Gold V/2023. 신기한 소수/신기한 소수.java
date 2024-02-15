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
		
		// 맨 앞자리에는 1을 놓지 않기 위한 start 값 설정
		int start = 1;
		if (cnt == 0) start = 2;
		
		A: for (int i = start; i < 10; i++) {
			// 다음 숫자 만들기
			int temp = num * 10 + i;
			for (int j = 2; j <= temp / 2; j++) {
				// 나누어 떨어지는 수가 있다면 다음 숫자 생성
				if(temp % j == 0) continue A;
			}
			rec(cnt + 1, temp);
		}
	}
}