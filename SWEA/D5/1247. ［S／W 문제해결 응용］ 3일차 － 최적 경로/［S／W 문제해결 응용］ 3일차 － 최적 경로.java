import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static class Location {
		int X, Y;
		public Location(int x, int y) {
			this.X = x;
			this.Y = y;
		}
	}
	static int T, N, answer;
	static Location[] map;
	static Location office;
	static Location home;
	static boolean[] isSelected;
	static int[] picked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			office = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			map = new Location[N];
			for (int i = 0; i < N; i++) {
				map[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			isSelected = new boolean[N];
			picked = new int[N];
			answer = Integer.MAX_VALUE;
			route(0);
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	private static void route (int cnt) {
		if (cnt == N) {
			int temp = Math.abs(home.X - map[picked[0]].X) + Math.abs(home.Y - map[picked[0]].Y);
			for (int i = 0; i < N - 1; i++) {
				temp += Math.abs(map[picked[i]].X - map[picked[i+1]].X) + Math.abs(map[picked[i]].Y - map[picked[i+1]].Y);
			}
			temp += Math.abs(office.X - map[picked[N-1]].X) + Math.abs(office.Y - map[picked[N-1]].Y);
			answer = Math.min(answer, temp);
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			picked[cnt] = i;
			route(cnt + 1);
			isSelected[i] = false;
		}
	}
}