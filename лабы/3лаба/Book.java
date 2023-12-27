public class Book {
    private String author;
    private String title;
    private int countCopy;

    public Book() {
        this.author = "";
        this.title = "";
        this.countCopy = 0;
    }

    public Book(String author, String title, int countCopy) {
        this.author = author;
        this.title = title;
        this.countCopy = countCopy;
    }

    public String bookInfo() {
        return this.author + "\n" + this.title + "\n" + this.countCopy;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCountCopy() {
        return this.countCopy;
    }
}
