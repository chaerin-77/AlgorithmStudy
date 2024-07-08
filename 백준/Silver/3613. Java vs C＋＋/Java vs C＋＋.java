import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (Character.isUpperCase(name.charAt(0))) {
            System.out.println("Error!");
            return;
        }
        if (name.charAt(0) == '_' || name.charAt(name.length() - 1) == '_') {
            System.out.println("Error!");
            return;
        }
        if (name.matches(".*_{2,}.*")) {
            System.out.println("Error!");
            return;
        }
        if (name.matches(".*[A-Z].*") && name.contains("_")) {
            System.out.println("Error!");
            return;
        }
        if (name.contains("_")) {
            String[] list = name.split("_");
            sb.append(list[0]);
            for (int i = 1; i < list.length; i++) {
                for (int j = 0; j < list[i].length(); j++) {
                    if (j == 0) sb.append(Character.toUpperCase(list[i].charAt(j)));
                    else sb.append(list[i].charAt(j));
                }
            }
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isUpperCase(name.charAt(i))) sb.append("_");
                sb.append(Character.toLowerCase(name.charAt(i)));
            }
        }
        System.out.println(sb);
    }
}