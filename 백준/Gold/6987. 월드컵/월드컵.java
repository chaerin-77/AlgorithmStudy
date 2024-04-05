import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean flag;
    static int[][] matchInfo = new int[][] {
            {0,1},
            {0,2},
            {0,3},
            {0,4},
            {0,5},
            {1,2},
            {1,3},
            {1,4},
            {1,5},
            {2,3},
            {2,4},
            {2,5},
            {3,4},
            {3,5},
            {4,5}
    };
    static int[][] input = new int[6][3];
    static int[][] result = new int[6][3]; // 승 무 패

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        A: for (int t = 0; t < 4; t++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                input[i][0] = Integer.parseInt(st.nextToken());
                input[i][1] = Integer.parseInt(st.nextToken());
                input[i][2] = Integer.parseInt(st.nextToken());
                if (input[i][0] + input[i][1] + input[i][2] > 5) {
                    sb.append(0).append(" ");
                    continue A;
                }
            }
            flag = false;
            checkResult(0);
            sb.append(flag ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }

    private static void checkResult(int cnt) {
        if (cnt == 15) {
            flag = true;
            return;
        }

        int A = matchInfo[cnt][0];
        int B = matchInfo[cnt][1];
        
        // A가 승
        if (result[A][0] < input[A][0] && result[B][2] < input[B][2]) {
            result[A][0]++;
            result[B][2]++;;
            checkResult(cnt + 1);
            result[B][2]--;
            result[A][0]--;
            if (flag) return; // 완성 시 종료
        }

        // 무승부
        if (result[A][1] < input[A][1] && result[B][1] < input[B][1]) {
            result[A][1]++;
            result[B][1]++;;
            checkResult(cnt + 1);
            result[B][1]--;
            result[A][1]--;
            if (flag) return; // 완성 시 종료
        }

        // B가 승리
        if (result[A][2] < input[A][2] && result[B][0] < input[B][0]) {
            result[A][2]++;
            result[B][0]++;;
            checkResult(cnt + 1);
            result[B][0]--;
            result[A][2]--;
            if (flag) return; // 완성 시 종료
        }
    }
}