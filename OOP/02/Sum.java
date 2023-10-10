// 既存のライブラリ（クラス）を使うためのインポート宣言
// import java.util.*; でもよい
import java.util.Scanner;

// 1つ目のクラスの定義。与えられた値を足し込んでいく機能をもったクラス
class Sum {
    // 現在の合計を保持するフィールド。
    // private で、このインスタンスだけが読み書きができるよう制限。
    private int sum;
    // コンストラクタ：インスタンスの初期化を行う特殊なメソッド
    Sum() {
        this.sum = 0;  // 自分(this)の持つフィールド sum を 0 に初期化
    }
    // 受け取った x を現在の合計に足し込むメソッド
    void add(int x) {
        this.sum += x; // 自分(this)の持つフィールド sum を更新
    }
    // [from, to] の区間の整数を全て足し込むメソッド
    void addFromTo(int from, int to) {
        // for文による繰り返し
        for(int i = from; i <= to; i++) {
            this.add(i);  // 自身の他のメソッドを呼ぶことも可能
        }
    }
    // 現在の合計（sum）を返すメソッド
    int getSum() {
        return this.sum;
    }
}

// 2つ目のクラスの定義。こちらはテスト用
class SumCheck {
    // いわゆる main 関数。
    public static void main(String [] args) {
        Sum s = new Sum(); // Sum クラスのインスタンス生成
        // ターミナルから数値を読むための Scanner のインスタンス生成
        Scanner sc = new Scanner(System.in);
        // 整数が入力される限り繰り返す
        while(sc.hasNextInt()) {
            int x = sc.nextInt(); // 入力された整数を読み込む
            if(x == 0) break; // 0 が入力されたら終わり
            // そうでなければその整数を s に与える。
            s.add(x);
            // 現在の合計を出力。文字列は + で連結できる。
            // 文字列に値を + すると、文字列に変換されてから連結される
            System.out.println("current sum = " + s.getSum());
        }
        // 最後に 1 から 10 まで足してみる
        s.addFromTo(1, 10);
        // 合計が 55 増えているはずである
        System.out.println("sum = " + s.getSum());
    }
}
