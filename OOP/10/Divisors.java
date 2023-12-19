import java.util.*;
// Template Method パターンの一例
class Divisors {
    // 約数の数を数える
    Integer numDivisors(int n) {
        int cnt = 0;
        for(int i = 2; i <= n; i++) {
            if(n % i == 0) cnt++;
        }
        return Integer.valueOf(cnt);
    }
    // 約数のリストを返す。コードの流れは上と同じ
    List<Integer> allDivisors(int n) {
        ArrayList<Integer> ds = new ArrayList<Integer>();
        for(int i = 2; i <= n; i++) {
            if(n % i == 0) ds.add(i);
        }
        return ds;
    }
    // 上記２つの計算の共通部分を抜き出した共通ルーチン。
    // 差異は、OpD インターフェースのメソッドに隠蔽
    void common(int n, OpD op) {
        op.init();
        for(int i = 2; i <= n; i++) {
            if(n % i == 0) op.add(i);
        }
        // return する値の型が違う -> op 自体に戻り値をもたせ、後で取り出す
    }
    // 共通ルーチンによる和。Op の実装に SumUpToOp クラスを使う
    int numDivisorsByCommon(int n) {
        NumDivisorsOp op = new NumDivisorsOp();
        common(n, op);
        return op.getValue();
    }
    // 共通ルーチンによる階乗。Op の実装に Factp クラスを使う
    List<Integer> allDivisorsByCommon(int n) {
        AllDivisorsOp op = new AllDivisorsOp();
        common(n, op);
        return op.getValue();
    }
    // リストを文字列にする関数
    static <E> String l2s(List<E> es) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        boolean fst = true;
        for(E e : es) {
            sb.append((fst ? "" : ", ") + e);
            fst = false;
        }
        sb.append("]");
        return sb.toString();
    }
    // 確認用の main
    public static void main(String [] args) {
        Divisors d = new Divisors();
        // 和について、どちらのルーチンでも同じ
        System.out.println("native   : " + d.numDivisors(100));
        System.out.println("by common: " + d.numDivisorsByCommon(100));
        // 階乗について、どちらのルーチンでも同じ
        System.out.println("native   : " + l2s(d.allDivisors(100)));
        System.out.println("by common: " + l2s(d.allDivisorsByCommon(100)));
    }
}

// 共通ルーチンで計算する際の、差異毎にメソッドを用意したもの
// 戻り値が異なるので、それを型パラメータ E とする
interface OpD<E> {
    void init();
    void add(int i);
    E getValue();
}
// numDivisors 用 OpD 実装
// 戻り値の型は Integer なので、OpD<Integer> を実装。
class NumDivisorsOp implements OpD<Integer> {
    int cnt;
    public void init() { cnt = 0; } 
    public void add(int i) { cnt++; }
    public Integer getValue() { return Integer.valueOf(cnt); }
}
// allDivisors 用 OpD 実装
// 戻り値の型は List<Integer> なので、OpD<List<Integer>> を実装。
class AllDivisorsOp implements OpD<List<Integer>> {
    ArrayList<Integer> ds;
    public void init() { ds = new ArrayList<Integer>(); } 
    public void add(int i) { ds.add(i); }
    public List<Integer> getValue() { return ds; }
}
