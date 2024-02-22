import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int V, E;
    static int[] parents; // 서로소 집합
    static Edge[] edgeList; // 간선 정보 저장 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edgeList = new Edge[E]; // E크기의 간선 리스트 생성
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }
            Arrays.sort(edgeList); // 가중치에 따라 정렬

            parents = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                parents[i] = i; // 자기 자신을 부모로 하는 집합 생성
            }

            int cnt = 0;
            long minWeight = 0; // 가중치 합의 자료형은 long
            for (Edge e : edgeList) {
                if (!union(e.from, e.to)) continue; // union에 실패 (부모가 같음) 시 다음 탐색
                minWeight += e.weight; // 가중치 합
                if(++cnt == V - 1) break; // 모든 정점을 탐색했기 때문에 break;
            }
            System.out.println(String.format("#%d %d", t, minWeight));
        }
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootB] = rootA; // 합집합
        return true;
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]); // 경로 압축
    }
}