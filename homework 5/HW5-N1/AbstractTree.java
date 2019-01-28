import java.util.*;
abstract class AbstractTree<E> implements Tree<E> {

  public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }


  public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }

  public boolean isRoot(Position<E> p) { return p == root(); }

  public int numChildren(Position<E> p) {
    int count=0;
    for (Position child : children(p)) count++;
    return count;
  }

  public int size() {
    int count=0;
    for (Position p : positions()) count++;
    return count;
  }

  public boolean isEmpty() { return size() == 0; }

  public int depth(Position<E> p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }

  public int height(Position<E> p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (Position<E> c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }

  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } // return element!
    public void remove() { posIterator.remove(); }
  }

  public Iterator<E> iterator() { return new ElementIterator(); }

  public Iterable<Position<E>> positions() { return postorder(); }

  private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(snapshot.size(), p);                       // for preorder, we add position p before exploring subtrees
    for (Position<E> c : children(p))
      preorderSubtree(c, snapshot);
  }

  public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return (Iterable<Position<E>>) snapshot;
  }

    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c : children(p)) {
      postorderSubtree(c, snapshot);
    }

    snapshot.add(snapshot.size(), p);
    System.out.printf("%s ", p.getElement());
                         // for postorder, we add position p after exploring subtrees
  }

  public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(root(), snapshot);   // fill the snapshot recursively
    return (Iterable<Position<E>>) snapshot;
  }
}