import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 해결 프로세스
 * 1. 입력 시에는 HashMap의 merge함수를 사용
 * - 기존의 값이 있다면 식으로 계산, 없다면 해당 값 추가
 * 2. 입력 후 출력에는 pq로 정렬하여 사용
 * - 클래스를 생성하여 comparable 정의 후 정렬
 */

public class Main {
    static class Word implements Comparable<Word> {
        String word;
        int count;

        public Word(int count, String word) {
            this.count = count;
            this.word = word;
        }

        @Override
        public int compareTo(Word o) {
            if (this.count == o.count) {
                if (this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word);
                }
                return Integer.compare(o.word.length(), this.word.length());
            }
            return Integer.compare(o.count, this.count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() < M) continue;
            map.merge(s, 1, Integer::sum); // Integer::sum을 사용해서 기존 값과 새 값 합산
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();
        for (String word : map.keySet()) {
            pq.add(new Word(map.get(word), word));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Word w = pq.poll();
            sb.append(w.word).append("\n");
        }
        System.out.println(sb);
    }
}