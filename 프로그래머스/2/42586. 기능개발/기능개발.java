import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Integer> work = new Stack<>();
        int N = progresses.length;
        int[] answer = new int[N];
        
        for (int i = N - 1; i >= 0; i--) {
        	int temp = 1;
        	int w = 100 - progresses[i];
        	
        	while(temp * speeds[i] < w) temp++;
        	
        	work.push(temp);
        }
        
        int idx = 0;
        while(!work.isEmpty()) {
        	int temp = work.pop();
        	int cnt = 1;
        	while(!work.isEmpty() && temp >= work.peek()) {
        		cnt ++;
       			work.pop();
       		}        		
        	answer[idx++] = cnt;
        }
        
        return Arrays.copyOf(answer, idx);
    }
}