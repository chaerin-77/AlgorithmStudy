import java.util.Arrays;
import java.util.Scanner;

/*
N개의 도시들 (사이엔 길이 없을수도)
한 도시에서 출발해 N개의 도시를 거쳐 다시 원래의 도시로 돌아옴
한 번 갔던 도시로는 다시 갈 수 없음
가장 적은 비용의 계획
W[i][j] : i에서 j로 가는 비용 (비대칭)
W[i][i] 는 항상 0
W[i][j] 가 0이라면 가는 길이 없는 것

입력
N
N개의 줄에 비용 행렬

*문제 해결 프로세스*
1. N개의 조합 생성 (중복 X)
2. 비용을 확인하여 길이 없을 경우 return;
3. 값이 있을 경우 전역 변수인 answer에 값 저장
(answer은 값 저장 시 Math.min 사용)
 */

public class Main {
	static int N, answer = Integer.MAX_VALUE;
	static int[][] city;
	static int[] picked;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		city = new int[N][N];
		picked = new int[N + 1];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				city[i][j] = sc.nextInt();
			}
		}
		road(0);
		System.out.println(answer);
	}

	private static void road(int cnt) {
		if (cnt == N) {
			// 마지막 도시에서 시작점으로 돌아가는 것 추가
			picked[N] = picked[0];
			int temp = 0;
			for (int i = 0; i < N; i++) {
				// 가는 길이 없으면 return
				if (city[picked[i]][picked[i+1]] == 0) return;
				temp += city[picked[i]][picked[i+1]];
			}
			answer = Math.min(answer, temp);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			picked[cnt] = i;
			road(cnt + 1);
			isSelected[i] = false;
		}
	}
}