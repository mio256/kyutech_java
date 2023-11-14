/*
 * BigInteger を保持するデータ構造
 */

import java.util.Scanner;
import java.math.BigInteger;

// BigInteger を保持するデータ構造のベースクラス。
// 要素を持っているかの判定、要素の追加、要素の取り出しができる。
// 一部メソッドは実装の意味がないので abstract として本体未定義
abstract class BICollection {
    String name;  // テスト用に名前を持つことにする
    int size;     // 格納している要素数
    BICollection(String name) { // コンストラクタ：要素数を初期化する
        // メソッド引数とフィールドとで変数名が被ることは許可されている：
        // 　this を付けたらフィールド変数、付けなければ引数のローカル変数を意味する。
        this.name = name;
        size = 0;
    }
    String getName() { return name; } // 名前取得
    boolean isEmpty() { // 要素があるかどうか
        return size == 0;
    }
    abstract void add(BigInteger x); // 値を追加する
    abstract BigInteger extract();   // 値をひとつ返すとともに、その値を削除
    abstract BigInteger peek();      // extract の削除なしバージョン
}

// スタックとキューを実装するためのセル
class BICell {
    private BigInteger val;    // セルは、BigInteger をひとつ持つ
    private BICell nxt; // そして、「次のセル」の参照を持つ
    // val の値を持つセルを作るコンストラクタ。「次のセル」も受け取る。
    BICell(BigInteger val, BICell nxt) {
        this.val = val;
        this.nxt = nxt;
    }
    void setNext(BICell nxt) { // 「次のセル」の変更
        this.nxt = nxt;
    }
    BigInteger getVal() {       // 格納されている値の取得
        return val;
    }
    BICell getNext() {  // 「次のセル」を返す
        return nxt;
    }
}

// スタックの実装
class BIStack extends BICollection {
    BICell top; // スタックの一番上のセル
    BIStack() {
        // まず、親クラスのコンストラクタを呼ぶ
        super("BIStack");
        // スタックが空なので、top = null (セルなし)
        top = null;
    }
    void add(BigInteger x) {
        // 値に x を持ち、
        // 「次のセル」として古い top を指すセルを新たな top に。
        top = new BICell(x, top);
        size++;
    }
    BigInteger extract() {
        // top セルの値を return するために取得し、
        // 「次のセル」に記録されている古い top を復元。
        BigInteger x = top.getVal();
        top = top.getNext();
        size--;
        return x;
    }
    BigInteger peek() {
        // top セルの値を見れば良い
        return top.getVal();
    }
}


// キューの実装
class BIQueue extends BICollection {
    BICell top, bot; // キューの先頭と末尾
    BIQueue() {
        super("BIQueue");
        // キューが空 
        bot = null;
        top = null;
    }
    void add(BigInteger x) {
        // 値に x を持つセル c を作る
        BICell c = new BICell(x, null);
        // キューが空ならば、そのセル c を top と bot に
        if(size == 0) {
            top = c;
            bot = c;
        } else { // そうでなければ
            // top の「次のセル」を c にして、c を新たな top に
            top.setNext(c);
            top = c;
        }
        size++;
    }
    BigInteger extract() {
        // bot セルの値を return するために取得し、
        // bot の「次のセル」に bot を更新。
        BigInteger x = bot.getVal();
        bot = bot.getNext();
        size--;
        if(size == 0) top = null;
        return x;
    }
    BigInteger peek() {
        // bot セルの値を見れば良い
        return bot.getVal();
    }
}


//=========== 以下は、ここまでに定義したクラスを使ってみた例 ============

// キュートスタックに同じ操作をしたときの違いをみる
class BICollectionCheck {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        BICollection c1 = new BIQueue();
        BICollection c2 = new BIStack();
        System.out.println("Input integers (finish by initial '0', remove by initial sign '-'):");
        while(sc.hasNext()) {
            String x = sc.next();
            if(x.charAt(0) == '0') break;
            if(x.charAt(0) != '-') { // add
                c1.add(new BigInteger(x));
                c2.add(new BigInteger(x));
            } else { // remove (extract)
                c1.extract();
                c2.extract();
            }
        }
        System.out.println("Words in the data structures:");
        for(int i = 0; i < 2; i++) {
            BICollection ic = null;
            if(i == 0) ic = c1;
            else       ic = c2;
            System.out.print(" " + ic.getName() + ":");
            // 入力されたものを出力
            while(!ic.isEmpty()) {
                System.out.print(" " + ic.extract());
            }
            System.out.println();
        }
    }
}

