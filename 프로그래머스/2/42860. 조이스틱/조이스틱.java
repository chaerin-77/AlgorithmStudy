class Solution {
	public int solution(String name) {
        int answer = 0, cursor = 0;
        int length = name.length();
        int move = length - 1;
        
        for (int i = 0; i < length; i++) {
        	answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        	
        	cursor = i + 1;
        	while(cursor < length && name.charAt(cursor) == 'A')
        		cursor ++;
        	
        	move = Math.min(move, i * 2 + length - cursor);
        	move = Math.min(move, (length - cursor) * 2 + i);
        }
        
        return answer + move;
    }
}
