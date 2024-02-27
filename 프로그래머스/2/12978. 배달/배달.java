import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
	static class City {
		int to, weight;

		public City(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		List<City>[] map = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < road.length; i++) {
			int from = road[i][0];
			int to = road[i][1];
			int weight = road[i][2];
			map[from].add(new City(to, weight));
			map[to].add(new City(from, weight));
		}
		int[] distance = new int[N + 1];
		Arrays.fill(distance, 1_000_000_000);
		distance[1] = 0;
		
		PriorityQueue<City> pq = new PriorityQueue<>((w1, w2) -> w1.weight - w2.weight);
		pq.offer(new City(1, 0));
		
		while (!pq.isEmpty()) {
			City temp = pq.poll();
			if (temp.weight > distance[temp.to]) continue;
			
			if (distance[temp.to] <= K) answer++;
			else break;
			
			for (City c : map[temp.to]) {
				int dist = distance[temp.to] + c.weight;
				if (dist < distance[c.to]) {
					distance[c.to] = dist;
					pq.offer(new City(c.to, dist));
				}
			}
		}
        
        return answer;
    }
}
