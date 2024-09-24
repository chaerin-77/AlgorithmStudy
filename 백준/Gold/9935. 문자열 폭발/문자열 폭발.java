import java.util.*;
import java.io.*;

/*
 * 문제 해결 프로세스
 * 1. 큐를 2개 사용해서 추가하며 비교
 * 2.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] words = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] bomb = st.nextToken().toCharArray();

        Stack<Character> s = new Stack<>();
        for (int i = 0; i < words.length; i++) {
            s.push(words[i]);
            if (s.size() < bomb.length) continue;

            Stack<Character> check = new Stack<>();
            for (int j = bomb.length - 1; j >= 0; j--) {
                check.push(s.pop());
                if (check.peek() != bomb[j]) {
                    while (!check.isEmpty()) s.push(check.pop());
                    break;
                }
            }
        }
        if (s.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while (!s.isEmpty()) sb.append(s.pop());
            System.out.println(sb.reverse());
        }
    }
}