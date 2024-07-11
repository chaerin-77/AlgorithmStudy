import java.util.*;
import java.io.*;

/*
 * 문제 해결 프로세스
 * 1. 경로 압축을 통해 갈 수 있는지 여부 확인
 * - 부모가 같으면 이동 가능
 * 2. 시작 점의 부모를 시작으로 각 방문 계획의 부모가 동일할 경우 방문 가능
 */

public class Main {
    static int[][] graph;
    static int[] plan, parents;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        M = Integer.parseInt(br.readLine());
        plan = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 0) continue;
                union(i, j);
            }
        }

        int start = parents[plan[1]];
        for (int i = 2; i <= M; i++) {
            if (parents[plan[i]] == start) continue;
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (rootA < rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}