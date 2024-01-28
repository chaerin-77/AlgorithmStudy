/*
LCS 2

문제
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

입력
첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며,
최대 1000글자로 이루어져 있다.

출력
첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.

LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.
*/
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int dp[1001][1001] = {
    0,
};

int main()
{
    string s1, s2;
    cin >> s1 >> s2;

    for (int i = 1; i <= s1.size(); i++)
    {
        for (int j = 1; j <= s2.size(); j++)
        {
            if (s1[i - 1] == s2[j - 1])
                dp[i][j] = dp[i - 1][j - 1] + 1;
            else
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    cout << dp[s1.size()][s2.size()] << "\n";
    if (dp[s1.size()][s2.size()] == 0)
        return 0;

    string LCS = "";
    int f_idx = s2.size();
    for (int i = s1.size(); i >= 1; i--)
    {
        for (int j = f_idx; j >= 1; j--)
        {
            // 위랑 같고 왼쪽이랑 값이 달라야 함
            if (dp[i][j] == dp[i - 1][j]) // 위의 값이랑 같으면 열 고정
            {
                f_idx = j;
                break;
            }
            else if (dp[i][j] == dp[i][j - 1]) // 왼쪽 값이랑 같으면 계속해서 열 감소
                continue;
            else
                LCS = s1[i - 1] + LCS; // 값이 달라지면 LCS에 추가
        }
    }
    cout << LCS;

    return 0;
}