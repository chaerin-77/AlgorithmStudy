import java.util.Scanner;

class Solution {
    public int[] solution(int N) {
        int[][] arr = new int[N][N];
        int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, -1 } };

        int row = 0, col = 0, num = 1, turn = 0;

        for (int i = 0; i < N; i++) {
            for (int j = N - 1 - turn; j >= 0; j--) {
                arr[row][col] = num++;

                if (j == 0) turn++;
                row += deltas[turn % 3][0];
                col += deltas[turn % 3][1];
            }
        }

        int[] answer = new int[num - 1];
        num = 0;
        A: for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 0) continue A;
                answer[num] = arr[i][j];
                num++;
            }
        }
        return answer;
    }
}