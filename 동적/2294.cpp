/*
동전 2

문제
n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

입력
첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 
다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 
동전의 가치는 100,000보다 작거나 같은 자연수이다. 
가치가 같은 동전이 여러 번 주어질 수도 있다.

출력
첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
*/
#include <iostream>
#include <algorithm>
using namespace std;

int dp[10001] = {0, };
int input[101] = {0, };

int main ()
{
    int N, K;
    cin >> N >> K;

    for (int i = 1; i <= N; i++) cin >> input[i];

    for (int i = 1; i <= K; i++) dp[i] = 10001;

    for (int i = 1; i <= N; i++)
    {
        for (int j = input[i]; j <= K; j++)
        {
            // dp[0]이 0이기 때문에 앞에서 부터 시작할 경우 1이 저장됨
            dp[j] = min(dp[j], dp[j - input[i]] + 1);
        }
    }
    if (dp[K] == 10001) cout << -1 << endl;
    else cout << dp[K] << endl;
    return 0;
}