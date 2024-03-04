import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class BD implements Comparable<BD>{
		String name;
		int d, m, y;
		
		public BD(String name, int d, int m, int y) {
			super();
			this.name = name;
			this.d = d;
			this.m = m;
			this.y = y;
		}

		@Override
		public int compareTo(BD o) {
			if (this.y == o.y) {
				if (this.m == o.m) {
					return o.d - this.d;
				}
				return o.m - this.m;
			}
			return o.y - this.y;
		}

		@Override
		public String toString() {
			return "BD [name=" + name + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<BD> birthday = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			birthday.add(new BD(name, day, month, year));
		}
		if (N == 1) System.out.println(birthday.get(0).name);
		else {
			Collections.sort(birthday);
			System.out.println(birthday.get(0).name);
			System.out.println(birthday.get(birthday.size() - 1).name);
		}
	}
}