/*
주사위

문제
    +---+
    | D |
+---+---+---+---+
| E | A | B | F |
+---+---+---+---+
    | C |
    +---+
주사위는 위와 같이 생겼다.
주사위의 여섯 면에는 수가 쓰여 있다. 위의 전개도를 수가 밖으로 나오게 접는다.

A, B, C, D, E, F에 쓰여 있는 수가 주어진다.

지민이는 현재 동일한 주사위를 N^3개 가지고 있다.
이 주사위를 적절히 회전시키고 쌓아서, N×N×N크기의 정육면체를 만들려고 한다.
이 정육면체는 탁자위에 있으므로, 5개의 면만 보인다.

N과 주사위에 쓰여 있는 수가 주어질 때,
보이는 5개의 면에 쓰여 있는 수의 합의 최솟값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. 둘째 줄에 주사위에 쓰여 있는 수가 주어진다.
위의 그림에서 A, B, C, D, E, F에 쓰여 있는 수가 차례대로 주어진다.
N은 1,000,000보다 작거나 같은 자연수이고, 쓰여 있는 수는 50보다 작거나 같은 자연수이다.

출력
첫째 줄에 문제의 정답을 출력한다.
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    long long N;
    long long result = 0;
    vector<int> dice;

    cin >> N;
    for (int i = 0; i < 6; i++)
    {
        int value;
        cin >> value;
        dice.push_back(value);
        result += value;
    }

    if (N == 1)
    {
        cout << result - *max_element(dice.begin(), dice.end());
        return 0;
    }

    long long v[3];
    v[0] = min(dice[0], dice[5]);
    v[1] = min(dice[1], dice[4]);
    v[2] = min(dice[2], dice[3]);
    sort(v, v + 3);

    long long one = v[0];
    long long two = one + v[1];
    long long three = two + v[2];

    result = 0;
    result += three * 4;
    result += (two * 4 * (N - 1)) + (two * 4 * (N - 2));
    result += (one * 4 * (N - 2) * (N - 1)) + (one * (N - 2) * (N - 2));

    cout << result << endl;
    return 0;
}