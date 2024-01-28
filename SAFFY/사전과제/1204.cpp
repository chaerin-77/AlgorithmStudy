/*
최빈수 구하기
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
        int x;
        cin >> x; 
        int num[101] = {0, };
        
        for (int i = 0; i < 1000; i++) 
        {
            int score;
            cin >> score;
            num[score]++;
        }
        
        int answer = 0;
        for (int i = 0; i <= 100; i++)
        {
            if (num[i] >= num[answer]) answer = i;
        }
        cout << "#" << test_case << " " << answer << endl;
    }
    return 0;
}