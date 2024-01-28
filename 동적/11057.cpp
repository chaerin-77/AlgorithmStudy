/*
오르막 수

문제
오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.

예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.

수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오.
수는 0으로 시작할 수 있다.

입력
첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.

출력
첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
*/
#include <iostream>
#define mod 10007
using namespace std;

int dp[1001][11] = {
    0,
};

int main()
{
    int N;
    cin >> N;

    for (int i = 0; i < 10; i++)
        dp[1][i] = 1;

    for (int i = 2; i <= N; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            if (j == 0)
            {
                dp[i][0] = 1;
                continue;
            }
            else
            {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]);
                dp[i][j] %= mod;
            }
        }
    }

    int result = 0;
    for (int i = 0; i < 10; i++)
        result = (result + dp[N][i]);

    cout << result % mod << endl;
    return 0;
}