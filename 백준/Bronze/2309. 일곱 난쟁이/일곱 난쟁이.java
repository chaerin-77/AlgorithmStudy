import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int sum = 0;
	static int[] height = new int[9];
	static int[] fake = new int[2];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			height[i] = Integer.parseInt(sc.nextLine());
			sum += height[i];
		}
		sum -= 100;
		Arrays.sort(height);
		rec(0, 0);
	}
	
	private static void rec(int cnt, int start) {
		if (cnt == 2) {
			if (height[fake[0]] + height[fake[1]] != sum) return;
			for (int i = 0; i < 9; i++) {
				if(i == fake[0] || i == fake[1]) continue;
				System.out.println(height[i]);
			}
			System.exit(0);
		}
		for (int i = start; i < 9; i++) {
			fake[cnt] = i;
			rec(cnt + 1, i + 1);
		}
	}
}