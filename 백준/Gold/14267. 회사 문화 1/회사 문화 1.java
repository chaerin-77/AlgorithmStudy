import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 자료구조 잘 생각하기
 * - 먼저 입력되는 번호부터 차례대로 리스트에 저장 -> n번 배열에는 직속 부하인 다음 번호 저장
 * 2. 해당 번호로 하향식 dfs로 값 합산
 */

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> company = new ArrayList<>();
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            company.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) continue;
            company.get(num).add(i); // 각 번호가 몇 번째 서열인지 저장
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            answer[num] += score; // 각 번호의 칭찬 점수 저장
        }
        dfs(1); // 가장 높은 서열부터 시작
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int idx) {
        for (int nextIdx : company.get(idx)) {
            answer[nextIdx] += answer[idx]; // 자기 밑 사람에게 내 점수 저장
            dfs(nextIdx);
        }
    }

}