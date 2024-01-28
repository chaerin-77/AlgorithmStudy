/*
숫자 배열 회전

N x N 행렬이 주어질 때,

시계 방향으로 90도, 180도, 270도 회전한 모양을 출력하라.


[제약 사항]

N은 3 이상 7 이하이다.

[입력]

가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.

각 테스트 케이스의 첫 번째 줄에 N이 주어지고,

다음 N 줄에는 N x N 행렬이 주어진다.

[출력]

출력의 첫 줄은 '#t'로 시작하고,

다음 N줄에 걸쳐서 90도, 180도, 270도 회전한 모양을 출력한다.

입력과는 달리 출력에서는 회전한 모양 사이에만 공백이 존재함에 유의하라.

(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)
*/

#include <iostream>
using namespace std;

int main()
{
    int test_case;
    int T;
    cin >> T;

    for (test_case = 1; test_case <= T; ++test_case)
    {
        int N;
        cin >> N;
        int arr[8][8] = {
            0,
        };
        int arr1[8][8] = {
            0,
        };
        int arr2[8][8] = {
            0,
        };
        int arr3[8][8] = {
            0,
        };

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                cin >> arr[i][j];

        for (int i = 0; i < N; i++)
        {
            for (int j = N - 1; j >= 0; j--)
            {
                arr1[i][j] = arr[N - j - 1][i];
            }
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = N - 1; j >= 0; j--)
            {
                arr2[i][j] = arr1[N - j - 1][i];
            }
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = N - 1; j >= 0; j--)
            {
                arr3[i][j] = arr2[N - j - 1][i];
            }
        }

        cout << "#" << test_case << "\n";
        for (int i = 0; i < N; i++)
        {
            for (int k = 0; k < N; k++)
            {
                cout << arr1[i][k];
            }
            cout << " ";
            for (int k = 0; k < N; k++)
            {
                cout << arr2[i][k];
            }
            cout << " ";
            for (int k = 0; k < N; k++)
            {
                cout << arr3[i][k];
            }
            cout << "\n";
        }
    }
    return 0;
}