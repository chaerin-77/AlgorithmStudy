import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 해결 프로세스
 * 1. 그룹 별 멤버 관리, 멤버에서 그룹 명도 지정 가능해야 함 -> 맵, 리스트
 * 2. 키 값으로 겁색시 map.keySet()을 이용하여 key 값으로 하나하나 확인 가능
 */

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            int cnt = Integer.parseInt(br.readLine());
            List<String> member = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                member.add(br.readLine());
            }
            Collections.sort(member);
            map.put(name, member);
        }
        for (int i = 0; i < M; i++) {
            String word = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if (type == 0) {
                List<String> member = map.get(word);
                for (String s : member) {
                    sb.append(s).append("\n");
                }
            } else {
                for (String s : map.keySet()) {
                    if (map.get(s).contains(word)) {
                        sb.append(s).append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }
}