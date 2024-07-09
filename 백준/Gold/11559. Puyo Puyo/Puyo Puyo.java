import java.io.*;
import java.util.*;

/*
 * 문제 해결 프로세스
 * 1. 한 반복 당 2개의 프로세스 진행
 * - 4개 연속인지 확인하여 터트리기
 * - 터진 후 빈공간 확인하여 블록 내리기
 * 2. 이 때 터지는 블록이 있는지 boolean에 저장하여 터지지 않을 경우 break;
 */

public class Main {
    static char[][] map = new char[12][6];
    static int answer = 0;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static Queue<int[]> checkQ = new ArrayDeque<>(), bombQ = new ArrayDeque<>();
    static boolean flag = false;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        while (true) {
            flag = false;
            visited = new boolean[12][6];
            // 4개 이상 연쇄되는 뿌요 터트리기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (visited[i][j] || map[i][j] == '.') continue;
                    bfs(i, j);
                }
            }

            // 빈 칸 내리기
            for (int c = 0; c < 6; c++) {
                int cnt = 0;
                for (int r = 11; r >= 0; r--) {
                    if (map[r][c] == '.') cnt++;
                    else if (cnt > 0) {
                        map[r + cnt][c] = map[r][c];
                        map[r][c] = '.';
                    }
                }
            }
//            Print();

            if (!flag) break;
            answer++;
        }
        System.out.println(answer);
    }

    private static void bfs(int r, int c) {
        int cnt = 1;
        checkQ.offer(new int[]{r, c});
        bombQ.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!checkQ.isEmpty()) {
            int[] temp = checkQ.poll();
            int cr = temp[0];
            int cc = temp[1];
            char color = map[cr][cc];
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                // 범위, 방문 체크
                if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6 || visited[nr][nc]) continue;
                // 현재 색상과 같지 않으면 패스
                if (map[nr][nc] != color) continue;
                checkQ.offer(new int[]{nr, nc});
                bombQ.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
                cnt++;
            }
        }
        if (cnt < 4) {
            bombQ.clear();
            return;
        }
        flag = true;
        while (!bombQ.isEmpty()) {
            int[] temp = bombQ.poll();
            int cr = temp[0];
            int cc = temp[1];
            map[cr][cc] = '.';
        }
    }

    static void Print() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}