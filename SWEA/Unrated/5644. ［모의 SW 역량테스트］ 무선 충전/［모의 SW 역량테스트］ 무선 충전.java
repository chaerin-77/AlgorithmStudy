import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int M, A, answer;
	static int[] Amove, Bmove;
	static int[][] BC;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			answer = 0;

			Amove = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				Amove[i] = Integer.parseInt(st.nextToken());
			}

			Bmove = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				Bmove[i] = Integer.parseInt(st.nextToken());
			}

			BC = new int[A][4];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				BC[i][1] = Integer.parseInt(st.nextToken()) - 1;
				BC[i][0] = Integer.parseInt(st.nextToken()) - 1;
				BC[i][2] = Integer.parseInt(st.nextToken());
				BC[i][3] = Integer.parseInt(st.nextToken());
			}
			
			// 충전량이 높은 BC부터 확인할 것이기 때문에 높은 순으로 정렬
			Arrays.sort(BC, (a, b) -> b[3] - a[3]);

			move();

			System.out.println(String.format("#%d %d", t, answer));
		}
	}

	public static void move() {
		// 처음 시작점은 (0,0), (9,9)
		int Ar = 0, Ac = 0, Br = 9, Bc = 9;

		for (int m = 0; m <= M; m++) {
			boolean[] Abc = new boolean[A];
			boolean[] Bbc = new boolean[A];
			int Acnt = 0, Bcnt = 0; // A, B 각각 해당되는 BC의 갯수 count

			Ar += dr[Amove[m]];
			Ac += dc[Amove[m]];
			Br += dr[Bmove[m]];
			Bc += dc[Bmove[m]];

			for (int i = 0; i < A; i++) {
				int br = BC[i][0];
				int bc = BC[i][1];
				int bs = BC[i][2];

				if (Math.abs(br - Ar) + Math.abs(bc - Ac) <= bs) {
					Acnt++;
					Abc[i] = true;
				}
				if (Math.abs(br - Br) + Math.abs(bc - Bc) <= bs) {
					Bcnt++;
					Bbc[i] = true;
				}
			}
			
			// 둘 다 해당되는 BC가 없다면 다음 이동
			if (Acnt == 0 && Bcnt == 0) continue;
			
			// 가능한 두 선택 중 최댓값 answer에 저장
			answer += Math.max(selectBC(Abc, Bbc), selectBC(Bbc, Abc));
		}
	}

	public static int selectBC(boolean[] a, boolean[] b) {
		int select = -1; // 먼저 선택한 사람의 BC 인덱스 저장
		int sum = 0;
		for (int i = 0; i < A; i++) {
			if (a[i]) {
				select = i;
				sum += BC[i][3];
				break;
			}
		}

		for (int i = 0; i < A; i++) {
			// 해당되는 BC 이면서 앞에서 선택한 것이 아니라면
			if (b[i] && i != select) {
				sum += BC[i][3];
				break;
			}
		}
		return sum;
	}
}