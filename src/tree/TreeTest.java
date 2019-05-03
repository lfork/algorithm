package tree;

/**
 *
 * @author 98620
 * @date 2018/10/25
 */
public class TreeTest {
    public static void main(String[] args) {

        Node root = new Node(1);
        Tree tree = new Tree(root);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6= new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        root.lChild = node1;
        root.rChild = node2;

        node1.lChild = node3;
        node1.rChild = node4;

        node3.lChild = node5;

        node5.lChild = node6;
        node5.rChild = node7;

        node2.lChild = node8;
        node2.rChild = node9;

        tree.travelTreeLDR();

    }
}
