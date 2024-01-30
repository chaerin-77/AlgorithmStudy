/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int[][] ladder;
    static int start, find;

    static int[] dy = {-1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            find = -1;
            br.readLine();
            ladder = new int[100][100];
            for (int i = 0; i < ladder.length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < ladder[0].length; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    if (ladder[i][j] == 2) {
                        start = j;
                    }
                }
            }
            rec(99, start, 0);

            System.out.println(String.format("#%d %d", t, find));
        }
        br.close();
    }

    private static void rec(int x, int y, int dir) {
        if (x == 0) {
            find = y;
            return;
        }

        if (dir != 0) {
            int ny = y + dir;
            if (ny >= 0 && ny < 100 && ladder[x][ny] == 1) {
                rec(x, ny, dir);
            } else {
                rec(x - 1, y, 0);
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            int ny = y + dy[i];
            if (ny >= 0 && ny < 100 && ladder[x][ny] == 1) {
                rec(x, ny, dy[i]);
                return;
            }
        }
        rec(x - 1, y, 0);
    }
}