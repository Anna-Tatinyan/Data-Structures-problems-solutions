
import java.util.Comparator;

public class AVLTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

  /** Constructs an empty map using the natural ordering of keys. */
  public AVLTree() {
  };

  private static class AVLEntry<K, V> implements Entry<K, V> {
    private K k;
    private V v;

    public AVLEntry(K k, V v) {
      this.k = k;
      this.v = v;
    }

    public void setKey(K k) {
      this.k = k;
    }

    public void setValue(V v) {
      this.v = v;
    }

    public K getKey() {
      return k;
    }

    public V getValue() {
      return v;
    }

    public String toString() {
      return "<" + k + ", " + v + ">";
    }
  }

  protected class AVLNode<K, V> implements Position<Entry<K, V>> {
    private Entry<K, V> element;
    private int height;
    private AVLNode<K, V> parent;
    private AVLNode<K, V> left;
    private AVLNode<K, V> right;

    public AVLNode(Entry<K, V> e, AVLNode<K, V> above, AVLNode<K, V> leftChild, AVLNode<K, V> rightChild) {
      element = e;
      parent = above;
      left = leftChild;
      right = rightChild;
    }

    public AVLNode<K, V> getParent() {
      return parent;
    }

    public AVLNode<K, V> getLeft() {
      return left;
    }

    public AVLNode<K, V> getRight() {
      return right;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int h) {
      height = h;
    }

    public void setElement(Entry<K, V> e) {
      element = e;
    }

    public void setParent(AVLNode<K, V> parentNode) {
      parent = parentNode;
    }

    public void setLeft(AVLNode<K, V> leftChild) {
      left = leftChild;
    }

    public void setRight(AVLNode<K, V> rightChild) {
      right = rightChild;
    }

    public Entry<K, V> getElement() {
      return element;
    }

    public String toString() {
      return element.toString();
    }

  }

  private AVLNode<K, V> createNode(K key, V value) {
    AVLEntry<K, V> entry = new AVLEntry<K, V>(key, value);
    AVLNode<K, V> myEntry = new AVLNode<K, V>(entry, null, null, null);
    return myEntry;
  }

  private AVLNode<K, V> root = null;

  public Position<Entry<K, V>> root() {
    return root;
  }

  private int size = 0;
  private Comparator<K> comp = new DefaultComparator<>();

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  protected int compare(K a, K b) {
    return comp.compare(a, b);
  }

  protected AVLNode<K, V> AVLValidate(Position<Entry<K, V>> p) throws IllegalArgumentException {
    if (!(p instanceof AVLNode))
      throw new IllegalArgumentException("Not valid position type");
    AVLNode<K, V> node = (AVLNode<K, V>) p;
    if (node.getParent() == node)
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  public Position<Entry<K, V>> left(Position<Entry<K, V>> p) throws IllegalArgumentException {
    AVLNode<K, V> node = AVLValidate(p);
    return node.getLeft();
  }

  public Position<Entry<K, V>> right(Position<Entry<K, V>> p) throws IllegalArgumentException {
    AVLNode<K, V> node = AVLValidate(p);
    return node.getRight();
  }

  public Position<Entry<K, V>> addRoot(Entry<K, V> entry) throws IllegalStateException {

    return null;
  }

  private int getBalance(AVLNode<K, V> N) {
    if (N == null) {
      return 0;
    }
    return height(N.getLeft()) - height(N.getRight());
  }

  private int height(AVLNode<K, V> node) {
    if (node == null) {
      return 0;
    }
    return node.getHeight();
  }

  private AVLNode<K, V> BSTinsert(K k, V v) {
    AVLNode<K, V> newest = createNode(k, v);
    if (isEmpty()) {
      root = newest;
    } else {
      BSTinsertHelper(root, newest, k);
    }
    size++;
    return newest;
  }

  private void BSTinsertHelper(AVLNode<K, V> root, AVLNode<K, V> newest, K k) {

    if (compare(k, root.getElement().getKey()) == 0) {
      root.setElement(newest.getElement());
      size--;
    } else if (compare(k, root.getElement().getKey()) > 0) {
      if (right(root) == null) {
        newest.setParent(root);
        root.setRight(newest);
      } else {
        BSTinsertHelper((AVLNode<K, V>) right(root), newest, k);
      }
    } else {
      if (left(root) == null) {
        newest.setParent(root);
        root.setLeft(newest);
      } else {
        BSTinsertHelper((AVLNode<K, V>) left(root), newest, k);
      }
    }
  }

  private void rotateLeft(AVLNode<K, V> root) {
    if (root == null) {
      return;
    }
    AVLNode<K, V> pivot = root.getRight();
    root.setRight(pivot.getLeft());
    if (root.getRight() != null) {
      root.getRight().setParent(root);
    }
    pivot.setLeft(root);
    if (root.getParent() != null) {
      if (root == root.getParent().getLeft()) {
        root.getParent().setLeft(pivot);
      } else {
        root.getParent().setRight(pivot);
      }
      pivot.setParent(root.getParent());
    } else {
      this.root = pivot;
      pivot.setParent(null);
    }
    root.setParent(pivot);
  }

  private void rotateRight(AVLNode<K, V> root) {
    if (root == null) {
      return;
    }
    AVLNode<K, V> pivot = root.getLeft();
    root.setLeft(pivot.getRight());
    if (root.getLeft() != null) {
      root.getLeft().setParent(root);
    }
    pivot.setRight(root);
    if (root.getParent() != null) {
      pivot.setParent(root.getParent());
      if (root.getParent().getLeft() == root) {
        root.getParent().setLeft(pivot);
      } else {
        root.getParent().setRight(pivot);
      }
    } else {
      pivot.setParent(null);
    }
    root.setParent(pivot);
  }

  public V insert(K key, V value) {
    AVLNode<K, V> newest = BSTinsert(key, value);
    AVLNode<K, V> p = newest.getParent();
    int dif = getBalance(p);

    if (dif > 1) {
      if (getBalance(p.getLeft()) < 0) {
        rotateLeft(p.getLeft());
      }
      rotateRight(p);
    } else if (dif < -1) {
      if (getBalance(p.getRight()) > 0) {
        rotateRight(p.getRight());
      }
      rotateLeft(p);
    }
    while (p != null) {
      p.setHeight(1 + Math.max(height(p.getRight()), height(p.getLeft())));
      p = p.getParent();
    }
    return newest.getElement().getValue();
  }


  public void remove(K k) {
    Position<Entry<K, V>> p = search(k);
    if (p == null) {
      return;
    }
    AVLNode<K, V> node = AVLValidate(p);
    AVLNode<K, V> temp = node.getParent();
    if (node.getLeft() == null) {
      deepReplace(node, node.getRight());
    } else if (node.getRight() == null) {
      deepReplace(node, node.getLeft());
    } else {
      AVLNode<K, V> min = node.getRight();
      while (min.getLeft() != null) {
        min = min.getLeft();
        temp = min;
      }
      if (min.getParent() != node) {
        deepReplace(min, min.getRight());
        min.setRight(node.getRight());
        min.getRight().setParent(min);
      }
      deepReplace(node, min);
      min.setLeft(node.getLeft());
      min.getLeft().setParent(min);
    }

    while(temp != null) {
      temp.setHeight(1 + Math.max(height(temp.getRight()), height(temp.getLeft())));
      int dif = getBalance(temp);

      if (dif > 1) {
        if (getBalance(temp.getLeft()) < 0) {
          rotateLeft(temp.getLeft());
        }
        rotateRight(temp);
      } else if (dif < -1) {
        if (getBalance(temp.getRight()) > 0) {
          rotateRight(temp.getRight());
        }
        rotateLeft(temp);
      } else {
        break;
      }
      temp = temp.getParent();
    }
    size--;
  }

  private void deepReplace(AVLNode<K, V> u, AVLNode<K, V> v) {
    if (u.getParent() == null) {
      root = v;
    } else if (u == u.getParent().getLeft()) {
      u.getParent().setLeft(v);
    } else {
      u.getParent().setRight(v);
    }
    if (v != null) {
      v.setParent(u.getParent());
    }
  }

  public Position<Entry<K, V>> search(K k) {
    return searchHelper(root, k);
  }

  private Position<Entry<K, V>> searchHelper(Position<Entry<K, V>> node, K k) {
    if (node == null || node.getElement().getKey().equals(k)) {
      return node;
    } else if (compare(k, node.getElement().getKey()) > 0) {
      return searchHelper(right(node), k);
    } else {
      return searchHelper(left(node), k);
    }
  }
}
