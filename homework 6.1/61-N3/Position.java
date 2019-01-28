import java.util.*;
public interface Position<E> {
  E getElement() throws IllegalStateException;
}