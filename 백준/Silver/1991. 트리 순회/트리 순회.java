import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    String s;
    Node left;
    Node right;

    public Node(String s, Node left, Node right) {
        this.s = s;
        this.left = left;
        this.right = right;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node node = new Node(null, null, null);

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            insert(node, tmp);
        }

        preOrder(node);
        System.out.println();
        inOrder(node);
        System.out.println();
        postOrder(node);
    }

    public static Node nullCk(Node node, String s) {
        if (s.equals(".")) {
            return null;
        }
        return new Node(s, null, null);
    }

    public static void insert(Node node, String[] tmp) {

        if (node.s == null) {
            node.s = tmp[0];
        }

        if (node.s.equals(tmp[0])) {
            node.left = nullCk(node, tmp[1]);
            node.right = nullCk(node, tmp[2]);
            return;
        } else {
            if (node.left != null) {
                insert(node.left, tmp);
            }
            if (node.right != null) {
                insert(node.right, tmp);
            }
        }

    }

    public static void preOrder(Node node) {
        if (node != null && node.s != null) {
            System.out.print(node.s);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void inOrder(Node node) {
        if (node != null && node.s != null) {
            inOrder(node.left);
            System.out.print(node.s);
            inOrder(node.right);
        }
    }

    public static void postOrder(Node node) {
        if (node != null && node.s != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.s);
        }
    }
}