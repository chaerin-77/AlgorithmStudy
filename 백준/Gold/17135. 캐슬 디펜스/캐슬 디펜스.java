import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 문제 해결 프로세스
 * 1. 적들을 내리는 것이 아닌 성의 위치를 올려서 계산
 * 2. 궁수의 위치는 조합으로 선정 -> 이후 모두 탐색
 * 3. 가장 가까운 적이 우선이나 거리가 같을 경우 왼쪽의 적 처리 (적 중복 선택 가능)
 *  - 적 발견 시 각 궁수 별 적과의 거리를 계산하여 갱신
 *  - 이 때, 거리가 같을 경우 더 왼쪽의 적인지 확인하여 갱신
 * 4. 이후 적의 좌표를 0으로 바꾸어주고 성을 위로 올린다
 * 5. 하나의 조합 확인 후 모든 정보를 처음의 상태로 원위치 시켜야 함
*/
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
    }
    static int N, M, D, answer = 0, enemyCnt = 0, count = 0;
    static int[][] map, setMap;
    static Location[] Archer;
    static Location[] picked;
    
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
            }
        }
        combi(0, 0);
        System.out.println(answer);
    }
    
    private static void combi(int cnt, int start) {
        if (cnt == 3) {
            war();
            answer = Math.max(answer, count); // 정답 max 값으로 갱신
            Archer[0].r = Archer[1].r = Archer[2].r = N; // 궁수 위치 초기화
            
            // 사용한 맵 초기화
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, setMap[i], 0, M);
            }
            return;
        }
        
        for (int i = start; i < M; i++) {
            // 궁수의 위치 조합으로 뽑기
            Archer[cnt] = new Location(N, i);
            combi(cnt + 1, i + 1);
        }
    }
    
    private static void war() {
        int castle = N;
        count = 0;
        while (castle > 0) {
            // 뽑은 적의 좌표 초기화
            picked[0] = new Location(-1, -1);
            picked[1] = new Location(-1, -1);
            picked[2] = new Location(-1, -1);
            
            // 성 위치에서 사정거리 닿는 곳부터 성 앞까지만 반복문 (0보다 작아진다면 0부터 확인)
            for (int i = (castle - D < 0 ? 0 : castle - D); i < castle; i++) {
                for (int j = 0; j < M; j++) {
                    if (setMap[i][j] == 0) continue;
                    
                    // 적이 있다면 확인
                    pickEnemy(i, j);
                }
            }

            for (int i = 0; i < 3; i++) {
                // 적이 뽑혔고, 해치운 적이 아니라면 0으로 변경 후 적 count 증가
                if (picked[i].r != -1 && setMap[picked[i].r][picked[i].c] != 0) {
                    setMap[picked[i].r][picked[i].c] = 0;
                    count++;
                }
            }

            // 성을 위로 이동하여 적이 아래로 내려온 효과
            castle--;
            Archer[0].r = Archer[1].r = Archer[2].r = castle;
        }
    }
    
    private static void pickEnemy(int er, int ec) {
        for (int a = 0; a < 3; a++) {
            int r = Archer[a].r;
            int c = Archer[a].c;
            int dist = Math.abs(r - er) + Math.abs(c - ec);
            
            // 사정 거리를 벗어날 경우 종료
            if (dist > D) continue;

            // 조준했던 적의 거리보다 현재 적의 거리가 더 가까울 경우 조준 변경
            if (dist < picked[a].dist) {
                picked[a].r = er;
                picked[a].c = ec;
                picked[a].dist = dist;
            } 
            // 거리는 같지만 더 왼쪽에 있는 적이라면 조준 변경
            else if (dist == picked[a].dist && ec < picked[a].c) {
                picked[a].r = er;
                picked[a].c = ec;
            }
        }
    }
}