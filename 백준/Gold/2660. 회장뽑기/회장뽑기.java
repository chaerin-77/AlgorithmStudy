import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 해결 프로세스
 * 1. 각 회원 별로 bfs 탐색 진행
 * 2. 이 때 레벨 별 bfs를 통해 depth 계산
 * - depth == 점수
 */

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[] point;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        point = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            list[a].add(b);
            list[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (point[i] != min) continue;
            cnt++;
        }
        sb.append(min).append(" ").append(cnt).append("\n");
        for (int i = 1; i <= N; i++) {
            if (point[i] != min) continue;
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int i) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        q.offer(i);
        visited[i] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int a = q.poll();
                for (int b : list[a]) {
                    if (visited[b]) continue;
                    q.offer(b);
                    visited[b] = true;
                }
            }
            cnt++;
        }
        point[i] = cnt - 1;
        min = Math.min(min, cnt - 1);
    }
}