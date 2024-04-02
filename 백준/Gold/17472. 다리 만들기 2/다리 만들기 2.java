import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, num = 1;
    static int[][] map;
    static int[][] adjMatrix;
    static boolean[][] visited;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static List<int[]> island = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 섬 라벨링
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    map[i][j] = num;
                    visited[i][j] = true;
                    island.add(new int[] {i, j});
                    findIsland(i, j);
                    num++;
                }
            }
        }
        
        // 다리 만들기
        adjMatrix = new int[num][num];
        for (int i = 1; i < num; i++) Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        visited = new boolean[N][M];
        for (int i = 0; i < island.size(); i++) {
            int[] temp = island.get(i);
            int r = temp[0];
            int c = temp[1];
            linkIsland(r, c);
        }

        // 각 섬이 최단 거리로 연결되어 있는지 확인
        System.out.println(findMinDist());
    }

    private static void findIsland(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 범위, 방문 체크
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;
            if (map[nr][nc] == 0) continue;

            map[nr][nc] = num;
            visited[nr][nc] = true;
            findIsland(nr, nc);
        }
    }

    private static void linkIsland(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 범위, 방문 체크
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;

            if (map[nr][nc] == 0) makeBridge(r, c, d);
            else {
            	visited[nr][nc] = true;
            	linkIsland(nr, nc);
            }
        }
    }

    private static void makeBridge(int r, int c, int d) {
        int length = 0;
        int nr = r;
        int nc = c;
        while (true) {
            nr += dr[d];
            nc += dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) return;
            if (map[nr][nc] != 0) break;
            length++;
        }
        // 다리의 길이가 2 미만이면 함수 종료
        if (length < 2) return;
        
        // 자기 자신을 만났을 경우에 return;
        if (map[nr][nc] == map[r][c]) return;

        // 인접 행렬을 최소값으로 갱신
        adjMatrix[map[r][c]][map[nr][nc]] = Math.min(adjMatrix[map[r][c]][map[nr][nc]], length);
        adjMatrix[map[nr][nc]][map[r][c]] = adjMatrix[map[r][c]][map[nr][nc]];
    }

    private static int findMinDist() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((w1, w2) -> w1[1] - w2[1]);
        int[] minEdge = new int[num];
        boolean[] selected = new boolean[num];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[1] = 0;
        pq.offer(new int[] {1, 0}); // 시작 인덱스, 거리
        
        int V = 0, dist = 0;
        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int island = temp[0];
            int bridge = temp[1];

            if (selected[island]) continue;
            
            selected[island] = true;
            dist += bridge;
            if (++V == num - 1) return dist;
            
            for (int i = 1; i < num; i++) {
                if (selected[i] || adjMatrix[island][i] >= minEdge[i]) continue;
                minEdge[i] = adjMatrix[island][i]; // 최소값 갱신
                pq.offer(new int[] {i, minEdge[i]});
            }
        }

        return -1;
    }
}