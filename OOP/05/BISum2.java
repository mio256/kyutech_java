import java.util.Scanner;
import java.math.BigInteger;

// ベースクラス： BigInteger の加算器
class BISum {
    private BigInteger sum;

    BISum() {
        sum = BigInteger.ZERO;
    }

    void add(BigInteger x) {
        sum = sum.add(x);
    }

    BigInteger getSum() {
        return sum;
    }
}

/*
 * BISum を継承し、「加算倍率」の機能を付け加えたクラス BISum2 を定義せよ。
 * 少なくとも、以下の2つのメソッドの追加が必要：
 *
 * ・現在の加算倍率を m にセットするメソッド：
 *
 * void setMultiplier(BigInteger m)
 *
 * ・現在の加算倍率を返すメソッド：
 *
 * BigInteger getMultiplier()
 *
 * 上記のほか、必要なコンストラクタやフィールドを入れること。
 * 更に、親クラスのメソッドの一部をオーバーライドする必要があればすること。
 *
 */

class BISum2 /* fill here */ {

    /* fill here */

}

// BISum2 の動作確認用クラス
class BISum2Check {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BISum s = new BISum2(); // BISum2 は BISum でもある
        for (;;) {
            // 古い倍率を表示（BISum2 のメソッドを呼ぶため BISum2 にキャスト）
            System.out.println("Previous multiplier = "
                    + ((BISum2) s).getMultiplier());
            // 新たな倍率を入力してもらう
            System.out.print("Multiplier?: ");
            if (!sc.hasNext())
                break;
            String m = sc.next();
            if (m.equals("0"))
                break; // 0 なら終わり
            // 倍率セット
            ((BISum2) s).setMultiplier(new BigInteger(m));
            // ループ
            while (sc.hasNext()) {
                String x = sc.next(); // 入力された整数を読み込む
                if (x.equals("0"))
                    break; // 0 が入力されたら終わり
                s.add(new BigInteger(x)); // それ以外なら足す
                System.out.println("current sum = " + s.getSum());
            }
        }
    }
}
