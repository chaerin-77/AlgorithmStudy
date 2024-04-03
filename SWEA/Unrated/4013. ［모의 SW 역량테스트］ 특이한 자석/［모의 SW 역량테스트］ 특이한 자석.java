import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 문제 해결 프로세스
 * 1. 포인터를 사용하여 직접 회전 시키지 않고 회전한 것처럼 연산
 * 2. 왼쪽 톱니와 오른쪽 톱니를 구분하여 확인
 * 3. 회전을 시키며 회전 가능 여부 체크 불가
 * - 회전 방향을 따로 저장한 후 한 번에 연산
 */

public class Solution {
	static int answer;
	static int[] pointer, direction;
	static int[][] magnet;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());
			magnet = new int[4][8]; // 입력 받을 배열
			pointer = new int[4]; // 빨간 화살표 위치를 가리키는 인덱스를 가질 배열
			answer = 0;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1; // 0번부터 시작하도록 함
				int dir = Integer.parseInt(st.nextToken());
				visited = new boolean[4]; // 이미 돌린 자석을 또 돌리지 않기 위함
				visited[num] = true;
				direction = new int[4]; // 방향을 저장해두고 한 번에 돌리기 위함
				direction[num] = dir;
				rotate(num, dir);
				for (int j = 0; j < 4; j++) {
					// 반복문을 돌며 빨간색 화살표가 가리킬 인덱스 변경
					pointer[j] = (pointer[j] - direction[j] + 8) % 8;
				}
			}
			
			// 점수 계산
			for (int i = 0; i < 4; i++) {
				if (magnet[i][pointer[i]] == 1) answer += Math.pow(2, i);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int num, int dir) {
		// 왼쪽 자석
		if (num - 1 >= 0 && !visited[num - 1]) {
			// 두 자석이 마주보는 위치를 인덱스로 확인하여 다를 경우 회전
			if (magnet[num][(pointer[num]+6)%8] != magnet[num-1][(pointer[num-1]+2)%8]) {
				visited[num-1] = true;
				direction[num-1] = -dir;
				rotate(num-1, -dir);
			}
		}
		
		// 오른쪽 자석
		if (num + 1 < 4 && !visited[num + 1]) {
			// 두 자석이 마주보는 위치를 인덱스로 확인하여 다를 경우 회전
			if (magnet[num][(pointer[num]+2)%8] != magnet[num+1][(pointer[num+1]+6)%8]) {
				visited[num+1] = true;
				direction[num+1] = -dir;
				rotate(num+1, -dir);
			}
		}
	}
}