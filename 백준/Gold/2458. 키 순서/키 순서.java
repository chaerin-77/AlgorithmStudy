import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 나의 순서를 알 수 있는 경우 -> 부모의 수와 자식의 수를 모두 알 때
 * 2. 입력 시 역방향 리스트도 생성하여 사용
 */

public class Main {
	static int N, M, answer, cnt;
	static List<Integer>[] students;
	static List<Integer>[] reverse;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		students = new List[N+1];
		reverse = new List[N+1];
		for (int i = 1; i <= N; i++) {
			students[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			students[a].add(b); // 정방향 리스트
			reverse[b].add(a); // 역방향 리스튼
		}
			
		for (int a = 1; a <= N; a++) {
			cnt = 0;
			visited = new boolean[N+1];
			findParents(a); // 부모의 개수
			findChildren(a); // 자식의 개수
				
			// 부모의 개수와 자식의 개수를 더했을 때 N-1과 같다면 정답
			if (cnt == N-1) answer++;
		}
		System.out.println(answer);
	}
	
	private static void findChildren(int a) {
		visited[a] = true;
		for (int b : reverse[a]) {
			if (visited[b]) continue;
			cnt++; // 자기 자신을 셀 수 있기 때문에 여기서 증가
			findChildren(b);
		}
	}

	private static void findParents(int a) {
		visited[a] = true;
		for (int b : students[a]) {
			if (visited[b]) continue;
			cnt++; // 자기 자신을 셀 수 있기 때문에 여기서 증가
			findParents(b);
		}
	}
}