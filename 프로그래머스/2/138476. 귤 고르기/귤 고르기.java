import java.util.Arrays;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] count = new int[10000001];
        for (int i = 0; i < tangerine.length; i++) {
        	count[tangerine[i]]++;
        }
        
        Arrays.sort(count);
        
        for (int i = count.length - 1; i >= 0; i--) {
        	k -= count[i];
			answer++;
			
			if(k <= 0) break;
        }
        
        return answer;
    }
}