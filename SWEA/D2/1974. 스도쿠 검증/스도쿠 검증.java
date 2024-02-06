import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 각 조건을 확인하는 배열 생성
			boolean[][] horizon = new boolean[9][10];
			boolean[][] vertical = new boolean[9][10];
			boolean[][] box = new boolean[9][10];
			
			int snum = 0, num = 0, answer = 1;
			for (int i = 0; i < 9; i++) {
				// 행이 3의 배수 일 때 3을 더해 위치 변경
				if (i > 0 && i % 3 == 0) snum += 3;
				
				// 행이 반복될 때마다 초기 값으로 초기화
				num = snum;
				
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					int temp = Integer.parseInt(st.nextToken());
					
					// 3의 배수일 때마다 num 변수 하나 증가
					if (j > 0 && j % 3 == 0) num++;
					
					// 중복되는 수가 나오면 answer을 0으로 설정
					if (horizon[i][temp] || vertical[j][temp] || box[num][temp]) {
						answer = 0;
					}
					
					horizon[i][temp] = true;
					vertical[j][temp] = true;
					box[num][temp] = true;
				}
			}
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
}
