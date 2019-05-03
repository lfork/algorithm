package tree;

import java.util.Stack;

/**
 * @author 98620
 * @date 2018/10/25
 */
public class Tree {

    public Tree(Node root) {
        this.root = root;
    }

    private Node root;


    /**
     * 中序遍历  左根右 LDR
     */
    public void travelTreeLDR() {
        Stack<Node> nodes = new Stack<>();
        Node tempNode = root;
        while (!nodes.empty() || tempNode != null) {
            if ( tempNode != null) {
                nodes.push(tempNode);
                tempNode = tempNode.lChild;
            } else {
                tempNode = nodes.pop();
                System.out.println(tempNode.data);

                tempNode = tempNode.rChild;
            }
        }
    }
}

