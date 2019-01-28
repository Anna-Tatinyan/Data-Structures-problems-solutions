public class AVLEntry<K,V> implements Entry<K,V> {
    private K k;  // key
    private V v;  // value

    public AVLEntry(K key, V value) {
      k = key;
      v = value;
    }

    public K getKey() { return k; }
    public V getValue() { return v; }

    protected void setKey(K key) { k = key; }
    protected V setValue(V value) {
      V old = v;
      v = value;
      return old;
    }

    public String toString() { return "<" + k + ", " + v + ">"; }
  } 