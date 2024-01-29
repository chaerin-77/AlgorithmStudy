/*
이항 계수 2

문제
자연수 N과 정수 K가 주어졌을 때 이항 계수 NCK를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ K ≤ N)

출력
\(\binom{N}{K}\)를 10,007로 나눈 나머지를 출력한다.
*/
#include <iostream>
#define mod 10007
using namespace std;

long long dp[1001][1001] = {
    0,
};

//이항계수: nCk = (n-1)Ck + (n-1)C(k-1)
int main()
{
    int N, K;
    cin >> N >> K;

    for (int i = 1; i <= N; i++)
    {
        for (int j = 0; j <= K; j++)
        {
            if (j == 0 || i == j) dp[i][j] = 1;
            else dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % mod;
        }
    }
    cout << dp[N][K] << endl;
    return 0;
}