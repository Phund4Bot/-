public class Stack<T> {
    private T[] data;
    private int size;
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        try {
            data[size] = element;
            size++;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Stack is full");
        }
    }

    public T pop() {
        if (size == 0) return null;
        T el = data[size-1];
        data[size-1] = null;
        size--;
        return el;
    }

    public T peek() {
        return size == 0 ? null : data[size-1];
    }
}
