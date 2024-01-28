/*
ACM Craft

문제
ACM크래프트는 다이나믹한 게임 진행을 위해 건물을 짓는 순서가 정해져 있지 않다.
즉, 첫 번째 게임과 두 번째 게임이 건물을 짓는 순서가 다를 수도 있다.
매 게임시작 시 건물을 짓는 순서가 주어진다.
또한 모든 건물은 각각 건설을 시작하여 완성이 될 때까지 Delay가 존재한다.

위의 예시를 보자.

이번 게임에서는 다음과 같이 건설 순서 규칙이 주어졌다.
1번 건물의 건설이 완료된다면 2번과 3번의 건설을 시작할수 있다. (동시에 진행이 가능하다)
그리고 4번 건물을 짓기 위해서는 2번과 3번 건물이 모두 건설 완료되어야지만 4번건물의 건설을 시작할수 있다.

따라서 4번건물의 건설을 완료하기 위해서는 우선 처음 1번 건물을 건설하는데 10초가 소요된다.
그리고 2번 건물과 3번 건물을 동시에 건설하기 시작하면 2번은 1초뒤에 건설이 완료되지만
아직 3번 건물이 완료되지 않았으므로 4번 건물을 건설할 수 없다.
3번 건물이 완성되고 나면 그때 4번 건물을 지을수 있으므로 4번 건물이 완성되기까지는 총 120초가 소요된다.

특정건물을 가장 빨리 지을 때까지 걸리는 최소시간을 알아내는 프로그램을 작성해주자.

입력
첫째 줄에는 테스트케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 주어진다.
첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K이 주어진다. (건물의 번호는 1번부터 N번까지 존재한다)
둘째 줄에는 각 건물당 건설에 걸리는 시간 D1, D2, ..., DN이 공백을 사이로 주어진다.
셋째 줄부터 K+2줄까지 건설순서 X Y가 주어진다. (이는 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능하다는 의미이다)

마지막 줄에는 백준이가 승리하기 위해 건설해야 할 건물의 번호 W가 주어진다.

출력
건물 W를 건설완료 하는데 드는 최소 시간을 출력한다.
편의상 건물을 짓는 명령을 내리는 데는 시간이 소요되지 않는다고 가정한다.

건설순서는 모든 건물이 건설 가능하도록 주어진다.

제한
2 ≤ N ≤ 1000
1 ≤ K ≤ 100,000
1 ≤ X, Y, W ≤ N
0 ≤ Di ≤ 100,000, Di는 정수
*/
#include <iostream>
#include <vector>
#include <queue>

#define MAX 1001
using namespace std;

// int Time[MAX] = {0, };
// int Result_Time[MAX] = {0, };
// int Entry[MAX] = {0, };
// vector<int> Build(MAX, 0);

int main()
{
    int T;
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        // 초기화
        int Time[MAX] = {0, };
        int Result_Time[MAX] = {0, };
        int Entry[MAX] = {0, };
        vector<vector<int>> Build(MAX, vector<int>(MAX, 0));

        // 입력
        int N, K, D, W;
        cin >> N >> K;
        for (int i = 1; i <= N; i++) cin >> Time[i];
        for (int i = 0; i < K; i++)
        {
            int a, b;
            cin >> a >> b;
            Build[a].push_back(b); // a 건물 다음에 지어져야 하는 b건물 a인덱스 맨 뒤에 추가
            Entry[b]++; // b 건물 짓기 전 선행 작업 개수 저장
        }
        cin >> W;
        
        // 계산 시작
        queue<int> Q;
        for (int i = 1; i <= N; i++)
        {
            if (Entry[i] == 0) 
            {
                Q.push(i); // 선행 작업이 없는 건물들은 큐에 넣음
                Result_Time[i] = Time[i]; // i번째 건물을 지을 때 걸리는 총 시간 저장
            }
        }
        
        while (!Q.empty()) // 모든 건물 지을 때까지 
        {
            int cur = Q.front(); // 가장 앞에 있는 건물 pop
            Q.pop();

            for (int i = 0; i < Build[cur].size(); i++) // 해당 건물 다음에 지어져야할 건물들
            {
                int next = Build[cur][i]; // 다음에 지어질 건물 번호
                Result_Time[next] = max(Result_Time[next], Result_Time[cur] + Time[next]);
                //다음 건물 번호가 지어지기 최소 시간은 (저장된 다음 건물의 최소 시간, 현재 건물의 최소 시간 + 다음 건물 건축 시간)의 최댓 값이다
                Entry[next]--; // 한 차수를 해결했기 때문에 감소 시킴

                if (Entry[next] == 0) Q.push(next); // 선행 건물이 없어지면 큐에 push
            }
        }
        cout << Result_Time[W] << endl;
    }
    return 0;
}