class Main {
  public static void main(String[] args) {
    AVLTree<Integer, Integer> tree = new AVLTree<>();
    tree.insert(0,1);
    tree.insert(2,2);
    tree.insert(8,4);
    tree.insert(9,2);
    tree.insert(1,6);
    tree.insert(64,6);
    // tree.insert(10,6);
    tree.remove(2);
    
    for(Position<Entry<Integer,Integer>> el: tree.inorder()) {
      System.out.println(el.getElement());
    }  
      }
}