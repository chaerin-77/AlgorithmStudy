/*
파리퇴치3

N x N 배열 안의 숫자는 해당 영역에 존재하는 파리의 개체 수를 의미한다.

아래는 N=5 의 예이다.

파리 킬러 스프레이를 한 번만 뿌려 최대한 많은 파리를 잡으려고 한다.
스프레이의 노즐이 + 형태로 되어있어, 스프레이는 + 혹은 x 형태로 분사된다.
스프레이를 M의 세기로 분사하면 노즐의 중심이 향한 칸부터 각 방향으로 M칸의 파리를 잡을 수 있다.
다음은 M=3 세기로 스프레이르 분사한 경우 파리가 퇴치되는 칸의 예로, +또는 x 중 하나로 분사된다.
뿌려진 일부가 영역을 벗어나도 상관없다.

한 번에 잡을 수 있는 최대 파리수를 출력하라.

[제약 사항]

1. N 은 5 이상 15 이하이다.
2. M은 2 이상 N 이하이다.
3. 각 영역의 파리 갯수는 30 이하 이다.

[입력]

가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스의 첫 번째 줄에 N 과 M 이 주어지고,
다음 N 줄에 걸쳐 N x N 배열이 주어진다.
*/
#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        int N, M;
        cin >> N >> M;
        int sum = 0;
        int arr[N][N] = {
            0,
        };
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                cin >> arr[i][j];
            }
        }

        // + 형태
        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, 1, -1};
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                int tmp = arr[r][c];
                for (int m = 1; m < M; m++)
                {
                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d] * m;
                        int nc = c + dc[d] * m;
                        if (nr >= 0 && nr < N && nc >= 0 && nc < N)
                        {
                            tmp += arr[nr][nc];
                        }
                    }
                }
                sum = max(sum, tmp);
            }
        }

        // x 형태
        int drx[4] = {-1, 1, -1, 1};
        int dcx[4] = {-1, 1, 1, -1};
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                int tmp = arr[r][c];
                for (int m = 1; m < M; m++)
                {
                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + drx[d] * m;
                        int nc = c + dcx[d] * m;
                        if (nr >= 0 && nr < N && nc >= 0 && nc < N)
                        {
                            tmp += arr[nr][nc];
                        }
                    }
                }
                sum = max(sum, tmp);
            }
        }
        cout << "#" << t + 1 << " " << sum << "\n";
    }
    return 0;
}