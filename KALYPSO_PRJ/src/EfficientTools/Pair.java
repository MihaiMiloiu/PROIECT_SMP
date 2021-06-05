package EfficientTools;

public class Pair<X, Y> {
    X first;
    Y second;

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return this.first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return this.second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

    public String toString() {
        return "Pair{first=" + this.first + ", second=" + this.second + "}";
    }

}
