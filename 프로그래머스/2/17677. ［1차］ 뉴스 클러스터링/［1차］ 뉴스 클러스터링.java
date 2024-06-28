import java.util.*;

class Solution {
        public int solution(String str1, String str2) {
        int answer = 0, inter = 0, uni = 0;
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            if (Character.isLetter(str1.charAt(i)) && Character.isLetter(str1.charAt(i + 1))) {
                String temp = str1.substring(i, i + 2).toUpperCase();
                map1.merge(temp, 1, Integer::sum);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            if (Character.isLetter(str2.charAt(i)) && Character.isLetter(str2.charAt(i + 1))) {
                String temp = str2.substring(i, i + 2).toUpperCase();
                map2.merge(temp, 1, Integer::sum);
            }
        }

        // 첫번째 문자열을 기준으로 비교
        for (String s : map1.keySet()) {
            // 포함되어 있으면 교집합, 합집합 연산 후 키 삭제
            if (map2.containsKey(s)) {
                inter += Math.min(map1.get(s), map2.get(s));
                uni += Math.max(map1.get(s), map2.get(s));
                map2.remove(s);
            } else uni += map1.get(s); // 그게 아니라면 합칩합에만 추가
        }

        // 삭제되지 않고 남아있는 키 합집합에 추가
        for (String s : map2.keySet()) {
            uni += map2.get(s);
        }

        if (uni == 0 && inter == 0) return 65536;
        answer = (int) (inter / (double) uni * 65536); // 실수 나눗셈을 하기 위함
        return answer;
    }
}