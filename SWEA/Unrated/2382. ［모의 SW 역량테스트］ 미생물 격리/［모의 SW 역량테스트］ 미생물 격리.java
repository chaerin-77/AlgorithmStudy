import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static class Microbe implements Comparable<Microbe>{
        int loc, r, c, num, dir;

        public Microbe(int loc, int r, int c, int num, int dir) {
            super();
            this.loc = loc;
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }
        
        public void move() {
            r += dr[dir];
            c += dc[dir];
            loc = r*N + c;
        }

        @Override
        public int compareTo(Microbe o) {
            if (this.loc == o.loc) {
                return o.num - this.num;
            }
            return this.loc - o.loc;
        }
    }
    static int N, M, K, answer;
    static List<Microbe> microbes;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            microbes = new ArrayList<>();
            answer = 0;
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                microbes.add(new Microbe(N*r+c, r, c, num, dir));
                answer += num;
            }

            for (int i = 0; i < M; i++)
                moveGroup();
            System.out.println(String.format("#%d %d", t, answer));
        }
    }

    private static void moveGroup() {
        // 우선 각 군집 좌표 이동
        for (int i = 0; i < microbes.size(); i++) {
            microbes.get(i).move();
            int r = microbes.get(i).r;
            int c = microbes.get(i).c;
            int num = microbes.get(i).num;
            int dir = microbes.get(i).dir;

            // 약품 구역에 도달했을 경우
            if (r < 1 || c < 1 || r >= N - 1 || c >= N - 1) {
                microbes.get(i).num = num / 2; // 미생물의 수 절반으로 감소

                // 반대 방향으로 전환
                if (dir == 0 || dir == 2) microbes.get(i).dir = dir + 1;
                else microbes.get(i).dir = dir - 1;

                answer = answer - num + (num / 2);
                if (num / 2 == 0) {
                    microbes.remove(i);
                    i--;
                    continue;
                }
            }
        }

        Collections.sort(microbes);

        for (int i = 0; i < microbes.size() - 1; i++) {
            Microbe cur = microbes.get(i);
            Microbe next = microbes.get(i + 1);

            if (next.loc == cur.loc) {
                cur.num += next.num;
                microbes.remove(i + 1);
                i--;
            }
        }
    }
}