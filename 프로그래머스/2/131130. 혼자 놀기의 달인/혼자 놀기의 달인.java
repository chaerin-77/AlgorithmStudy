/*
 * 문제 해결 프로세스
 * 1. 배열의 처음부터 끝까지 각각을 시작 박스로 해서 시도
 * - 이 때 박스 포함 여부 true로 체크
 * - 갯수 count
 * 2. 다시 배열의 처음부터 끝까지를 시작 박스로 해서 시도
 * - 이미 true일 경우 패스
 * - 갯수 count
 * 3. 최댓값 저장
 */

public class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        for (int i = 0; i < cards.length; i++) {
            boolean[] check = new boolean[cards.length];
            int num = i;
            int oneCnt = 0;
            while (!check[num]) {
                check[num] = true;
                num = cards[num] - 1;
                oneCnt++;
            }

            // 2번 상자 그룹 구하기
            for (int j = 0; j < cards.length; j++) {
                int twoCnt = 0;
                if (check[j]) continue; // 이미 포함되어있는 경우 패스
                num = j;
                while (!check[num]) {
                    check[num] = true;
                    num = cards[num] - 1;
                    twoCnt++;
                }
                answer = Math.max(answer, oneCnt * twoCnt);
            }
        }
        return answer;
    }
}
