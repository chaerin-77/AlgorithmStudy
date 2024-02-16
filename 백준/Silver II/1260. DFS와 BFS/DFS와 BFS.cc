/*
DFS와 BFS

문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 
탐색을 시작할 정점의 번호 V가 주어진다. 
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. 
V부터 방문된 점을 순서대로 출력하면 된다.
*/
#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
#include <string.h>
using namespace std;

vector <int> vertex[1001];
vector <int> bfs_result;
vector <int> dfs_result;
bool visit[1002];

void dfs (int V)
{
    visit[V] = true;
    dfs_result.push_back(V);

    for (int i = 0; i < vertex[V].size(); i++)
        if(!visit[vertex[V][i]]) 
            dfs(vertex[V][i]);
}

void bfs (int V)
{
    queue<int> q;
    q.push(V);
    visit[V] = true;

    while (!q.empty())
    {
        int x = q.front();
        q.pop();
        bfs_result.push_back(x);

        for (int i = 0; i < vertex[x].size(); i++)
        {
            if (!visit[vertex[x][i]])
            {
                q.push(vertex[x][i]);
                visit[vertex[x][i]] = true;
            }
        }
    }
    
}

int main ()
{
    int N, M, V;
    cin >> N >> M >> V;

    for (int i = 1; i <= M; i++)
    {
        int a, b;
        cin >> a >> b;
        vertex[a].push_back(b);
        vertex[b].push_back(a); // 양방향 간선 처리
    }

    for (int i = 1; i <= N; i++)
        sort(vertex[i].begin(), vertex[i].end());

    dfs(V);
    memset(visit, false, sizeof(visit));
    bfs(V);

    for (int i = 0; i < dfs_result.size(); i++)
        cout << dfs_result[i] << " ";

    cout << "\n";
    
    for (int i = 0; i < bfs_result.size(); i++)
        cout << bfs_result[i] << " ";

    return 0;
}