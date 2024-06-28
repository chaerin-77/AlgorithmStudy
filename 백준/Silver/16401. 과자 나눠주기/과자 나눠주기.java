import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] snacks = new int[N];
        int max = 0, answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snacks[i]);
        }
        int l = 1, r = max + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += snacks[i] / mid;
            }

            if (cnt < M) {
                r = mid;
            } else {
                answer = Math.max(answer, mid);
                l = mid + 1;
            }
        }
        System.out.println(answer);
    }
}