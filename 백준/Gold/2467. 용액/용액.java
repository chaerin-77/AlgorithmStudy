/*
 * 문제 해결 프로세스
 * 1. 오름차순으로 입력되기 때문에 정렬 필요 없음
 * 2. 왼쪽과 오른쪽에 포인터를 두고 이동시키며 값 합산
 * - 0 보다 작을 경우 왼쪽 포인터 증가
 * - 0 보다 클 경우 오른쪽 포인터 감소
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] ph = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ph[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = N - 1, min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (l < r && min != 0) {
            int sum = Math.abs(ph[l] + ph[r]);
            if (sum < min) {
                min = sum;
                answer = new int[]{ph[l], ph[r]};
            }
            if (ph[l] + ph[r] < 0) l++;
            else r--;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}