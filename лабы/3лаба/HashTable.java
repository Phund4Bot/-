import java.util.LinkedList;

public abstract class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[1000];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[initialCapacity];
        size = 0;
    }

    private int hash(K key) {
        try{
            return key.hashCode() % (table.length);
        } catch(ArithmeticException e) {
            System.out.println("Your table is 0 length");
            System.exit(0);
            return 0;
        }
        
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<K, V>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}