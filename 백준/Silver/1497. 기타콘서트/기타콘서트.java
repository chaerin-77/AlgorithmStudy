import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static long max = 0;
    static long[] guitar, picked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        guitar = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String info = st.nextToken();
            for (int j = 0; j < M; j++) {
                char c = info.charAt(j);
                if (c == 'N') continue;
                guitar[i] = guitar[i] | (1L << j);
                max = max | (1L << j); // 모든 기타를 샀을 때 가능한 최대 곡 저장
            }
//            System.out.println(i + ": " + Integer.toBinaryString(guitar[i]));
        }
//        System.out.println("max: " + Integer.toBinaryString(max));
        if (max == 0) {
            System.out.println(-1);
            return;
        }
        comb(0, 0, 0);
        System.out.println(answer);
    }

    private static void comb(int start, int cnt, long flag) {
//        System.out.println("flag: " + Integer.toBinaryString(flag));
        if (cnt > answer) return;

        if (flag == max) {
            answer = cnt;
            return;
        }

        for (int i = start; i < N; i++) {
//            picked[cnt] = i;
            long cur = flag | guitar[i];
            comb(start + 1, cnt + 1, cur);
        }
    }
}