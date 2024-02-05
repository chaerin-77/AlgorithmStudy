import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<Integer> secret = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				secret.add(Integer.parseInt(st.nextToken()));
			}
			
			int command = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < command; i++) {
				st.nextToken();
				int startIdx = Integer.parseInt(st.nextToken());
				int insertCnt = Integer.parseInt(st.nextToken());
				
				for (int j = 0; j < insertCnt; j++) {
					secret.add(startIdx++, Integer.parseInt(st.nextToken()));
				}
			}
			sb.append("#" + t);
			for (int i = 0; i < 10; i++)
				sb.append(" " + secret.get(i));
			System.out.println(sb);
		}
	}
}