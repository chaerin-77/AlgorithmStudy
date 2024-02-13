import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] fruit = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			fruit[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(fruit);
		
		for (int i = 0; i < N; i++) {
			// 정렬을 했기 때문에 지금 과일을 못 먹고 다음 과일을 먹을 가능성 X
			// 먹을 수 있다면 길이 증가, 그렇지 않다면 반복문 종료
			if (fruit[i] <= L) L++;
			else break;
		}
		System.out.println(L);
	}
}