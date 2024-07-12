class Solution {
    static int answer, length, target;
    static int[] numbers;

    public int solution(int[] arr, int num) {
        answer = 0;
        numbers = arr;
        target = num;
        length = arr.length;

        sum(0, 0);

        return answer;
    }

    private void sum(int depth, int calc) {
        if (depth == length) {
            if (calc == target) answer++;
            return;
        }

        sum(depth + 1, calc + numbers[depth]);
        sum(depth + 1, calc - numbers[depth]);
    }
}