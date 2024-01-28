/*
미로 탐색

문제
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 
이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 
칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 
다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
*/
#include <iostream>
#include <algorithm>
#include <string>
#include <queue>
using namespace std;

int map[101][101] = {0, };
int ans[101][101] = {0, };
int visit[101][101] = {0, };
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};
int N, M;

void bfs (int x, int y) // 최단 경로는 bfs!! -> queue 사용
{
    queue<pair<int, int>> q;
    q.push(make_pair(x, y));
    visit[x][y] = 1;
    ans[x][y] = 1;

    while (!q.empty())
    {
        int a = q.front().first;
        int b = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int next_x = a + dx[i];
            int next_y = b + dy[i];

            if (next_x < 0 || next_y < 0 || next_x >= N || next_y >= M)
                continue; // 다음 반복
            
            if (map[next_x][next_y] == 0)
                continue;
            
            if (map[next_x][next_y] && !visit[next_x][next_y])
            {
                ans[next_x][next_y] = ans[a][b] + 1;
                q.push(make_pair(next_x, next_y));
                visit[next_x][next_y] = 1;
            }
        }
    }
    
}

int main ()
{
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
            map[i][j] = str[j] - '0';
    }
    
    bfs(0, 0);

    cout << ans[N - 1][M - 1] << endl;
    return 0;
}