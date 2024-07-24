import java.util.*;
import java.io.*;

/*
 * 문제 해결 프로세스
 * 1. 문자열의 길이가 S(k) = 2 * S(k - 2) + k + 3 으로 증가
 * 2. 해당 숫자를 사용하여 연산
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(moo(N));
    }

    private static String moo(int n) {
        int size = 3;
        int idx = 0;

        if (n == 1) return "m";
        else if (n <= 3) return "o";
        else {
            while (size < n) {
                size = size * 2 + idx + 4;
                idx++;
            }

            int fb = (size - idx - 3) / 2;

            if (size - fb + 1 <= n) return moo(n - size + fb);
            else if (n == fb + 1) return "m";
            else return "o";
        }
    }
}