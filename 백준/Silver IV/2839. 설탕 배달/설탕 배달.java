import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int pb = 0;
		while (N >= 0) {
			// N이 5로 나누어질 때까지 3씩 감소
			// 5로 나누어질 때가 가장 봉지가 적게 필요
			if (N % 5 == 0) {
				// 5로 나누어 진다면 값 출력 후 return;
				pb += N / 5;
				System.out.println(pb);
				return;
			}
			N -= 3;
			pb++;
		}
		// 그렇지 않다면 -1 출력
		System.out.println(-1);
	}
}