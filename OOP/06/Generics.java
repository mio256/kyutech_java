/*
 * Generics を使いたい例：ペア
 */

import java.math.BigInteger;

/* まずは Generics なしでのペアの定義 */

// 「String のペア」のクラス
class StringPair {
    private String fst;
    private String snd;

    StringPair(String fst, String snd) {
        this.fst = fst;
        this.snd = snd;
    }

    String getFirst() {
        return fst;
    }

    String getSecond() {
        return snd;
    }
}

// 「BigInteger のペア」のクラス
class BIPair {
    private BigInteger fst;
    private BigInteger snd;

    BIPair(BigInteger fst, BigInteger snd) {
        this.fst = fst;
        this.snd = snd;
    }

    BigInteger getFirst() {
        return fst;
    }

    BigInteger getSecond() {
        return snd;
    }
}

/*
 * 上の２つのクラスは、String と BigInteger が置き換わっただけのコード。
 * でも、String と BigInteger は関係のないクラスだから、別々のコードが必要。
 * しかし、ペアにしたいクラスが現れる度にペアクラスを実装するのは、面倒極まりない。
 */

// Generics を用いたペアの定義：「E クラスのペア」
// E は、このクラスを使う時に具体的なクラスをしていることになる。
// この、後で代入される型（型変数）を、クラス名の後に < > で括って書く。
// この型変数は、クラスの定義内で変数の型などに使用できる。
class Pair<E> {
    private E fst;
    private E snd;

    Pair(E fst, E snd) {
        this.fst = fst;
        this.snd = snd;
    }

    E getFirst() {
        System.out.println("Pair<E>'s getFirst");
        return fst;
    }

    E getSecond() {
        System.out.println("Pair<E>'s getSecond");
        return snd;
    }
}

// 型変数は複数あっても良い
// 例えば異なるクラス E, F のペアは次のように定義される
class Pair2<E, F> {
    private E fst;
    private F snd;

    Pair2(E fst, F snd) {
        this.fst = fst;
        this.snd = snd;
    }

    E getFirst() {
        System.out.println("Pair2<E,F>'s getFirst");
        return fst;
    }

    F getSecond() {
        System.out.println("Pair2<E,F>'s getSecond");
        return snd;
    }
}

// Generics を使用する例
class GenericsCheck {
    public static void main(String[] args) {
        // Generics を使ったクラスは、< >内に具体的なクラスを指定して使う
        Pair<BigInteger> bis = new Pair<BigInteger>(BigInteger.ZERO,
                BigInteger.ONE);
        Pair<String> strs = new Pair<String>("Hello", "World");

        // どちらも Pair<E> に実装したメソッドが使われることを確認
        System.out.println(bis.getFirst());
        System.out.println(bis.getSecond());
        System.out.println(strs.getFirst());
        System.out.println(strs.getSecond());

        // Generics を使ったクラスは、< >内に具体的なクラスを指定して使う
        Pair2<BigInteger, String> bistr = new Pair2<BigInteger, String>(BigInteger.ZERO, "Hello");
        System.out.println(bistr.getFirst());
        System.out.println(bistr.getSecond());
    }
}
