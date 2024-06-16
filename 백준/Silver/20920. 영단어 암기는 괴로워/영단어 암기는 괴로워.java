import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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