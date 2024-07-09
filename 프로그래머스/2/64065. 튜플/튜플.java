import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] parts = s.split("\\},\\{");
        Set<Integer> set = new HashSet<>();
        // 결과를 저장할 리스트
        List<int[]> resultList = new ArrayList<>();

        // 각 부분 문자열 처리
        for (String part : parts) {
            // 콤마를 기준으로 숫자 분할
            String[] numStrings = part.split(",");
            int[] nums = new int[numStrings.length];
            for (int i = 0; i < numStrings.length; i++) {
                nums[i] = Integer.parseInt(numStrings[i]);
            }
            resultList.add(nums);
        }
        resultList.sort((a, b) -> {
            return Integer.compare(a.length, b.length);
        });

        int[] answer = new int[resultList.get(resultList.size() - 1).length];
        int index = 0;
        int size = 0;
        for (int i = 0; i < resultList.size(); i++) {
            for (int num : resultList.get(i)) {
                set.add(num);
                if (set.size() > size) {
                    answer[index++] = num;
                    size = set.size();
                }
            }
        }

        return answer;
    }
}