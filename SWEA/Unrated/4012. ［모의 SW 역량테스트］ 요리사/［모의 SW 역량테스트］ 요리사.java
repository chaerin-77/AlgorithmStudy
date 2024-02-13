import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
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
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	public static void comb (int cnt, int start) {
		if (cnt == N / 2) {
			List<Integer> Afood = new ArrayList<Integer>();
			List<Integer> Bfood = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				// 선택된 것은 Afood, 선택되지 않은 것은 Bfood에 저장
				if (isSelected[i]) Afood.add(i);
				else Bfood.add(i);
			}
			
			int A = 0, B = 0;
			for (int i = 0; i < N/2; i++) {
				for (int j = i; j < N/2; j++) {
					// 이중 반복문으로 모든 시너지 계산
					A += Synergy[Afood.get(i)][Afood.get(j)] + Synergy[Afood.get(j)][Afood.get(i)];
					B += Synergy[Bfood.get(i)][Bfood.get(j)] + Synergy[Bfood.get(j)][Bfood.get(i)];
				}
			}
			answer = Math.min(answer, Math.abs(A - B));
			return;
		}
		for (int i = start; i < N; i++) {
			// boolean 배열로 해당 요소를 조합에 넣엇는 지 표시
			isSelected[i] = true;
			comb(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	}
}