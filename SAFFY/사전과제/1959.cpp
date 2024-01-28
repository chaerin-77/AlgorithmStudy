/*
두 개의 숫자열
*/

#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int main() 
{
    int test_case;
    int T;
    cin >> T;
    
    for (test_case = 1; test_case <= T; ++test_case)
    {
        int N, M;
        cin >> N >> M;
        int Ai[21] = {0, };
        int Bj[21] = {0, };
        
        for (int i = 0; i < N; i++) cin >> Ai[i];
        for (int i = 0; i < M; i++) cin >> Bj[i];
        
        int cnt = abs(N - M) + 1;
        int sum = 0, ans = 0;
        if (N > M)
        {
            for (int i = 0; i < cnt; i++)
            {
                for (int j = 0; j < 20; j++)
                {
                    sum += Ai[j+i] * Bj[j];
                }
                ans = max(ans, sum);
                sum = 0;
            }
        }
        else
        {
            for (int i = 0; i < cnt; i++)
            {
                for (int j = 0; j < 20; j++)
                {
                    sum += Ai[j] * Bj[j + i];
                }
                ans = max(ans, sum);
                sum = 0;
            }
        }
        
        cout << "#" << test_case << " " << ans << endl;
    }
    return 0;
}