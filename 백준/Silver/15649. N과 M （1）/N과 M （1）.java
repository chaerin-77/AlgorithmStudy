import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] picked;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        picked = new int[M];
        isSelected = new boolean[N + 1];
        rec(0);
        
		System.out.println(sb);
	}
	private static void rec(int idx) {
		if (idx == M) {
			Arrays.stream(picked).forEach(a->sb.append(a+" "));
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(isSelected[i]) continue;
			picked[idx] = i;
			isSelected[i] = true;
			rec(idx + 1);
			isSelected[i] = false;
		}
		return;
	}
}
