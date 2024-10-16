/*
 * 문제 해결 프로세스
 * 1. 들어온 광물들을 그룹화해서 피로도 계산
 * 2. 피로도가 높은 순으로 좋은 곡괭이 먼저 사용하기
 * 3. 광물의 개수가 곡괭이 소모량보다 많은 경우 예외처리
*/

import java.util.*;

public class Solution {
    class Group implements Comparable<Group> {
        int stone = 0, iron = 0, dia = 0;
        int tired = 0;

        public Group(int s, int i, int d, int t) {
            this.stone = s;
            this.iron = i;
            this.dia = d;
            this.tired = t;
        }

        @Override
        public int compareTo(Group o) {
            return Integer.compare(o.tired, this.tired);
        }
    }

    static PriorityQueue<Group> q;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        q = new PriorityQueue<>();
        int totalPicks = picks[0] + picks[1] + picks[2];
        int totalMinerals = minerals.length;
        
        if (totalPicks * 5 < totalMinerals) {
            minerals = Arrays.copyOf(minerals, totalPicks * 5);
        }

        getGroup(minerals);

        while (!q.isEmpty()) {
            if (picks[0] > 0) {
                picks[0]--;
                Group g = q.poll();
                answer += (g.stone + g.iron + g.dia);
            } else if (picks[1] > 0) {
                picks[1]--;
                Group g = q.poll();
                answer += (g.stone + g.iron + g.dia * 5);
            } else if (picks[2] > 0) {
                picks[2]--;
                Group g = q.poll();
                answer += (g.stone + g.iron * 5 + g.dia * 25);
            } else {
                break;
            }
        }

        return answer;
    }

    public void getGroup(String[] minerals) {
        for (int i = 0; i < minerals.length; i += 5) {
            int stone = 0, iron = 0, dia = 0, tired = 0;
            for (int j = 0; j < 5; j++) {
                if (i + j >= minerals.length) break;

                if (minerals[i + j].equals("stone")) {
                    stone++;
                    tired += 1;
                } else if (minerals[i + j].equals("iron")) {
                    iron++;
                    tired += 5;
                } else {
                    dia++;
                    tired += 25;
                }
            }
            q.offer(new Group(stone, iron, dia, tired));
        }
    }
}
