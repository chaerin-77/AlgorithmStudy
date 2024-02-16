class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0, Scnt = skill_trees.length;
        
        A: for (int i = 0; i < Scnt; i++) {
        	String sk = skill_trees[i];
        	int idx = 0;
        	for (int j = 0; j < sk.length(); j++) {
        		for (int k = idx; k < skill.length(); k++) {
        			if (sk.charAt(j) == skill.charAt(idx))idx++;
        			else if (k > idx && sk.charAt(j) == skill.charAt(k))
        				continue A;
        		}
        	}
        	answer++;
        }
        
        return answer;
    }
}