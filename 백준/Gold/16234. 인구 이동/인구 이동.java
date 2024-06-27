/*
 * 문제 해결 프로세스
 * 1. 모든 좌표에서 bfs 시작. 이 때 큐를 2개 사용해서 좌표 저장
 * - bfs로 국경이 열린 곳의 좌표를 2번째 큐에 저장하고 인구합과 칸 수를 셈
 * - 이후 나눠진 인구를 새로운 맵에 표시
 * 2. 해당되지 않는 좌표는 바로 새로운 맵에 복사
 * 3. 인구 이동 발생 여부는 bfs 시작 전 사방을 체크하는 것으로 확인
 * - boolean 변수를 사용하여 확인
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R, days = 0;
    static int[][] map, nextMap;
    static boolean[][] visited;
    static boolean isMoved = false;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static Queue<int[]> q = new ArrayDeque<>();
    static Queue<int[]> moveQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        nextMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            System.arraycopy(map[i], 0, nextMap[i], 0, N);
        }

        while (true) {
            visited = new boolean[N][N];
            isMoved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || !move(i, j)) continue;
                    visited[i][j] = true;
                    isMoved = true;
                    bfs(i, j);
//                    System.out.println();
                }
            }
            if (!isMoved) break;
            days++;
            for (int i = 0; i < N; i++) {
                System.arraycopy(nextMap[i], 0, map[i], 0, N);
            }
//            System.out.println(days);
//            print();
        }
        System.out.println(days);
    }

    private static void bfs(int r, int c) {
        q.offer(new int[]{r, c});
        moveQ.offer(new int[]{r, c});
        int count = 1, sum = 0;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cr = temp[0];
            int cc = temp[1];
            int num = map[cr][cc];
            sum += map[cr][cc];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
                if (Math.abs(num - map[nr][nc]) > R || Math.abs(num - map[nr][nc]) < L) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                moveQ.offer(new int[]{nr, nc});
                count++;
            }
        }

        int calc = sum / count;
        while (!moveQ.isEmpty()) {
            int[] temp = moveQ.poll();
            int cr = temp[0];
            int cc = temp[1];
            nextMap[cr][cc] = calc;
        }
    }

    private static boolean move(int r, int c) {
        int num = map[r][c];
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
            if (Math.abs(num - map[nr][nc]) > R || Math.abs(num - map[nr][nc]) < L) continue;
            return true;
        }
        return false;
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}