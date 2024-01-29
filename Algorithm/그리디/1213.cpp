/*
팰린드롬 만들기

문제
임한수와 임문빈은 서로 사랑하는 사이이다.

임한수는 세상에서 팰린드롬인 문자열을 너무 좋아하기 때문에,
둘의 백일을 기념해서 임문빈은 팰린드롬을 선물해주려고 한다.

임문빈은 임한수의 영어 이름으로 팰린드롬을 만들려고 하는데,
임한수의 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.

임문빈을 도와 임한수의 영어 이름을 팰린드롬으로 바꾸는 프로그램을 작성하시오.

입력
첫째 줄에 임한수의 영어 이름이 있다.
알파벳 대문자로만 된 최대 50글자이다.

출력
첫째 줄에 문제의 정답을 출력한다.
만약 불가능할 때는 "I'm Sorry Hansoo"를 출력한다.
정답이 여러 개일 경우에는 사전순으로 앞서는 것을 출력한다.
*/
#include <iostream>
#include <string>
using namespace std;

int main()
{
    string input;
    string output;
    int alphabet[26] = {
        0,
    };
    int cnt = 0;
    char last = 0;

    cin >> input;

    for (int i = 0; i < input.size(); i++)
    {
        alphabet[input[i] - 'A']++;
    }

    for (int i = 0; i < 26; i++)
    {
        if (alphabet[i] % 2 != 0)
        {
            cnt++;
            last = i + 'A';

            if (cnt > 1)
            {
                cout << "I'm Sorry Hansoo";
                return 0;
            }
        }

        for (int j = 0; j < alphabet[i] / 2; j++)
            output += i + 'A';
    }

    if(last != 0) output += last;

    for (int i = 25; i >= 0; i--)
    {
        for (int j = 0; j < alphabet[i] / 2; j++)
            output += i + 'A';
    }

    cout << output;
}