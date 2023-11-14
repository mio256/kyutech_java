/*
 * String を保持するデータ構造
 */

import java.util.Scanner;

// String を保持するデータ構造のベースクラス。
// 要素を持っているかの判定、要素の追加、要素の取り出しができる。
// 一部メソッドは実装の意味がないので abstract として本体未定義
abstract class StringCollection {
    String name; // テスト用に名前を持つことにする
    int size; // 格納している要素数

    StringCollection(String name) { // コンストラクタ：要素数を初期化する
        // メソッド引数とフィールドとで変数名が被ることは許可されている：
        // this を付けたらフィールド変数、付けなければ引数のローカル変数を意味する。
        this.name = name;
        size = 0;
    }

    String getName() {
        return name;
    } // 名前取得

    boolean isEmpty() { // 要素があるかどうか
        return size == 0;
    }

    abstract void add(String x); // 値を追加する

    abstract String extract(); // 値をひとつ返すとともに、その値を削除

    abstract String peek(); // extract の削除なしバージョン
}

// スタックとキューを実装するためのセル
class StringCell {
    private String val; // セルは、String をひとつ持つ
    private StringCell nxt; // そして、「次のセル」の参照を持つ
    // val の値を持つセルを作るコンストラクタ。「次のセル」も受け取る。

    StringCell(String val, StringCell nxt) {
        this.val = val;
        this.nxt = nxt;
    }

    void setNext(StringCell nxt) { // 「次のセル」の変更
        this.nxt = nxt;
    }

    String getVal() { // 格納されている値の取得
        return val;
    }

    StringCell getNext() { // 「次のセル」を返す
        return nxt;
    }
}

// スタックの実装
class StringStack extends StringCollection {
    StringCell top; // スタックの一番上のセル

    StringStack() {
        // まず、親クラスのコンストラクタを呼ぶ
        super("StringStack");
        // スタックが空なので、top = null (セルなし)
        top = null;
    }

    void add(String x) {
        // 値に x を持ち、
        // 「次のセル」として古い top を指すセルを新たな top に。
        top = new StringCell(x, top);
        size++;
    }

    String extract() {
        // top セルの値を return するために取得し、
        // 「次のセル」に記録されている古い top を復元。
        String x = top.getVal();
        top = top.getNext();
        size--;
        return x;
    }

    String peek() {
        // top セルの値を見れば良い
        return top.getVal();
    }
}

// キューの実装
class StringQueue extends StringCollection {
    StringCell top, bot; // キューの先頭と末尾

    StringQueue() {
        super("StringQueue");
        // キューが空
        bot = null;
        top = null;
    }

    void add(String x) {
        // 値に x を持つセル c を作る
        StringCell c = new StringCell(x, null);
        // キューが空ならば、そのセル c を top と bot に
        if (size == 0) {
            top = c;
            bot = c;
        } else { // そうでなければ
            // top の「次のセル」を c にして、c を新たな top に
            top.setNext(c);
            top = c;
        }
        size++;
    }

    String extract() {
        // bot セルの値を return するために取得し、
        // bot の「次のセル」に bot を更新。
        String x = bot.getVal();
        bot = bot.getNext();
        size--;
        if (size == 0)
            top = null;
        return x;
    }

    String peek() {
        // bot セルの値を見れば良い
        return bot.getVal();
    }
}

// =========== 以下は、ここまでに定義したクラスを使ってみた例 ============

// キュートスタックに同じ操作をしたときの違いをみる
class StringCollectionCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringCollection c1 = new StringQueue();
        StringCollection c2 = new StringStack();
        System.out.println("Input words (finish by initial '0', remove by initial sign '-'):");
        while (sc.hasNext()) {
            String x = sc.next();
            if (x.charAt(0) == '0')
                break;
            if (x.charAt(0) != '-') { // add
                c1.add(x);
                c2.add(x);
            } else { // remove (extract)
                c1.extract();
                c2.extract();
            }
        }
        System.out.println("Words in the data structures:");
        for (int i = 0; i < 2; i++) {
            StringCollection ic = null;
            if (i == 0)
                ic = c1;
            else
                ic = c2;
            System.out.print(" " + ic.getName() + ":");
            // 入力されたものを出力
            while (!ic.isEmpty()) {
                System.out.print(" " + ic.extract());
            }
            System.out.println();
        }
    }
}
