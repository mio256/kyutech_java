// Template Method パターンの一例
class SumAndFact {
    // 和の愚直なコード
    int sumUpTo(int n) {
        int ret = 0;
        for(int i = 0; i <= n; i++) {
            ret = ret + i;
        }
        return ret;
    }
    // 階乗の愚直なコード。上の和のコードと全体の流れがほぼ同じ
    int fact(int n) {
        int ret = 1;
        for(int i = 1; i <= n; i++) {
            ret = ret * i;
        }
        return ret;
    }
    // 和と階乗の計算の共通部分を抜き出した共通ルーチン。
    // 差異は、Op インターフェースのメソッドに隠蔽
    int common(int n, Op op) {
        int ret = op.initValue();
        for(int i = op.start(); i <= n; i++) {
            ret = op.operate(ret, i);
        }
        return ret;
    }
    // 共通ルーチンによる和。Op の実装に SumUpToOp クラスを使う
    int sumUpToByCommon(int n) {
        return common(n, new SumUpToOp());
    }
    // 共通ルーチンによる階乗。Op の実装に Factp クラスを使う
    int factByCommon(int n) {
        return common(n, new FactOp());
    }
    // 確認用の main
    public static void main(String [] args) {
        SumAndFact saf = new SumAndFact();
        // 和について、どちらのルーチンでも同じ
        System.out.println("native   : " + saf.sumUpTo(10));
        System.out.println("by common: " + saf.sumUpToByCommon(10));
        // 階乗について、どちらのルーチンでも同じ
        System.out.println("native   : " + saf.fact(10));
        System.out.println("by common: " + saf.factByCommon(10));
    }
}

// 和と階乗を共通ルーチンで計算する際の、差異毎にメソッドを用意したもの
interface Op {
    int initValue();
    int start();
    int operate(int ret, int i);
}
// 和用の Op 実装
class SumUpToOp implements Op {
    public int initValue() { return 0; }
    public int start() { return 0; }
    public int operate(int ret, int i) { return ret + i; }
}
// 階乗用の Op 実装
class FactOp implements Op {
    public int initValue() { return 1; }
    public int start() { return 1; }
    public int operate(int ret, int i) { return ret * i; }
}
