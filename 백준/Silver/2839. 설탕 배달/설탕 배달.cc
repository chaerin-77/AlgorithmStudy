#include<iomanip>
#include<iostream>
#include<string>
using namespace std;

int main()
{
    int N = 0;
    int pb = 0;

    cin >> N;

    while(N >= 0) {
        if(N % 5 == 0) {
            pb += N/5;
            cout << pb;
            return 0;
        }
        N -= 3;
        pb++;
    }
    cout << -1;
    return 0;
}