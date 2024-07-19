import java.util.*;
import java.io.*;

/*
 * 문제 해결 프로세스
 * 1. 청소가 된 빈 칸은 2로 표시
 *
1. 현재 칸이 청소되지 않은 경우 현재 칸 청소
2. 현재 칸의 주변 4칸 중 청소되지 않은 곳이 없음
	-> 바라보는 방향을 유지한 채 한칸 후진 후 1번으로 돌아감
	-> 후진할 수 없으면 작동 중단
3. 현재 칸의 주변 4칸 중 청소되지 않은 칸이 있는 경우
	-> 반시계 방향 90도 회전
	-> 바라보는 방향 기준 앞쪽이 청소되지 않은 칸인 경우 한 칸 전진
	-> 1번으로 돌아감
 */

public class Main {
    static int N, M, answer = 0, dir;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}; // 북동남서
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sR = Integer.parseInt(st.nextToken());
        int sC = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(sR, sC);
        System.out.println(answer);
    }

    private static void clean(int r, int c) {
        while (true) {
            // 1. 현재 칸이 청소되지 않은 경우 현재 칸 청소
            if (map[r][c] == 0) {
                map[r][c] = 2;
                answer++;
//                System.out.println(answer);
//                Print();
            }

            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 0) {
                    flag = true; // 청소되지 않은 곳이 있다면 flag를 true로 변경
                    break;
                }
            }

            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 곳이 없음
            if (!flag) {
                r += dr[(dir + 2) % 4];
                c += dc[(dir + 2) % 4];

                if (r < 0 || c < 0 || r >= N || c >= M || map[r][c] == 1) return; // 후진이 불가능한 경우 종료
            }
            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 칸이 있는 경우
            else {
                dir = (dir + 3) % 4;

                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (map[nr][nc] == 0) {
                    r += dr[dir];
                    c += dc[dir];
                }
            }
        }
    }

    static void Print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}