/*
 */
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    int n;
    cin >> n; // 강의실 개수
    vector<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];
    int b, c; // 총감독관, 부감독관 감시 가능 명수
    cin >> b >> c;
    long long result = n; // 총감독관 강의실마다 투입
    for (int i = 0; i < n; i++)
    {
        v[i] -= b; // 총감독관이 감시할 수 있는 인원 빼기
        if (v[i] <= 0)
            continue;
        result += v[i] / c;
        if (v[i] % c)
            result += 1;
    }
    cout << result;
}