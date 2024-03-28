import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Pipe {
        int r, c;
        char pipe;
        public Pipe(int r, int c, char pipe) {
            super();
            this.r = r;
            this.c = c;
            this.pipe = pipe;
        }
    }
    
    static int R, C;
    static char[][] map;
    static int[][] dr = {{0, 1}, {-1, 0}, {-1, 0}, {0, 1}, {-1, 1}, {0, 0}, {0, 1, 0, -1}};
    static int[][] dc = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}, {0, 0}, {-1, 1}, {1, 0, -1, 0}};
    static Deque<Pipe> pipes = new ArrayDeque<>();
    static Pipe answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                char temp = s.charAt(j);
                map[i][j] = temp;
                if (temp != '.' && temp != 'M' && temp != 'Z') pipes.offer(new Pipe(i, j, map[i][j]));
            }
        }
        bfs();
        System.out.println((answer.r + 1) + " " + (answer.c + 1) + " " + answer.pipe);
    }

    private static void bfs() {
        while (!pipes.isEmpty()) {
            Pipe pipe = pipes.poll();
            int r = pipe.r;
            int c = pipe.c;
            char type = pipe.pipe;

            int idx = checkIdx(type);
            for (int d = 0; d < dr[idx].length; d++) {
                int nr = r + dr[idx][d];
                int nc = c + dc[idx][d];

                // 범위 체크
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                
                // 연결되어 있지 않음
                if (map[nr][nc] == '.') {
                    char t = setPipe(nr, nc);
                    answer = new Pipe (nr, nc, t);
                    return;
                }
            }
        }
    }

    private static char setPipe(int r, int c) {
        boolean[] direction = new boolean[4];
        //0. 상 탐색
        int nr = r-1;
        int nc = c;
        if(!(nr < 0 || nc < 0 || nr >= R || nc >= C) && (map[nr][nc] == '|' || map[nr][nc] == '1' || map[nr][nc] == '4' || map[nr][nc] == '+'))
            direction[0] = true;

        //1. 하 탐색
        nr = r+1;
        if(!(nr < 0 || nc < 0 || nr >= R || nc >= C) && (map[nr][nc] == '|' || map[nr][nc] == '2' || map[nr][nc] == '3' || map[nr][nc] == '+'))
            direction[1] = true;

        //2. 좌 탐색
        nr = r;
        nc = c-1;
        if(!(nr < 0 || nc < 0 || nr >= R || nc >= C) && (map[nr][nc] == '-' || map[nr][nc] == '1' || map[nr][nc] == '2' || map[nr][nc] == '+'))
            direction[2] = true;

        //3. 우 탐색
        nc = c+1;
        if(!(nr < 0 || nc < 0 || nr >= R || nc >= C) && (map[nr][nc] == '-' || map[nr][nc] == '4' || map[nr][nc] == '3' || map[nr][nc] == '+'))
            direction[3] = true;

        if(direction[0] && direction[1] && direction[2] && direction[3]) return '+';
        if(direction[1] && direction[3]) return '1';
        if(direction[0] && direction[3]) return '2';
        if(direction[0] && direction[2]) return '3';
        if(direction[1] && direction[2]) return '4';
        if(direction[0] && direction[1]) return '|';
        if(direction[2] && direction[3]) return '-';

        return '.';
    }

    private static int checkIdx(char type) {
        switch (type) {
        case '1':
            return 0;
        case '2':
            return 1;
        case '3':
            return 2;
        case '4':
            return 3;
        case '|':
            return 4;
        case '-':
            return 5;
        default:
            return 6;
        }
    }
}