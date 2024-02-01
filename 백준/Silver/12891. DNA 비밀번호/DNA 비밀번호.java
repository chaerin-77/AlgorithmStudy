import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int P, S, ans = 0;
	static int[] counter = new int[4];
	static int[] temp = new int[4];
    static boolean check_str() {
        return temp[0] >= counter[0] && temp[1] >= counter[1] && temp[2] >= counter[2] && temp[3] >= counter[3];
    }
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		String DNA = br.readLine();
		for (int i = 0; i < P; i++) {
			if(DNA.charAt(i) == 'A') temp[0]++;
			else if(DNA.charAt(i) == 'C') temp[1]++;
			else if(DNA.charAt(i) == 'G') temp[2]++;
			else if(DNA.charAt(i) == 'T') temp[3]++;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			counter[i] = Integer.parseInt(st.nextToken());
			if (temp[i] < counter[i]) {
				System.out.println(0);
				return;
			}
		}
		
		temp = new int[4];
		for (int j = 0; j < S; j++) {
			if(DNA.charAt(j) == 'A') temp[0]++;
			else if(DNA.charAt(j) == 'C') temp[1]++;
			else if(DNA.charAt(j) == 'G') temp[2]++;
			else if(DNA.charAt(j) == 'T') temp[3]++;
		}
		if (check_str()) ans ++;
		
		A: for (int i = S; i < P; i++) {
			if(DNA.charAt(i) == 'A') temp[0]++;
			else if(DNA.charAt(i) == 'C') temp[1]++;
			else if(DNA.charAt(i) == 'G') temp[2]++;
			else if(DNA.charAt(i) == 'T') temp[3]++;
			
			if(DNA.charAt(i - S) == 'A') temp[0]--;
			else if(DNA.charAt(i - S) == 'C') temp[1]--;
			else if(DNA.charAt(i - S) == 'G') temp[2]--;
			else if(DNA.charAt(i - S) == 'T') temp[3]--;
			
			if (check_str()) ans++;
		}
		System.out.println(ans);
	}
}