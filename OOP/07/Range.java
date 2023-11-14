import java.util.*;

// インターフェースの実装例
class Range implements Iterator<Integer>, Iterable<Integer> {
    int end;
    int cur;

    Range(int e) {
        this.end = e;
        this.cur = 0;
    }

    // Iterator<Integer> のメソッド
    public boolean hasNext() {
        return cur < end;
    }

    // Iterator<Integer> のメソッド
    public Integer next() {
        Integer ret = Integer.valueOf(cur);
        cur++;
        return ret;
    }

    // Iterable<Integr> のメソッド
    public Iterator<Integer> iterator() {
        return this;
    }
}

// Range の動作チェック用
class RangeCheck {
    public static void main(String[] args) {
        // Range が Iterable<Integer> を継承（実装）するので、
        // 拡張for文を使うことができる
        for (Integer i : new Range(5)) {
            System.out.println("i = " + i);
        }
    }

}
