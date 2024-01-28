/*
가장 큰 증가하는 부분 수열

문제
수열 A가 주어졌을 때, 그 수열의 증가하는 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 합이 가장 큰 증가하는 부분 수열은
A = {1, 2, 50, 60} 이고, 합은 113이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 합이 가장 큰 증가하는 부분 수열의 합을 출력한다.
*/
#include <iostream>
#include <algorithm>
using namespace std;

int input[1001] = {
    0,
};
int dp[1001] = {
    0,
};

int main()
{
    int N;
    cin >> N;
    for (int i = 1; i <= N; i++)
        cin >> input[i];

    int ans = 0;
    for (int i = 1; i <= N; i++)
    {
        dp[i] = input[i];
        for (int j = 1; j < i; j++)
        {
            if (input[i] > input[j])
            {
                dp[i] = max(dp[i], dp[j] + input[i]);
            }
        }
        ans = max(ans, dp[i]);
    }

    cout << ans << endl;
    return 0;
}