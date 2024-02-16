class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0, Scnt = skill_trees.length;
        
        A: for (int i = 0; i < Scnt; i++) {
        	String sk = skill_trees[i];
        	int idx = 0;
        	B: for (int j = 0; j < sk.length(); j++) {
        		for (int k = idx; k < skill.length(); k++) {
        			// 선행 스킬과 같다면 다음 스킬로 인덱스 증가 후 다음 탐색
        			if (sk.charAt(j) == skill.charAt(idx)) {
        				idx++;
        				continue B;
        			}
        			// 선행 스킬이 아닌데 사용한 스킬이 있다면 다음 단어 탐색
        			else if (sk.charAt(j) == skill.charAt(k))
        				continue A;
        		}
        	}
        	answer++;
        }
        return answer;
    }
}
