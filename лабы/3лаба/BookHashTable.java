public class BookHashTable extends HashTable<String, Book> {
    public BookHashTable() {
        super();
    }
    
    public BookHashTable(int initialCapacity) {
        super(initialCapacity);
    }
}
