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
            if (move > i * 2 + (StringSize - cursor)) move = i * 2 + (StringSize - cursor);
            if (move > (StringSize - cursor) * 2 + i) move = (StringSize - cursor) * 2 + i;
        }

        return answer + move;
    }
}