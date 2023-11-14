import java.util.*;

// 適切なインターフェースを実装せよ
class RangeX implements Iterator<Integer>, Iterable<Integer> {
    int end, cur, step;

    RangeX(int s, int e, int d) {
        this.cur = s;
        this.end = e;
        this.step = d;
    }

    // Iterator<Integer> のメソッド
    public boolean hasNext() {
        return cur < end;
    }

    // Iterator<Integer> のメソッド
    public Integer next() {
        Integer ret = Integer.valueOf(cur);
        cur += step;
        return ret;
    }

    // Iterable<Integr> のメソッド
    public Iterator<Integer> iterator() {
        return this;
    }
}

// RangeX の動作チェック用
class RangeXCheck {
    public static void main(String[] args) {
        for (Integer i : new RangeX(3, 10, 2)) {
            System.out.println("i = " + i);
        }
    }
}
