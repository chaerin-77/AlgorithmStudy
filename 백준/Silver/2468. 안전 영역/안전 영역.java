/*
 * 문제
 * - 비가 내렸을 때 물에 잠기지 않는 안전 영역 최대 개수 조사
 *
 * 문제 해결 프로세스
 * 1. 입력과 동시에 지대의 최소 높이, 최대 높이 저장
 * - 반복문의 시작 조건과 종료 조건 확인 위함
 * 2. 숫자를 하나씩 늘리며 물에 잠기도록 함
 * - 이때 boolean 배열을 사용하여 물에 잠기지 않은 곳 따로 확인
 * 3. bfs로 방문 체크를 하며 덩어리 확인
 * - 최대 개수 저장
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, answer = 1, maxH = 0, minH = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] flood, visited;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        flood = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);
            }
        }
        // 물에 잠기게 하기
        for (int h = minH; h < maxH; h++) {
            int cur = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (flood[i][j]) continue;
                    if (map[i][j] == h) flood[i][j] = true;
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 이미 잠겼거나 방문했을 경우 다음 탐색
                    if (flood[i][j] || visited[i][j]) continue;
                    bfs(i, j);
                    cur++;
                }
            }
            answer = Math.max(answer, cur);
        }
        System.out.println(answer);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int r = temp[0];
            int c = temp[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (visited[nr][nc] || flood[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }
}