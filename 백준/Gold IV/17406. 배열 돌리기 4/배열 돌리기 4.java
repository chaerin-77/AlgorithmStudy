import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, answer = Integer.MAX_VALUE;
	static int[][] arr, arrT, rotate;
	static int[] picked;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        arrT = new int[N][M];
        rotate = new int[K][3];
        picked = new int[K];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arrT[i][j] = arr[i][j];
            }
        }
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotate[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotate[i][2] = Integer.parseInt(st.nextToken());
        }
        permu(0, 0);

        System.out.println(answer);
    }
    
    public static void permu (int cnt, int flag) {
    	if (cnt == K) {
    		for (int i = 0; i < K; i++) {
    			rotation(rotate[picked[i]]);
    		}
    		for (int i = 0; i < N; i++) {
                int temp = 0;
                for (int j = 0; j < M; j++) {
                    temp += arr[i][j];
                }
                answer = Math.min(answer, temp);
                System.arraycopy(arrT[i], 0, arr[i], 0, M);
            }
    		return;
    	}
    	
    	for (int i = 0; i < K; i++) {
    		if ((flag & 1 << i) != 0) continue;
    		picked[cnt] = i;
    		permu(cnt + 1, flag | 1 << i);
    	}
    }
    
    public static void rotation(int[] rotate) {
    	 int r = rotate[0];
         int c = rotate[1];
         int s = rotate[2];
         
         for (int range = 0; range < s; range++) {
         	int sr = r - s + range;
         	int sc = c - s + range;
             int temp = arr[sr][sc];
             int dir = 0, cr = sr, cc = sc;
             while(true) {
                 int nr = cr + dr[dir];
                 int nc = cc + dc[dir];

                 if (nr == sr && nc == sc) break;
                 if (nr < sr || nc < sc || nr > r + s - range || nc > c + s - range) {
                     dir = (dir + 1) % 4;
                     continue;
                 }
                 arr[cr][cc] = arr[nr][nc];
                 cr = nr;
                 cc = nc;
             }
             arr[cr][cc] = temp;
         }
//         Print();
    }
    
//    public static void Print() {
//    	for (int i = 0; i < N; i++) {
//    		for (int j = 0; j < M; j++) {
//    			System.out.printf("%d ", arr[i][j]);
//    		}
//    		System.out.println();
//    	}
//    	System.out.println();
//    }
}