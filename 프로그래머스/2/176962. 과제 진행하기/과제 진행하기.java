import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        
        int N = plans.length;
        String[] answer = new String[N];
        Integer[] answerIdx = new Integer[N];
        
        // 과제 시작 시간을 기준으로 정렬
        Arrays.sort(plans, (p1, p2) -> p1[1].compareTo(p2[1]));
        
        Integer[][] myPlans = new Integer[N][3];
        
        for (int i = 0; i < plans.length; i++) {
            myPlans[i][0] = i;
            myPlans[i][1] = setMinute(plans[i][1]);
            myPlans[i][2] = Integer.parseInt(plans[i][2]);
        }

        // Stack 사용
        Stack<Integer[]> stack = new Stack<>();
        
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            int before = myPlans[i][1] + myPlans[i][2];  // 현재 과제 완료 시간
            int leftTime = myPlans[i + 1][1] - before;   // 다음 과제까지 남은 시간

            if (leftTime >= 0) {
                answerIdx[count++] = i;
                
                // 스택에 남아있는 멈춘 과제들을 처리
                while (!stack.isEmpty()) {
                    if (leftTime <= 0) break;
                    Integer[] node = stack.pop();
                    if (leftTime - node[1] >= 0) {
                        answerIdx[count++] = node[0];
                        leftTime -= node[1];
                    } else {
                        node[1] -= leftTime;
                        stack.push(node);
                        leftTime = 0;
                    }
                }
            } else {
                stack.push(new Integer[]{i, myPlans[i][2] - (myPlans[i + 1][1] - myPlans[i][1])});
            }
        }

        // 마지막 과제 처리
        answerIdx[count++] = N - 1;
        
        // 스택에 남은 과제들을 처리
        while (!stack.isEmpty()) {
            answerIdx[count++] = stack.pop()[0];
        }
        
        // 정답 배열에 결과 기록
        for (int i = 0; i < N; i++) {
            answer[i] = plans[answerIdx[i]][0];
        }

        return answer;
    }
    
    // 시간을 분 단위로 변환
    public int setMinute(String time) {
        String[] times = time.split(":");
        String hour = times[0];
        String minute = times[1];
        return Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }
}
