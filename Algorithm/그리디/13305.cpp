#include<iomanip>
#include<iostream>
#include<vector>
using namespace std;

int main()
{
    int N;
    long min, result = 0;
    cin >> N;
    vector<int> city;
    vector<long> distance;

    for(int i = 0; i < N-1; i++) {
        int value;
        cin >> value;
        distance.push_back(value);
    }

    for(int i = 0; i < N; i++) {
        int value;
        cin >> value;
        city.push_back(value);
    }

    min = city[0];
    result = city[0] * distance[0];
    
    for(int i = 1; i < N-1; i++) {
        if(city[i] > min) {
            result += min * distance[i];
        }
        else {
            min = city[i];
            result += min * distance[i];
        }
    }
    cout << result;
}