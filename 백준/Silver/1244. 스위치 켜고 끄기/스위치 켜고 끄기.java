import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int S = Integer.parseInt(br.readLine()); // 스위치 갯수
		int[] switches = new int[S + 1]; // 스위치 상태 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= S; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		int N = Integer.parseInt(br.readLine()); // 학생 수
		for (int i = 0; i < N; i++) { // 학생 성별, 스위치 번호 입력
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				for (int s = number; s <= S; s += number) {
					switches[s] = Math.abs(switches[s] - 1);
				}
			} else {
				switches[number] = Math.abs(switches[number] - 1);
				for (int s = number + 1; s <= S; s++) {
					if (number - (s - number) < 1) break;
					if (switches[number - (s - number)] == switches[s]) {
						switches[s] = Math.abs(switches[s] - 1);
						switches[number - (s - number)] = Math.abs(switches[number - (s - number)] - 1); 
					} else break;
				}
			}
		}
		for (int i = 1; i <= S; i++) {
			sb.append(switches[i] + " ");
			if (i % 20 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}