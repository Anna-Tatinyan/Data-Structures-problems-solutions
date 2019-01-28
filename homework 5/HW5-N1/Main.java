import java.util.*;
public class Main {
  public static void main(String[] args) {
    LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();
    t.addRoot(5);
    //System.out.println(t.root().getElement());
    t.addRight(t.root(), 6);
    t.addLeft(t.root(), 7);
    Position<Integer> root = t.root();
    Position<Integer> right = t.right(root);
    Position<Integer> left = t.left(root);

        //level 3
        t.addLeft(left, 4);
        t.addRight(left, 5);
        t.addLeft(t.right(root), 6);
        t.addRight(t.right(root), 7);

        //level 4
        t.addLeft(t.left(left), 4);
        t.addRight(t.left(left), 52);
        t.addRight(t.right(left), 120);
        t.addRight(t.right(right), 141);

        //level 5
        t.addLeft(t.left(t.left(left)), 172);
        t.addRight(t.left(t.left(left)), 134);
        t.addLeft(t.right(t.left(left)), 144);
        t.addRight(t.right(t.right(left)), 154);
        t.addRight(t.right(t.right(right)), 176);

        //level 6
        t.addLeft(t.left(t.left(t.left(t.left((root))))), 171);
        t.addRight(t.left(t.left(t.left(t.left((root))))), 180);
        t.addRight(t.left(t.right(t.left(t.left((root))))), 190);
        t.addLeft(t.right(t.right(t.right(right))), 300);
        t.addRight(t.right(t.right(t.right(right))), 201);

        //level 7
        t.addLeft(t.left(t.left(t.left(t.left(t.left((root)))))), 2782);
        t.addRight(t.left(t.left(t.left(t.left(t.left((root)))))), 2113);
        t.addRight(t.left(t.right(t.right(t.right(t.right((root)))))), 66);
        t.addRight(t.right(t.right(t.right(t.right(right)))), 666);
    System.out.println("wholeHeight " + t.wholeHeight()); //height of a given position
    System.out.println("height of given position " + t.depth(right)); //depth of the given posiiton (root => 0)
    //Traverse the left subtree
    //Traverse the right subtree
    //Visit the root.
      System.out.println("postorder start");
    t.postOrderWithoutRecursion();
    System.out.println("");
    //For each node, first the node is visited and then it’s child nodes are put in a FIFO queue.
    System.out.println("breadthfirst start");
    t.breadthfirst();
    System.out.println("");
    // Visit the root.
    // Traverse the left subtree
    // Traverse the right subtree
    System.out.println("preorder start");
    t.iterativePreorder();
    System.out.println("");
    // Traverse the left subtree
    // Visit the root.
    // Traverse the right subtree
    System.out.println("inorder start");
    t.inorderWithoutRecursion();




    /////////////////////////////////////////////////////////////////////////////////
    System.out.println("HERE ANOTHER EXAMPLE");
    LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
    tree.addRoot(5);
    //System.out.println(t.root().getElement());
    tree.addRight(tree.root(), 6);
    tree.addLeft(tree.root(), 7);
    Position<Integer> root2 = tree.root();
    Position<Integer> right2 = tree.right(root2);
    Position<Integer> left2 = tree.left(root2);

        //level 3
        tree.addLeft(left2, 4);
        tree.addRight(left2, 5);
        tree.addLeft(tree.right(root2), 6);
        tree.addRight(tree.right(root2), 7);

    System.out.println("wholeHeight " + tree.wholeHeight()); //height of a given position
    System.out.println("height of given position " + tree.depth(right2)); //depth of the given posiiton (root => 0)
    //Traverse the left subtree
    //Traverse the right subtree
    //Visit the root.
      System.out.println("postorder start");
    tree.postOrderWithoutRecursion();
    System.out.println("");
    //For each node, first the node is visited and then it’s child nodes are put in a FIFO queue.
    System.out.println("breadthfirst start");
    tree.breadthfirst();
    System.out.println("");
    // Visit the root.
    // Traverse the left subtree
    // Traverse the right subtree
    System.out.println("preorder start");
    tree.iterativePreorder();
    System.out.println("");
    // Traverse the left subtree
    // Visit the root.
    // Traverse the right subtree
    System.out.println("inorder start");
    tree.inorderWithoutRecursion();
  }
}