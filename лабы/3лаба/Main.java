public class Main {
    public static void main(String[] args) {
        BookHashTable bhs = new BookHashTable(100);
        bhs.put("978-3-16-12345", new Book("L.N.Tolstoy", "Voyna i Mir", 100));
        bhs.put("978-3-16-12345", new Book("L.N.Tolstoy", "Voyna i Mir", 100));
        Book book1 = bhs.get("978-3-16-12345");
        System.out.println(book1.bookInfo());
        System.out.println(bhs.isEmpty());
        System.out.println(bhs.size());
        bhs.remove("978-3-16-12345");
        System.out.println(bhs.isEmpty());
    }
}
