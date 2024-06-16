import java.util.HashSet;
import java.util.Set;

class Solution {
    String[][] relation;
    int maxDepth, row, answer;
    int[] picked;

    public int solution(String[][] input) {
        maxDepth = input[0].length;
        picked = new int[maxDepth];
        row = input.length;
        relation = input;
        answer = 0;

        comb(0, 0);
        return answer;
    }

    private void comb(int idx, int depth) {
        if (depth > 0) {
            boolean isUnique = checkUnique(depth, -1); // 유일성 확인
            boolean isMinimal = false;

            // 선택된 키가 두 개 이상인 경우 최소성 확인
            if (depth > 1) {
                for (int i = 0; i < depth; i++) {
                    // 하나씩 제거해보며 최소성 확인
                    isMinimal = checkUnique(depth, i);
                    if (isMinimal) break; // 최소가 아닐 경우 break;
                }
            }

            // 유일성 만족 && 최소성 만족 -> 답 증가
            if (isUnique && !isMinimal) answer++;
        }

        if (depth == maxDepth) return;

        for (int i = idx; i < maxDepth; i++) {
            picked[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    private boolean checkUnique(int depth, int idx) {
        Set<String> set = new HashSet<>(); // 중복 체크를 위해 set 사용

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < depth; j++) {
                if (j == idx) continue; // 제외할 키의 경우 건너뜀
                sb.append(relation[i][picked[j]]);
            }
            set.add(sb.toString());
        }

        // 중복이 없다면 set의 크기가 모든 행의 크기와 동일 -> true
        // 중복이 있다면 유일하지 않음 -> false
        return set.size() == row;
    }
}