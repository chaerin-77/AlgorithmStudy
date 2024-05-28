/*
 * 문제 해결 프로세스
 * 1. 정렬 후 뒤에서부터 자르기
 * 2. 잘려진 부분의 값이 M보다 크거나 같다면 종료
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);
        int height = trees[N - 1] - 1;
        while(true) {
            long temp = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (trees[i] <= height) break;
                temp += trees[i] - height;
            }
            if (temp >= M) break;
            height--;
        }
        System.out.println(height);
    }
}