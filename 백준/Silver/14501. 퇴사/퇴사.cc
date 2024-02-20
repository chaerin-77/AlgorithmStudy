#include <iostream>
#include <algorithm>
using namespace std;

int Ti[16] = { 0, };
int Pi[16] = { 0, };
int dp[16] = { 0, };

int main()
{
	int N;
	cin >> N;

	for (int i = 1; i <= N; i++)
		cin >> Ti[i] >> Pi[i];

	for (int i = N; i > 0; i--)
	{
		if (i + Ti[i] > N + 1) dp[i] = dp[i + 1];
		else
		{
			dp[i] = max(dp[i + 1], dp[i + Ti[i]] + Pi[i]);
		}
	}
	cout << dp[1] << endl;

	return 0;
}