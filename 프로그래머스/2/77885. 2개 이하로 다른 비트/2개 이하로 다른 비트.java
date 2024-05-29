class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            // 짝수일 경우는 1 증가한 수가 정답
            if (num % 2 == 0) {
                num++;
            }
            // 홀수일 경우 연산
            else {
                String temp = Long.toBinaryString(num);
                temp = "0" + temp;
                for (int j = temp.length() - 1; j >= 0; j--) {
                    char c = temp.charAt(j);
                    if (c == '1') continue;

                    temp = temp.substring(0, j) + "10" + temp.substring(j + 2);
                    break;
                }
                num = Long.parseLong(temp, 2);
            }
            answer[i] = num;
        }
        return answer;
    }
}