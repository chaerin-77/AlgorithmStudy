import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> q = new ArrayDeque<>();
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cycle = 1;
			while(true) {
				int num = q.poll() - cycle;
				if (num <= 0) {
					q.offer(0);
					break;
				}
				q.offer(num);
				cycle = cycle % 5 + 1;
			}
			
			System.out.print("#" + t + " ");
			while (!q.isEmpty())
				System.out.print(q.poll() + " ");
			System.out.println();
		}
	}
}