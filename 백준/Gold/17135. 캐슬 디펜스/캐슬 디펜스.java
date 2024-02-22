import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Location {
        int r, c, dist = 11;
        
        // 궁수 생성 시 사용
        public Location(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
        
        // 뽑은 적 저장 시 사용
        public Location(int r, int c, int dist) {
            super();
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Location [r=" + r + ", c=" + c + ", dist=" + dist + "]";
        }
    }
    static int N, M, D, answer = 0, enemyCnt = 0, count = 0;
    static int[][] map, setMap;
    static Location[] Archer;
    static Location[] picked;

    static int check = 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        setMap = new int[N][M];
        Archer = new Location[3];
        picked = new Location[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                setMap[i][j] = map[i][j];
                if (map[i][j] == 1) enemyCnt++;
            }
        }
        combi(0, 0);
        System.out.println(answer);
    }
    
    private static void combi(int cnt, int start) {
        if (cnt == 3) {
//            System.out.println("Archer: " + Arrays.toString(Archer));
            war();
            answer = Math.max(answer, count);
            Archer[0].r = Archer[1].r = Archer[2].r = N;
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, setMap[i], 0, M);
            }
            return;
        }
        
        for (int i = start; i < M; i++) {
            Archer[cnt] = new Location(N, i);
            combi(cnt + 1, i + 1);
        }
    }
    
    private static void war() {
        int castle = N;
        count = 0;
        while (castle > 0) {
            picked[0] = new Location(-1, -1);
            picked[1] = new Location(-1, -1);
            picked[2] = new Location(-1, -1);

            for (int i = (castle - D < 0 ? 0 : castle - D); i < castle; i++) {
                for (int j = 0; j < M; j++) {
                    if (setMap[i][j] == 0) continue;
                    pickEnemy(i, j);
                }
            }

//            System.out.println("picked: " + Arrays.toString(picked));
            for (int i = 0; i < 3; i++) {
                if (picked[i].r != -1 && setMap[picked[i].r][picked[i].c] != 0) {
                    setMap[picked[i].r][picked[i].c] = 0;
                    count++;
//                    System.out.println("count: " + count);
                }
            }

            // 성을 위로 이동하여 적이 아래로 내려온 효과
            castle--;
            Archer[0].r = Archer[1].r = Archer[2].r = castle;
        }

//        System.out.println(count);
    }
    
    private static void pickEnemy(int er, int ec) {
        for (int a = 0; a < 3; a++) {
            int r = Archer[a].r;
            int c = Archer[a].c;
//            System.out.println("궁수: " + r + " " + c);
//            System.out.println("적: " + er + " " + ec);
            int dist = Math.abs(r - er) + Math.abs(c - ec);
//            System.out.println("dist: " + dist);
            // 사정 거리를 벗어날 경우 종료
            if (dist > D) continue;

            // 조준했던 적의 거리보다 현재 적의 거리가 더 가까울 경우 조준 변경
            if (dist < picked[a].dist) {
                picked[a].r = er;
                picked[a].c = ec;
                picked[a].dist = dist;
//                System.out.println("채택!!");
            } else if (dist == picked[a].dist && ec < picked[a].c) {
                picked[a].r = er;
                picked[a].c = ec;
//                System.out.println("채택!!");
            }
        }
    }
    
    public static void Print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d ", setMap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}