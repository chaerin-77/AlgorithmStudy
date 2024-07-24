import java.util.*;
import java.io.*;

/*
 * 문제 해결 프로세스
 * 1. 할 수 있는 행동의 경우는
 * - 한 칸 점프하기
 * - 지금 인덱스의 두 배 만큼의 칸에 순간이동하기
 * 2. 자신의 인덱스 만큼을 원래 저장하고 있다가 이동 거리의 두 배와 비교하여 저장
 */

public class Solution {
    public int solution(int n) {
        int cnt = 0;
        while (n > 0) {
            if (n % 2 == 0) n /= 2;
            else {
                n -= 1;
                cnt++;
            }
        }
        return cnt;
    }
}
