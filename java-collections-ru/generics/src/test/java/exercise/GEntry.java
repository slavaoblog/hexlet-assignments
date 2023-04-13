package exercise;

public class GEntry<T, S> {

    private T value1;
    private S value2;

    public GEntry(T value1, S value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public S getValue2() {
        return value2;
    }
}
