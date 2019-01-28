

public class Main {
  public static void main(String[] args) {
    DoublyLinkedListDeque<Integer> list = new DoublyLinkedListDeque<>();
    list.addLast(5);
    list.addFirst(8);
    System.out.println(list.removeFirst());
  }
}

 /* Running time O(1), space usage optimal. Approximately same as with array based. Space O(n) same as with array based, as the maximal size of the elements.*/