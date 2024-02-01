class Solution {
    public int solution(String name) {
        int answer = 0, cursor = 0;
        int StringSize = name.length();
        int move = StringSize - 1;

        for (int i = 0; i < StringSize; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            cursor = i + 1;
            while(true) {
                if (cursor >= StringSize || name.charAt(cursor) != 'A') break;
                cursor++;
            }
                        // 순서대로 가는 것과, 뒤로 돌아가는 것 중 이동수가 적은 것을 선택
            move = Math.min(move, i * 2 + StringSize - cursor);
            // 2022년 이전 테스트 케이스만 확인하면 여기까지해도 정답처리가 되기 때문에, 이전 정답들에는 여기까지만 정리되어 있지만,
            // BBBBAAAAAAAB 와 같이, 처음부터 뒷부분을 먼저 입력하는 것이 더 빠른 경우까지 고려하려면 아래의 코드가 필요합니다.
            move = Math.min(move, (StringSize - cursor) * 2 + i);
        }

        return answer + move;
    }
}