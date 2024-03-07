import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        
        // 정렬이 되어있기 때문에 바로 다음의 문자만 확인하면 됨
        // 앞부분이 같은지 순서로 정렬이 되어있기 때문
        for (int i = 0; i < phone_book.length - 1; i++) {
        	String check = phone_book[i];
        	String next = phone_book[i+1];
        	if (next.startsWith(check)) return false;
        }
        
        return answer;
    }
}
