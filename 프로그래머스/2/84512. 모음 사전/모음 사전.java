import java.util.ArrayList;
import java.util.List;

class Solution {
   static String[] arr = new String[] {"A", "E", "I", "O", "U"};
	static List<String> list = new ArrayList<String>();
	public int solution(String word) {
		int answer = 0;
		
		recursion("", 0);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(word)) {
				answer = i;
				break;
			}
		}
		return answer;
	}
	static void recursion (String str, int depth) {
		list.add(str);
		
		if (depth == 5) return;
		
		for (int i = 0; i < arr.length; i++) {
			recursion(str + arr[i], depth + 1);
		}
	}
} 