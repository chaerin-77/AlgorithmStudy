/*
행렬

문제
0과 1로만 이루어진 행렬 A와 행렬 B가 있다.
이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.

행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)

입력
첫째 줄에 행렬의 크기 N M이 주어진다.
N과 M은 50보다 작거나 같은 자연수이다.
둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.

출력
첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
*/
#include <iostream>
#define MAX 51
using namespace std;

char arr1[MAX][MAX];
char arr2[MAX][MAX];

void reverse(int x, int y)
{
    for (int i = x; i < x + 3; i++)
    {
        for (int j = y; j < y + 3; j++)
        {
            if (arr1[i][j] == '0')
                arr1[i][j] = '1';
            else
                arr1[i][j] = '0';
        }
    }
}

int main()
{
    int N, M, count = 0;
    cin >> N >> M;

    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            cin >> arr1[i][j];

    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            cin >> arr2[i][j];

    if (N < 3 || M < 3)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (arr1[i][j] != arr2[i][j])
                {
                    cout << -1;
                    return 0;
                }
            }
        }
        cout << 0;
        return 0;
    }

    for (int i = 0; i < N - 2; i++)
    {
        for (int j = 0; j < M - 2; j++)
        {
            if (arr1[i][j] != arr2[i][j])
            {
                reverse(i, j);
                count++;
            }
        }
    }

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (arr1[i][j] != arr2[i][j])
            {
                count = -1;
            }
        }
    }

    cout << count;
    return 0;
}