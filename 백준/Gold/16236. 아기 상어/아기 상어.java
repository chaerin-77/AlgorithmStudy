import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Shark {
		int r, c, size, fishCnt = 0, move = 0;

		public Shark(int r, int c, int size, int move) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.move = move;
		}
		
		public void eat() {
			fishCnt++;
			if (fishCnt == this.size) {
				size++;
				fishCnt = 0;
			}
		}
	}
	static class Fish implements Comparable<Fish>{
		int r, c, size, dist;

		public Fish(int r, int c, int size, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			if (Integer.compare(this.dist, o.dist) == 0) {
				if (Integer.compare(this.r, o.r) == 0) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.dist, o.dist);

		}
	}
	
	static int N, answer = 0;
	static int[][] map;
	static PriorityQueue<Fish> fishes = new PriorityQueue<>();
	static Shark shark;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					map[i][j] = 0;
				}
			}
		}
		findFish();
		while (!fishes.isEmpty()) {
			Fish fish = fishes.poll(); // 가장 거리가 가까운 물고기만 섭취
			answer += fish.dist;
			map[fish.r][fish.c] = 0;
			shark.eat();
			shark.r = fish.r;
			shark.c = fish.c;
			fishes.clear(); // 물고기 하나를 먹은 이후 거리를 다시 계산해야 하기 때문에 모두 삭제
			findFish();
		}
		
		System.out.println(answer);
	}

	private static void findFish() {
		Deque<Shark> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Shark(shark.r, shark.c, shark.size, 0));
		visited[shark.r][shark.c] = true;
		
		while(!q.isEmpty()) {
			Shark cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int move = cur.move;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위, 방문 체크
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
				
				// 나보다 큰 물고기라면 이동 불가
				if (map[nr][nc] > shark.size) continue;
				
				q.offer(new Shark(nr, nc, shark.size, move + 1));
				visited[nr][nc] = true;
				
				// 먹을 수 있는 물고기가 있다면 큐에 추가
				if (map[nr][nc] != 0 && map[nr][nc] < cur.size) {
					fishes.offer(new Fish(nr, nc, map[nr][nc], move + 1));
				}
			}
		}
	}
}