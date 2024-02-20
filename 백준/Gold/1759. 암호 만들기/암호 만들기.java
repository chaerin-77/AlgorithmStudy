import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int L, C;
	static char[] character, picked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		character = new char[C];
		picked = new char[L];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			character[i] = st.nextToken().charAt(0);
		}
		// 사전순으로 빠른 알파벳부터 찾기 위해 정렬
		Arrays.sort(character);
		comb(0, 0);
		System.out.println(sb);
	}
	
	public static void comb (int cnt, int start) {
		if (cnt == L) {
			String vowel = "aeiou";
			int v = 0, c = 0;
			for (int i = 0; i < L; i++) {
				// 모음과 자음 갯수 count
				if (vowel.contains(String.valueOf(picked[i]))) v++;
				else c++;
			}
			// 모음이 1개 이상, 자음이 2개 이상이면 출력
			if(v > 0 && c > 1) sb.append(String.valueOf(picked) + "\n");
			
			return;
		}
		
		for (int i = start; i < C; i++) {
			picked[cnt] = character[i];
			comb(cnt + 1, i + 1);
		}
	}
}