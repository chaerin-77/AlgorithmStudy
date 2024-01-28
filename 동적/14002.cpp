/*
가장 긴 증가하는 부분 수열 4

문제
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
가장 긴 증가하는 부분 수열은 A = {10, 20, 30, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

둘째 줄에는 가장 긴 증가하는 부분 수열을 출력한다. 그러한 수열이 여러가지인 경우 아무거나 출력한다.
*/
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int dp[1001] = {
    0,
};
int input[1001]{
    0,
};

int main()
{
    int N, result = 0, index = 0;
    cin >> N;
    vector<int> arr[1001];
    for (int i = 0; i < N; i++)
    {
        cin >> input[i];
        dp[i] = 1;
    }

    for (int i = 0; i < N; i++)
    {
        arr[i].push_back(input[i]);
        for (int j = 0; j < i; j++)
        {
            if (input[i] > input[j])
            {
                if (dp[i] < dp[j] + 1)
                {
                    arr[i].clear(); // 가장 긴 수열의 길이가 늘어나면 이전 기록 삭제
                    arr[i] = arr[j]; // 해당 수열 복사
                    arr[i].push_back(input[i]); // 마지막에 현재 값 push
                    dp[i] = dp[j] + 1;
                }
            }
        }
        if (result < dp[i])
        {
            result = dp[i];
            index = i;
        }
    }
    cout << result << endl;
    for (int i = 0; i < result; i++)
        cout << arr[index][i] << " ";
    return 0;
}