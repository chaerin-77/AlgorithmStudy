/*
타일 채우기

문제
3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.

입력
첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.

출력
첫째 줄에 경우의 수를 출력한다.
*/
#include <iostream>
using namespace std;

int dp[31] = {0, };

int main ()
{
    int N; cin >> N;
    if (N % 2 == 1)
    {
        cout << 0 << endl;
        return 0;
    }

    dp[0] = 1;
    dp[2] = 3;

    for (int i = 4; i <= N; i = i + 2)
    {
        dp[i] = dp[i - 2] * dp[2];

        for (int j = i - 4; j >= 0; j = j - 2)
        {
            dp[i] += dp[j] * 2;
        }
    }

    cout << dp[N] << endl;
    return 0;
}