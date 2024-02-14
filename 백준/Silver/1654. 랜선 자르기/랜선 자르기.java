import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long answer = 0, maxLan = 0;
	static long[] lan;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lan = new long[K];
		for (int i = 0; i < K; i ++) {
			lan[i] = Integer.parseInt(br.readLine());
			if(lan[i] > maxLan) maxLan = lan[i];
		}
		cutting();
		System.out.println(answer);
	}
	
	public static void cutting() {	
		long start = 1, end = maxLan, length = 0;
		
		while(start <= end) {
			length = (start + end) / 2;
			long cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += lan[i] / length;
			}
			if (cnt >= N) {
				if (answer < length) answer = length;
				start = length + 1;
			}
			else end = length - 1;

		}
	}
}