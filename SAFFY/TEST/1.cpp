#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{
	int T;
	cin >> T;

	for (int i = 0; i < T; i++)
	{
		int N, result = 0;
		cin >> N;
		vector<int> obj(N, 0);
		int check[1001] = { 0, };
		check[0] = 1001;

		for (int j = 0; j < N; j++)
		{
			cin >> obj[j];
			check[obj[j]]++;
		}
		sort(obj.begin(), obj.end(), greater<int>());
		
		int buy_idx = 0, service_idx = 0;
		for (int j = 0; j < N; j++)
		{
			buy_idx = obj[j];
			service_idx = obj[j] - 1;

			while (check[buy_idx] > 0)
			{
				check[buy_idx]--;
				result += buy_idx;

				while (!check[service_idx]) service_idx--;
				check[service_idx]--;

				if (service_idx == 0 && check[buy_idx] > 0)
				{
					result += buy_idx * check[buy_idx];
					check[buy_idx] = 0;
					break;
				}
			}
		}

		cout << "#" << i + 1 << " " << result << endl;
	}
	return 0;
}