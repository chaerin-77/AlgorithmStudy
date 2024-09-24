import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0, 1, 1, 1}; // 우, 우하, 하, 좌하
    static int[] dc = {1, 1, 0, -1}; // 방향: 가로, 대각선 우하, 세로, 대각선 좌하

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();
            if (s.equals("end")) break;  // 입력이 end인 경우 종료
            char[][] map = new char[3][3];  // 틱택토 보드
            int Xcnt = 0, Ocnt = 0;  // X와 O의 개수 카운트
            for (int i = 0; i < 9; i++) {
                char temp = s.charAt(i);  // 각 위치에 문자 할당
                map[i / 3][i % 3] = temp;  // 3x3 배열로 변환
                if (temp == 'X') Xcnt++;  // X의 개수 증가
                else if (temp == 'O') Ocnt++;  // O의 개수 증가
            }

            // 1. X와 O의 개수 규칙 확인 (X는 O보다 많거나 같아야 하고, 차이는 최대 1)
            if (Xcnt != Ocnt && Xcnt - 1 != Ocnt) {
                sb.append("invalid").append("\n");
                continue;
            }

            // 2. X와 O가 이겼는지 확인
            boolean Xwin = false, Owin = false;
            for (int i = 0; i < 9; i++) {
                int r = i / 3;
                int c = i % 3;
                if (map[r][c] == '.') continue; // 빈 칸은 스킵
                for (int d = 0; d < 4; d++) {
                    int nr1 = r + dr[d], nc1 = c + dc[d];  // 첫 번째 이동 위치
                    int nr2 = r + 2 * dr[d], nc2 = c + 2 * dc[d];  // 두 번째 이동 위치
                    // 범위를 벗어나는 경우 스킵
                    if (nr1 < 0 || nr1 > 2 || nc1 < 0 || nc1 > 2) continue;
                    if (nr2 < 0 || nr2 > 2 || nc2 < 0 || nc2 > 2) continue;
                    // 연속된 3개의 같은 문자인지 확인
                    if (map[r][c] == map[nr1][nc1] && map[r][c] == map[nr2][nc2]) {
                        if (map[r][c] == 'X') Xwin = true;
                        if (map[r][c] == 'O') Owin = true;
                    }
                }
            }

            // 3. X와 O 둘 다 승리한 경우는 불가능한 상태
            if (Xwin && Owin) {
                sb.append("invalid").append("\n");
                continue;
            }

            // 4. 승리자가 없는 경우 게임이 끝났는지 확인 (X가 이기면 Xcnt > Ocnt, O가 이기면 Xcnt == Ocnt)
            if (Xwin) {
                if (Xcnt == Ocnt + 1) sb.append("valid").append("\n");
                else sb.append("invalid").append("\n");
            } else if (Owin) {
                if (Xcnt == Ocnt) sb.append("valid").append("\n");
                else sb.append("invalid").append("\n");
            } else {
                // 5. 승리자가 없으면, 보드가 꽉 찼는지 확인
                if (Xcnt + Ocnt == 9) sb.append("valid").append("\n");
                else sb.append("invalid").append("\n");
            }
        }

        System.out.println(sb);  // 결과 출력
    }
}