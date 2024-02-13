import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N, answer;
	static int[][] Synergy;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			Synergy = new int[N][N];
			isSelected = new boolean[N];
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					Synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			comb(0, 0);
			sb.append("#" + t + " " +answer + "\n");
		}
		System.out.println(sb);
	}
	
	public static void comb (int cnt, int start) {
		if (cnt == N / 2) {
			int A = 0, B = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이중 반복문으로 모든 시너지 계산
					if (isSelected[i] && isSelected[j]) A += Synergy[i][j];
					else if (!isSelected[i] && !isSelected[j]) B += Synergy[i][j];
				}
			}
			answer = Math.min(answer, Math.abs(A - B));
			return;
		}
		for (int i = start; i < N; i++) {
			// boolean 배열로 해당 요소를 조합에 넣었는 지 표시
			isSelected[i] = true;
			comb(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	}
}