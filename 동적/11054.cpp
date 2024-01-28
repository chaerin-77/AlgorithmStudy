/*
가장 긴 바이토닉 부분 수열

문제
수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 
그 수열을 바이토닉 수열이라고 한다.

예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,  
{1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.

수열 A가 주어졌을 때, 
그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수열 A의 크기 N이 주어지고, 
둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
*/
#include <iostream>
#include <algorithm>
using namespace std;

int arr[1001] = {0, };
int dp[1001] = {0, };
int r_dp[1001] = {0, };

int main ()
{
    int N; cin >> N;
    for (int i = 1; i <= N; i++) cin >> arr[i];

    for (int i = 1; i <= N; i++)
    {
        dp[i] = 1;
        for (int j = 0; j <= i; j++)
        {
            if (arr[j] < arr[i])
            {
                dp[i] = max(dp[i] ,dp[j] + 1);
            }
        }
    }
    for (int i = N; i >= 1; i--)
    {
        r_dp[i] = 1;
        for (int j = N; j >= i; j--)
        {
            if (arr[i] > arr[j])
            {
                r_dp[i] = max(r_dp[i], r_dp[j] + 1);
            }
        }
    }

    int ans = 0;
    for (int i = 1; i <= N; i++)
    {
        if (ans < dp[i] + r_dp[i] - 1) ans = dp[i] + r_dp[i] -1;
    }

    cout << ans << endl;
    return 0;
}