import java.util.Scanner;

public class Main {
	static int N, min = Integer.MAX_VALUE;
	static int[][] resource;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		resource = new int[N][2];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			resource[i][0] = sc.nextInt();
			resource[i][1] = sc.nextInt();
		}

		cook(0);
		System.out.println(min);
	}

	private static void cook(int cnt) {
		if (cnt == N) {	
//			for(int i=0; i<N; i++) {
//				System.out.print(isSelected[i]?"■":"□");
//			}
//			System.out.println();
			
			int sour = 1, bitter = 0, pick = 0;
			for (int i = 0; i < N; i++) {
				if (!isSelected[i])
					continue;
				pick++;
				sour *= resource[i][0];
				bitter += resource[i][1];
			}
			if (pick == 0)
				return;
			if (Math.abs(sour - bitter) < min)
				min = Math.abs(sour - bitter);
			return;
		}

		isSelected[cnt] = true;
		cook(cnt + 1);

		isSelected[cnt] = false;
		cook(cnt + 1);
	}
}