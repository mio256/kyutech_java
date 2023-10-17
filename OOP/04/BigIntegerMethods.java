import java.math.*;

class BigIntegerMethods {
    // 配列の和を求める
    BigInteger sum(BigInteger [] bis) {
        return BigInteger.ZERO;
    }
    // 配列の積を求める
    BigInteger prod(BigInteger [] bis) {
        return BigInteger.ONE;
    }
    // 配列の最大値を求める
    BigInteger maximum(BigInteger [] bis) {
        return null;
    }
    // 配列の中の、偶数の数を返す
    int numEvens(BigInteger [] bis) {
        return 0;
    }
    // 階乗 n! を求める
    BigInteger fact(int n) {
        return BigInteger.ZERO;
    }
    // 大きい順に並べる。配列は上書きで。
    void sort(BigInteger [] bis) { // O(n^2) sort
    }
    // おまけ
    // 与えられた a, b, p （a ≠ 0, p は素数） に対して、
    // a * x ≡ b (mod p) を満たす x (0 <= x < p) を返す。
    // そのような x が必ず存在すると仮定して良い
    BigInteger solve(BigInteger a, BigInteger b, BigInteger p) {
        return BigInteger.ZERO;
    }

    // 配列出力用
    static String arrayToString(BigInteger [] bis) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(BigInteger bi : bis) {
            sb.append(bi + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("}");
        return sb.toString();
    }
    public static void main(String [] args) {
        // テスト用のコードはこの main に書いて
        // java BigIntegerMethods とすれば実行できる
        // 適宜自分でテストを書いて自分のコードの動作確認をすること

        BigIntegerMethods bim = new BigIntegerMethods();
        BigInteger [] bis = new BigInteger [] {
            BigInteger.valueOf(133343),
            BigInteger.valueOf(12),
            BigInteger.valueOf(-3567),
            BigInteger.valueOf(409),
            BigInteger.valueOf(-51123),
        };

        System.out.println(bim.sum(bis));
        System.out.println(bim.prod(bis));
        System.out.println(bim.maximum(bis));
        System.out.println(bim.numEvens(bis));
        System.out.println(bim.fact(10));
        bim.sort(bis); // 上書きに注意
        System.out.println(arrayToString(bis));
        System.out.println(bim.solve(new BigInteger("3"),
                                     new BigInteger("4"),
                                     new BigInteger("7")));
        System.out.println(bim.solve(new BigInteger("902397675389897923523"),
                                     new BigInteger("889048645894787899344"),
                                     new BigInteger("170141183460469231731687303715884105727")));


    }
}
