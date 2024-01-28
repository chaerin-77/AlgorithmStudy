/*
쉬운 계단 수

문제
45656이란 수를 보자.

이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.

N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.

입력
첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.

출력
첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
*/
#include <iostream>

#define mod 1000000000
using namespace std;

long long dp[101][10];

int main()
{
    int N;
    long long result = 0;
    cin >> N;

    for (int i = 1; i < 10; i++)
    {
        dp[1][i] = 1;
    }
    dp[1][0] = 0;

    for (int i = 2; i <= N; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            if (j == 0)
            {
                dp[i][j] = dp[i - 1][j + 1] % mod;
            }
            else if (j == 9)
            {
                dp[i][j] = dp[i - 1][j - 1] % mod;
            }
            else
            {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
            }
        }
    }

    for (int i = 0; i < 10; i++)
    {
        result += dp[N][i];
    }

    cout << result % mod << endl;
    return 0;
}