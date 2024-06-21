import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 왼쪽과 오른쪽을 확인하여 현재 칸에 얼마나 물이 고일 수 있는지 확인
 * 2. 이후 더 낮은 높이 만큼 물이 고일 수 있음
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] rain = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            rain[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 1; i < W - 1; i++) {
            int maxL = 0, maxR = 0, height = 0;

            // 왼쪽에서 가장 높은 높이 찾기
            for (int j = 0; j < i; j++) {
                maxL = Math.max(maxL, rain[j]);
            }

            // 오른쪽에서 가장 높은 높이 찾기
            for (int j = i + 1; j < W; j++) {
                maxR = Math.max(maxR, rain[j]);
            }
            height = Math.min(maxL, maxR);

            if (height > rain[i]) {
                answer += (height - rain[i]);
            }
        }
        System.out.println(answer);
    }
}