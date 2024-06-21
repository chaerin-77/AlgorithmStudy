import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 문제 해결 프로세스
 * 1. Set에 저장하며 중복 확인
 * 2. 순서는 % 연산을 이용하여 지정
 */

public class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        int num = 0;
        int[] player = new int[n];
        player[0] = 1;
        for (int i = 1; i < words.length; i++) {
            num = (num + 1) % n;
            player[num]++;
            // 중복 확인
            if (set.contains(words[i])) {
                answer[0] = num + 1;
                answer[1] = player[num];
                break;
            }

            // 끝말잇기 확인
            char end = words[i - 1].charAt(words[i - 1].length() - 1);
            char start = words[i].charAt(0);
            if (end != start) {
                answer[0] = num + 1;
                answer[1] = player[num];
                break;
            }

            set.add(words[i]);
        }

        return answer;
    }
}
