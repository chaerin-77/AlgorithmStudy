import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char[] input, picked;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		picked = new char[L]; // 최종으로 선택할 배열 - 길이 L
		input = new char[C]; // 입력 받을 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		password(0, 0);
		System.out.println(sb);
	}
	
	public static void password(int cnt, int start) {
		if (cnt == L) {
			if (check(picked)) {
				Arrays.sort(picked);
				sb.append(String.valueOf(picked));
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			picked[cnt] = input[i];
			password(cnt + 1, i + 1);
		}
	}
	
	public static boolean check (char[] picked) {
		String test = "aeiou";
		int vowel = 0;
		for (int i = 0; i < L; i++) {
			// valueof는 char을 string으로 변환
			if(test.contains(String.valueOf(picked[i]))) vowel ++;
		}
		
		if (vowel < 1 || L - vowel < 2) return false;
		else return true;
	}
}