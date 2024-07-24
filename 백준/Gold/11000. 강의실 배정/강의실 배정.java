import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class lecture implements Comparable<lecture> {
        int start, end;

        public lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(lecture o) {
            if (this.start == o.start) return Integer.compare(this.end, o.end);
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        lecture[] lectures = new lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new lecture(start, end);
        }
        Arrays.sort(lectures);

        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for (int i = 1; i < N; i++) {
            int temp = pq.peek();
            if (temp <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        System.out.println(pq.size());
    }
}