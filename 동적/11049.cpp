/*
행렬 곱셈 순서

문제
크기가 N×M인 행렬 A와 M×K인 B를 곱할 때 필요한 곱셈 연산의 수는 총 N×M×K번이다.
행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 달라지게 된다.

예를 들어, A의 크기가 5×3이고, B의 크기가 3×2, C의 크기가 2×6인 경우에 행렬의 곱 ABC를 구하는 경우를 생각해보자.

AB를 먼저 곱하고 C를 곱하는 경우 (AB)C에 필요한 곱셈 연산의 수는 5×3×2 + 5×2×6 = 30 + 60 = 90번이다.
BC를 먼저 곱하고 A를 곱하는 경우 A(BC)에 필요한 곱셈 연산의 수는 3×2×6 + 5×3×6 = 36 + 90 = 126번이다.
같은 곱셈이지만, 곱셈을 하는 순서에 따라서 곱셈 연산의 수가 달라진다.

행렬 N개의 크기가 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값을 구하는 프로그램을 작성하시오. 입력으로 주어진 행렬의 순서를 바꾸면 안 된다.

입력
첫째 줄에 행렬의 개수 N(1 ≤ N ≤ 500)이 주어진다.

둘째 줄부터 N개 줄에는 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)

항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어진다.

출력
첫째 줄에 입력으로 주어진 행렬을 곱하는데 필요한 곱셈 연산의 최솟값을 출력한다.
정답은 2^31-1 보다 작거나 같은 자연수이다.
 또한, 최악의 순서로 연산해도 연산 횟수가 2^31-1보다 작거나 같다.
*/
#define _CRT_SECURE_NO_WARNINGS
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <cmath>
#include <cstdio>
#include <string>
#include <deque>

using namespace std;

int N;
pair<int, int> matrix[501];
int dp[501][501]; // dp[1][3]이면 1번부터 3번 행렬 까지의 최소 연산
pair<int, int> dp_matrix[501][501]; // dp를 계산하기 위한 행과 열 값 저장

int Sum(int dx, int mid, int dy)
{
	return dp_matrix[dx][mid].first * dp_matrix[dx][mid].second * dp_matrix[mid + 1][dy].second;
	// dp ~ mid, mid+1 ~ dy 행렬의 곱셈 값을 반환
}

int main()
{
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for (int i = 1; i <= N; i++)
	{
		cin >> matrix[i].first >> matrix[i].second;
		dp_matrix[i][i] = matrix[i];
	}
	for (int i = 1; i < N; i++)
	{
		for (int dx = 1; dx + i <= N; dx++)
		{
			int dy = dx + i; // 계산하는 행렬의 간격을 늘림
			dp[dx][dy] = 2147483647;
			for (int mid = dx; mid < dy; mid++) // mid를 기준으로 dx ~ mid, mid+1 ~ dy 나눔
			{
				dp[dx][dy] = min(dp[dx][dy], dp[dx][mid] + dp[mid + 1][dy] + Sum(dx, mid, dy));
				// 원래 가지고 있던 값, 혹은 dx ~ mid, mid+1 ~ dy 의 최소 연산 횟수에 두 행렬의 연산 값 중 최소값 비교
			}
			// (2, 3), (3, 2) 행렬 계산 시 (2, 2) 행렬이 나오기 때문에
			dp_matrix[dx][dy].first = matrix[dx].first;
			dp_matrix[dx][dy].second = matrix[dy].second;
		}
	}
	cout << dp[1][N];
	return 0;
}