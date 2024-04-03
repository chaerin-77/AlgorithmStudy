import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
			magnet = new int[4][8];
			pointer = new int[4];
			answer = 0;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				visited = new boolean[4];
				visited[num] = true;
				direction = new int[4];
				direction[num] = dir;
				rotate(num, dir);
				for (int j = 0; j < 4; j++) {
					pointer[j] = (pointer[j] - direction[j] + 8) % 8;
				}
			}
			
			for (int i = 0; i < 4; i++) {
				if (magnet[i][pointer[i]] == 1) answer += Math.pow(2, i);
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int num, int dir) {
		if (num - 1 >= 0 && !visited[num - 1]) {
			if (magnet[num][(pointer[num]+6)%8] != magnet[num-1][(pointer[num-1]+2)%8]) {
				visited[num-1] = true;
				direction[num-1] = -dir;
				rotate(num-1, -dir);
			}
		}
		if (num + 1 < 4 && !visited[num + 1]) {
			if (magnet[num][(pointer[num]+2)%8] != magnet[num+1][(pointer[num+1]+6)%8]) {
				visited[num+1] = true;
				direction[num+1] = -dir;
				rotate(num+1, -dir);
			}
		}
	}
}