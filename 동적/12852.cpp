/*
1로 만들기 2

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
연산을 사용하는 횟수의 최솟값을 출력하시오.

입력
첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 자연수 N이 주어진다.

출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다.
정답이 여러 가지인 경우에는 아무거나 출력한다.
*/
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int dp[1000001] = {
    0,
};

int main()
{
    int N;
    cin >> N;

    for (int i = 1; i <= N; i++)
    {
        dp[i] = i;
    }

    for (int i = 2; i <= N; i++)
    {
        if (i % 2 == 0)
        {
            dp[i] = min(dp[i], dp[i / 2]);
        }
        if (i % 3 == 0)
        {
            dp[i] = min(dp[i], dp[i / 3]);
        }
        dp[i] = min(dp[i], dp[i - 1]) + 1;
    }

    cout << dp[N] - 1 << "\n";

    while (N != 0)
    {
        cout << N << " ";
        if (dp[N] == dp[N - 1] + 1)
        {
            N = N - 1;
        }
        else if (N % 2 == 0 && dp[N] == dp[N / 2] + 1)
        {
            N = N / 2;
        }
        else if (N % 3 == 0 && dp[N] == dp[N / 3] + 1)
        {
            N = N / 3;
        }
    }

    return 0;
}