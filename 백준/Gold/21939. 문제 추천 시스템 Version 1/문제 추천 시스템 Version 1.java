import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map.put(P, L);
        }

        TreeMap<Integer, Integer> sortedMap = new TreeMap<>((key1, key2) -> {
            Integer val1 = map.get(key1);
            Integer val2 = map.get(key2);
            if (val1 == null || val2 == null) return 0;
            int compare = val1.compareTo(val2);
            if (compare == 0) return key1.compareTo(key2);
            return compare;
        });
        sortedMap.putAll(map);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                map.put(P, L);
                sortedMap.put(P, L);
            } else if (command.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                sortedMap.remove(P);
            } else {
                int order = Integer.parseInt(st.nextToken());
                Map.Entry<Integer, Integer> temp;
                if (order == 1) {
                    temp = sortedMap.lastEntry();
                } else {
                    temp = sortedMap.firstEntry();
                }
                sb.append(temp.getKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}