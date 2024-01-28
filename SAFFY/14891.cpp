#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <deque>

using namespace std;

const int MN = 101;
string wheel[5]; // 1~4까지 톱니의 번호
int main()
{
    cin >> wheel[1] >> wheel[2] >> wheel[3] >> wheel[4]; // 4가지 톱니를 입력받음
    int K;
    cin >> K;
    int rotation[5]; // 회전을 표시하는 배열
    for (int i = 0; i < K; i++)
    {
        memset(rotation, 0, sizeof(rotation)); // 배열 초기화
        int num, direction;
        cin >> num >> direction;
        rotation[num] = direction; // 현재 톱니의 회전 방향 체크
        // 회전하는 톱니 왼쪽
        int now = num;        // 현재 톱니
        int nowd = direction; // 현재 방향
        for (int w = num - 1; w > 0; w--)
        {
            if (wheel[now][6] != wheel[w][2])
            {                        // 상극인 경우
                rotation[w] = -nowd; // 반대 방향으로 회전 표시
                nowd = -nowd;        // 현재 방향 수정
                now--;               // 다음 톱니로 이동
            }
            else
            {                    // 같은 극인 경우
                rotation[w] = 0; // 회전하지 않는다고 표시 후 종료
                break;
            }
        }
        // 회전하는 톱니 오른쪽
        now = num;        // 현재 톱니
        nowd = direction; // 현재 방향
        for (int w = num + 1; w <= 4; w++)
        {
            if (wheel[now][2] != wheel[w][6])
            {                        // 상극인 경우
                rotation[w] = -nowd; // 반대 방향으로 회전 표시
                nowd = -nowd;        // 현재 방향 수정
                now++;               // 다음 톱니로 이동
            }
            else
            {                    // 같은 극인 경우
                rotation[w] = 0; // 회전하지 않는다고 표시 후 종료
                break;
            }
        }

        // 톱니바퀴 회전시키기
        for (int j = 1; j <= 4; j++)
        {
            if (rotation[j] == 0)
                continue; // 회전하지 않는 경우 건너뛰기
            if (rotation[j] == 1)
            { // 시계방향으로 회전하는 경우
                string tmp = wheel[j].substr(0, 7);
                wheel[j] = wheel[j][7] + tmp;
            }
            else if (rotation[j] == -1)
            { // 반시계방향으로 회전하는 경우
                string tmp = wheel[j].substr(1);
                wheel[j] = tmp + wheel[j][0];
            }
        }
    }
    // 결과 계산
    int ans = 0;
    if (wheel[1][0] == '1')
        ans++;
    if (wheel[2][0] == '1')
        ans += 2;
    if (wheel[3][0] == '1')
        ans += 4;
    if (wheel[4][0] == '1')
        ans += 8;

    cout << ans;
}