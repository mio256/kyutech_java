/*
 * 関数定義も可能にしたプログラム電卓.
 * 再帰呼び出しも可能. 
 * ただし，引数以外の外側の変数（グローバル変数）は使えなくしている. 
 * コンパイル & 実行：
 *
 *   javac Calculator.java IntCalc.java MemoCalc.java ProgramCalc.java FunctionCalc.java
 *   java FunctionCalc fact.txt
 *   java FunctionCalc fib.txt
 *
 * どの様なコードであるかは fact.txt と fib.txt を参照
 */
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 関数の終了のための例外で, return されたとき「結果」を保持する. 
 * ただし, 例外は Generics が使えないので Object として保持. 
 */
class FunctionReturned extends RuntimeException {
    /**
     * return されたときの「結果」. 
     * 本当は Generics を使って {@code Result res;} としたいが, Java の規約上これは不可能. 
     */
    Object res;
    /**
     * return されたときの「結果」を受け取る. 
     * @param res return されたときの「結果」. 
     */
    FunctionReturned(Object res) {
        this.res = res;
    }
}

/**
 * 関数の終了をする「コマンド」. 
 * 深いところに入っていたも一気に抜け出せるように, 例外を投げて脱出する形となっている. 
 * <p><blockquote><pre>{@code
 * return
 * }</pre></blockquote><p>
 * という1行「ブロック」を受け付ける. この時点での「結果」が返されることになる. 
 * @param Result 「結果」の型
 */
class Return<Result> implements Command<Result> {
    /**
     * 関数を return して戻る. 
     * 関数の実行を始めるところで例外がキャッチされることを期待している. 
     */
    public Result tryExec(final String [] ts, final List<String> block, final Result res) {
        if(block.size() == 1 && ts.length == 1 && "return".equals(ts[0])) throw new FunctionReturned(res);
        return null;
    }
}

/**
 * 関数を表すクラス. 関数の引数の名前と関数のコードを保持. 
 */
class Function<Result> {
    /**
     * 関数本体のコードの文字列. 
     */
    String code;
    /**
     * 関数の仮引数の名前のリスト. 
     */ 
    List<String> args;
    /**
     * 関数の仮引数リストと関数本体のコードを受け取るコンストラクタ. 
     * @param args 仮引数のリスト
     * @param code 関数本体のコード
     */
    Function(List<String> args, String code) {
        this.args = new ArrayList<String>();
        this.args.addAll(args);
        this.code = code;
    }
}

/**
 * 定義された関数も記憶するメモリ. 
 * 関数実行のために, 関数実行開始時にその外部の変数を隠す機能を持つ. 
 * @param Result 「結果」の型. 
 */
class MemoryWithFunc<Result> extends Memory<Result> {
    /**
     * 関数の中で関数を呼ぶこともあるため, それぞれのレベルのためのメモリ（技術的には「環境」という）を記憶する. 
     */
    LinkedList<HashMap<String, Result>> mems;
    /**
     * 関数定義のための連想配列. 
     * なお, 同じ名前の関数が定義された場合, 実行時間に沿って最後に定義された関数が有効になっている. 
     * 場合によっては直感に反するので, その場合には関数の連想配列のリストも考える必要が出てくる. 
     */
    HashMap<String, Function<Result>> funcs;
    /**
     * コンストラクタ. 
     */
    MemoryWithFunc() {
        funcs = new HashMap<String, Function<Result>>();
        mems = new LinkedList<HashMap<String, Result>>();
    }
    /**
     * 関数を登録する. 
     * @param fname 関数名
     * @param f 関数の情報
     */
    void putFunc(String fname, Function<Result> f) {
        funcs.put(fname, f);
    }
    /**
     * 関数を取り出す. 与えられた関数名の関数がない場合には {@code null} が返される. 
     * @param fname 関数名
     * @return 与えられた関数名に関連づいている関数の情報. なければ {@code null}
     */
    Function<Result> getFunc(String fname) {
        return funcs.get(fname);
    }
    /**
     * 関数呼び出し時に, その時点までの変数を隠し, 仮引数しか変数として定義されてない状態にする. 
     * @param args 仮引数のリスト
     * @param vals 仮引数に入れる値（つまり, 実際に関数呼び出しするときの引数の値）
     */
    void pushNewMemory(List<String> args, List<Result> vals) {
        mems.addLast(mem);
        mem = new HashMap<String, Result>();
        int n = args.size();
        for(int i = 0; i < n; i++) {
            mem.put(args.get(i), vals.get(i));
        }
    }
    /**
     * 隠した変数を復元する. 
     */
    void popMemory() {
        mem = mems.removeLast();
    }
}

/**
 * 関数呼び出しの「コマンド」. 
 * 簡単のため, 引数は変数でのみ指定可能としている. 
 * <p><blockquote><pre>{@code
 * call f x_1 ... x_n
 * }</pre></blockquote><p>
 * という1行「ブロック」を受け入れる. 
 * 指定された関数 {@code f} の引数の数は, 与えられた引数の数 {@code n} の数に一致していなければならない. 
 */
class Call<Result> extends CommandWithMemory<Result> {
    /**
     * 関数を実行する電卓に渡すコマンドリスト. 
     */
    List<Command<Result>> comms;
    /**
     * 変数用の Memory と, コードブロックを実行するための電卓の構築に使うコマンドリストを受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     * @param comms コードブロックの実行に使うコマンドリスト
     */
    Call(MemoryWithFunc<Result> mem, List<Command<Result>> comms) {
        super(mem);
        this.comms = comms;
    }
    /**
     * 指定された関数をメモリに問い合わせ, 存在するなら引数の数をチェックし, 
     * OK なら外側の変数を隠して仮引数だけが定義された状態で関数を実行する. 
     *
     */
    public Result tryExec(final String [] ts, final List<String> block, final Result res) {
        if(block.size() != 1 || ts.length < 1 || !("call".equals(ts[0]))) return null;
        // 関数の問い合わせをするので、親クラスが Memory だと思っている mem を MemoryWithFunc にキャスト
        MemoryWithFunc<Result> mem2 = (MemoryWithFunc<Result>)mem;
        // 関数を問い合わせる
        Function<Result> f = mem2.getFunc(ts[1]);
        if(f == null) throw new RuntimeException("Unknown function: " + ts[1]);
        // 実際の引数の値を集める
        ArrayList<Result> vals = new ArrayList<Result>();
        for(int i = 2; i < ts.length; i++) {
            Result v = mem2.get(ts[i]);
            if(v == null) throw new UnknownVariableException(ts[i]);
            vals.add(v);
        }
        // 引数の数をチェック： あってないときは例外
        if(vals.size() < f.args.size()) {
            throw new RuntimeException("Too few function arguments given");
        }
        if(vals.size() > f.args.size()) {
            throw new RuntimeException("Too many function arguments given");
        }
        BufferedReader br = new BufferedReader(new StringReader(f.code));
        CalculatorNI<Result> calc = new CalculatorNI<Result>(br, comms);
        // 仮定： すべての「コマンド」が同じ MemoryWithFunc を使っているとする。
        // 関数で使える変数は、引数の変数のみ。それ以外の引数の記憶は隠す。
        mem2.pushNewMemory(f.args, vals);
        Result ret = null;
        // 関数を実行
        try {
            ret = calc.run(res);
        } catch(FunctionReturned e) { // 一気に return で抜けてきた場合
            // ここで unchecked or unsafe operations とコンパイラに文句言われるのは仕方ない. Java の限界. 
            ret = (Result)(e.res);
        }
        // 記憶の復元
        mem2.popMemory();
        return ret;
    }
}

/**
 * 新たな関数を定義する「コマンド」. 
 * <p><blockquote><pre>{@code
 * def f x_1 ... x_n:
 *  TAB comm_1
 *  TAB comm_2
 *    ...
 *  TAB comm_k
 * }</pre></blockquote><p>
 * という「ブロック」を受け付け, 
 * 仮引数 {@code x_1} から {@code x_n} を持つ関数 {@code f} を定義する. 
 * 2行目以降の「コマンド」（{@code comm_?}）を順に並べたものがこの関数の本体となる.  
 */
class Def<Result> extends BlockCommand<Result> {
    /**
     * 変数用の Memory と, コードブロックを実行するための電卓の構築に使うコマンドリストを受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     * @param comms コードブロックの実行に使うコマンドリスト
     */
    Def(Memory<Result> mem, List<Command<Result>> comms) {
        super(mem, comms);
    }
    /**
     * クラスの説明にある形の「ブロック」を受け入れる. 
     */
    boolean accept(String [] ts, final List<String> block) {
        if(ts.length >= 2 && "def".equals(ts[0])) {
            return true;
        }
        return false;
    }
    /**
     * 関数の定義を登録し, 現在の「結果」をそのまま返す. 
     */
    Result run(String [] ts, String code, Result res) {
        String fname = ts[1];
        ArrayList<String> args = new ArrayList<String>();
        for(int i = 2; i < ts.length; i++) {
            args.add(ts[i]);
        }
        Function<Result> f = new Function<Result>(args, code);
        ((MemoryWithFunc<Result>)mem).putFunc(fname, f);
        return res;
    }
}

/**
 * 整数値を「結果」とて, 関数定義も可能としたプログラム電卓を作成し動作させるクラス. 
 * 例えば, 次のような階乗計算の再帰関数が定義された「プログラム」を書いた fact.txt があったとして, 
 * <p><blockquote><pre>{@code
 * # 階乗計算の関数。
 * # print のコメントアウトを外せば動きが追えるだろう。
 * def fact n:
 * 	# print n
 * 	if n:
 * 		-1
 * 		store x
 * 		call fact x
 * 		*n
 * 		# store y
 * 		# print y
 * 		return
 * 	1
 * 
 * # fact 5 の計算
 * 5
 * store x
 * call fact x
 * 
 * # 結果の出力
 * store y
 * print y
 * }</pre></blockquote><p>
 * これをコマンドライン引数で渡して実行すると, 次のようになる. 
 * <p><blockquote><pre>{@code
 * $ javac Calculator.java IntCalc.java MemoCalc.java ProgramCalc.java FunctionCalc.java
 * Note: FunctionCalc.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ java FunctionCalc fact.txt
 * 120
 * $
 * }</pre></blockquote><p>
 * 出力された 120 は 5 の階乗である. 
 * なお, Generics が例外に使えないため, コンパイラが多少の Note を吐いているが, 
 * 問題ない（今回の設計では仕方ないことなので放置）. 
 */
class FunctionCalc {
    /**
     * コマンドライン引数があれば, それをファイル名としてファイルを開き, そこから入力を取る. 
     * そうでなければ標準入力から読み込む. 
     */
    public static void main(String [] args) throws Exception {
        // 関数も記憶するので MemoryWithFunc を作成
        MemoryWithFunc<BigInteger> mem = new MemoryWithFunc<BigInteger>();
        // コマンドリスト
        ArrayList<Command<BigInteger>> comms = new ArrayList<Command<BigInteger>>();
        comms.add(new EmptyCommand<BigInteger>());
        comms.add(new Comment<BigInteger>());
        comms.add(new IntValue());
        comms.add(new IntNeg());
        comms.add(new Puts<BigInteger>());
        comms.add(new IntArithWithMemory(mem));
        comms.add(new LoadStore<BigInteger>(mem));
        comms.add(new IntPrint(mem));
        comms.add(new IntIf(mem, comms));
        comms.add(new IntWhile(mem, comms));
        comms.add(new Def<BigInteger>(mem, comms));
        comms.add(new Call<BigInteger>(mem, comms));
        comms.add(new Return<BigInteger>());
        comms.add(mem);
        Calculator<BigInteger> c;
        // コマンドライン引数があればファイルから読み、そうでなければ標準入力から読む
        if(args.length > 0) {
            BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
            c = new CalculatorNI<BigInteger>(br, comms);
        } else { // interactive mode
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            c = new Calculator<BigInteger>(br, comms);
        }
        c.run(BigInteger.ZERO);
    }
}
