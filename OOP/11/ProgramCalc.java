/*
 * if と while をいれたプログラム(?)電卓.
 * ファイルからコードを読み込む形での動作もする. 
 * 文字列出力や変数の値の出力などの「コマンド」も持つ. 
 * コンパイル & 実行：
 *
 *   javac Calculator.java IntCalc.java MemoCalc.java ProgramCalc.java
 *   java ProgramCalc while.txt
 *   java ProgramCalc if.txt
 *
 * どの様なコードであるかは while.txt と if.txt を参照
 */

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Non-interactive mode (プロンプトと「結果」の表示なし) な Calculator. 
 * ファイルからコードを読み込んで動作させるときや while などでループする時にはプロンプトなどが邪魔なので. 
 * @param Result 「結果」の型
 */
class CalculatorNI<Result> extends Calculator<Result> {
    /**
     * 与えられた {@code BufferedReader} から入力を読み込み, 
     * 与えられた「コマンド」のリストにある演算を実行する電卓を作るコンストラクタ. 
     * @param br ここから入力を行単位で読み込む. 
     * @param comms このリストにある「コマンド」を電卓の演算とする. 
     */
    CalculatorNI(BufferedReader br, List<Command<Result>> comms) {
        super(br, comms);
    }
    /**
     * 現在の「結果」を出力しないように改変. 
     */
    void showCurrentResult(Result res) {
    }
    /**
     * プロンプトの表示をしないように改変. 
     */
    void showPrompt(String str) {
    }
    /**
     * 「ブロック」内の1行を読むが, 最終行の空行を自動挿入する. 
     * ファイルにコードを書く際には, 「ブロック」の終端に空行を置くのは邪魔（読みにくい）. 
     * よって, 「ブロック」内の最後の行を読んだ際に自動的に空行を挿入してやる. 
     */
    String readNextBlockLine() throws IOException {
        br.mark(1);
        int c = br.read();
        br.reset();
        // 次の行がタブ始まりなら、そのまま返す
        if(c == '\t') return br.readLine();
        // そうでない場合、「ブロック」終端用に空文字列を挿入
        // （コード中に空文字列を書きたくないので）
        return "";
    }
}

/**
 * while や if 等の, コードブロックを実行する系の「コマンド」のベースクラス. 
 * 条件判定に変数を使うなどするので CommandWithMemory を継承. 
 * @param Result 「結果」の型. 
 */
abstract class BlockCommand<Result> extends CommandWithMemory<Result> {
    /**
     * コードブロックを実行するための電卓に持たせるコマンドリスト. 
     */
    List<Command<Result>> comms;
    /**
     * 変数用の Memory と, コードブロックを実行するための電卓の構築に使うコマンドリストを受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     * @param comms コードブロックの実行に使うコマンドリスト
     */
    BlockCommand(Memory<Result> mem, List<Command<Result>> comms) {
        super(mem);
        this.comms = comms;
    }
    /**
     * サブクラスの {@code accept} メソッドに「ブロック」の情報を与え, 
     * それが {@code true} を返した場合にはサブクラスの {@code run} メソッドを実行して「結果」を作る. 
     */
    public Result tryExec(final String [] ts, final List<String> block, final Result res) {
        // サブクラスに実行の判断を委ねる
        if(!accept(ts, block)) return null;
        // コードブロックをひとつの文字列に直してから, run を実行する
        String code = String.join("\n", block.subList(1, block.size()));
        return run(ts, code, res);
    }
    /**
     * {@code code} を電卓で1回実行する. 
     * @param res 電卓の初期「結果」
     * @param code 実行すべきコードを表す文字列
     * @return コードを実行し終えたあとの「結果」
     */
    Result runOnce(Result res, String code) {
        // StringReader を用いて、文字列から一行ずつ読み込む BufferedReader を構築
        BufferedReader br = new BufferedReader(new StringReader(code));
        // それを電卓に渡すことで、文字列で表されたコードを電卓で実行する
        CalculatorNI<Result> calc = new CalculatorNI<Result>(br, comms);
        res = calc.run(res);
        return res;
    }
    /**
     * 与えら得た「ブロック」を実行するかどうかの判定. 
     * @param ts 「ブロック」の1行目をトークンに分解した配列
     * @param block 「ブロック」
     * @return 与えられた「ブロック」が実行可能なら {@code true}. そうでなければ {@code false}
     */
    abstract boolean accept(String [] ts, final List<String> block);
    /**
     * コードブロックの実行. 
     * @param ts 「ブロック」の1行目をトークンに分解した配列
     * @param code コードブロック（「ブロック」の2行目以降）の文字列
     * @param res コードブロックを実行する際の初期「結果」
     * @return コードブロックの実行後の「結果」
     */
    abstract Result run(String [] ts, String code, Result res);
}

/**
 * 指定された変数が 0 以外のときにコードブロック実行する「コマンド」. 
 * <p><blockquote><pre>{@code
 * if var:
 *  TAB comm_1
 *  TAB comm_2
 *    ...
 *  TAB comm_k
 * }</pre></blockquote><p>
 * という「ブロック」を受け付け, 
 * 変数 var が 0 以外なら「ブロック」の2行目以降の「コマンド」（{@code comm_?}）を順に実行. 
 */
class IntIf extends BlockCommand<BigInteger> {
    /**
     * 変数用の Memory と, コードブロックを実行するための電卓の構築に使うコマンドリストを受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     * @param comms コードブロックの実行に使うコマンドリスト
     */
    IntIf(Memory<BigInteger> mem, List<Command<BigInteger>> comms) {
        super(mem, comms);
    }
    /**
     * クラスの説明にある形の「ブロック」を受け付ける. 
     */
    boolean accept(String [] ts, final List<String> block) {
        if(ts.length == 2 && "if".equals(ts[0])) {
            BigInteger v = mem.get(ts[1]);
            if(v == null) throw new UnknownVariableException(ts[1]);
            return true;
        }
        return false;
    }
    /**
     * 「ブロック」の最初の行で指定された変数の値を参照し, 
     * それが 0 以外ならコードブロックを実行したあとの「結果」を返す. 
     * 変数の値が 0 なら与えられた「結果」をそのまま返す. 
     */
    BigInteger run(String [] ts, String code, BigInteger res) {
        BigInteger v = mem.get(ts[1]);
        if(v.equals(BigInteger.ZERO)) return res;
        return runOnce(res, code);
    }
}

/**
 * 指定された変数が 0 でない間, コードブロックの実行を繰り返す「コマンド」. 
 * <p><blockquote><pre>{@code
 * while var:
 *  TAB comm_1
 *  TAB comm_2
 *    ...
 *  TAB comm_k
 * }</pre></blockquote><p>
 * という「ブロック」を受け付け, 
 * 変数 var が 0 以外の間「ブロック」の2行目以降の「コマンド」（{@code comm_?}）を順に実行することを繰り返す. 
 */
class IntWhile extends BlockCommand<BigInteger> {
    /**
     * 変数用の Memory と, コードブロックを実行するための電卓の構築に使うコマンドリストを受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     * @param comms コードブロックの実行に使うコマンドリスト
     */
    IntWhile(Memory<BigInteger> mem, List<Command<BigInteger>> comms) {
        super(mem, comms);
    }
    /**
     * クラスの説明にある形の「ブロック」を受け付ける. 
     */
    boolean accept(String [] ts, final List<String> block) {
        if(ts.length == 2 && "while".equals(ts[0])) {
            BigInteger v = mem.get(ts[1]);
            if(v == null) throw new UnknownVariableException(ts[1]);
            return true;
        }
        return false;
    }
    /**
     * 「ブロック」の最初の行で指定された変数の値を参照し, それが 0 以外である限りコードブロックの実行を繰り返す. 
     * 変数の値が 0 になったら, そのときの「結果」を返す. 
     */
    BigInteger run(String [] ts, String code, BigInteger res) {
        for(;;) {
            BigInteger v = mem.get(ts[1]);
            if(v.equals(BigInteger.ZERO)) break;
            res = runOnce(res, code);
        }
        return res;
    }
}

/**
 * 指定された変数の値を標準出力に表示する「コマンド」. 
 * <p><blockquote><pre>{@code
 * print var
 * }</pre></blockquote><p>
 * という1行「ブロック」を受け付け, 変数 var の値を標準出力に出力する. 
 */
class IntPrint extends CommandWithMemory<BigInteger> {
    /**
     * 変数参照用の Memory を受け取る. 
     * @param mem 変数の値を記憶するオブジェクト
     */
    IntPrint(Memory<BigInteger> mem) {
        super(mem);
    }
    /**
     * 指定の形の「ブロック」が与えら得たとき, 変数の値を出力する. 
     * 変数が見つからない場合には, {@code UnknownVariableException} 例外を発生する. 
     */
    public BigInteger tryExec(final String [] ts, final List<String> block, final BigInteger res) {
        if(block.size() != 1) return null;
        if(ts.length != 2) return null;
        if("print".equals(ts[0])) {
            BigInteger v = mem.get(ts[1]);
            if(v == null) throw new UnknownVariableException(ts[1]);
            System.out.println(v);
            return res;
        }
        return null;
    }
}

/**
 * 与えられた文字列を標準出力に表示する「コマンド」. 
 * <p><blockquote><pre>{@code
 * puts "出力したい文字列"
 * }</pre></blockquote><p>
 * という1行「ブロック」を受け付け, 与えられた文字列を標準出力に出力する. 
 */
class Puts<Result> implements Command<Result> {
    /**
     * 与えら得た文字列を標準出力に出力する. 
     * 実際には, {@code puts} の後ろに書かれた最初の {@code "} から最後の {@code "} までの間の文字列を
     * 標準出力に出力する. この際, {@code \n} は改行に置き換える. 
     */
    public Result tryExec(final String [] ts, final List<String> block, final Result res) {
        if(block.size() != 1) return null;
        String line = block.get(0);
        StringTokenizer st = new StringTokenizer(line);
        if(st.hasMoreTokens()) {
            String token = st.nextToken();
            if("puts".equals(token)) {
                int x = line.indexOf("puts");
                int s = line.indexOf('"', x);
                int e = line.lastIndexOf('"');
                if(s + 1 <= e) {
                    String str = line.substring(s+1, e);
                    str = str.replaceAll("\\\\n", "\n");
                    System.out.print(str);
                }
                return res;
            }
            return null;
        }
        return null;
    }
}

/**
 * {@code #} から始まる1行「ブロック」をコメントとして無視する「コマンド」. 
 */
class Comment<Result> implements Command<Result> {
    /**
     * 冒頭の空白を除いて {@code #} から始まる1行「ブロック」を受け付け, 現在の「結果」をそのまま返す. 
     */
    public Result tryExec(final String [] ts, final List<String> block, final Result res) {
        if(block.size() != 1) return null;
        String line = block.get(0);
        StringTokenizer st = new StringTokenizer(line);
        if(st.hasMoreTokens()) {
            String token = st.nextToken();
            if(token.charAt(0) == '#') {
                return res;
            }
        }
        return null;
    }
}

/**
 * 整数値を「結果」とて, ループや条件分岐が可能なプログラム電卓を作成し動作させるクラス. 
 * 例えば, 次のような「プログラム」を書いた if.txt があったとして, 
 * <p><blockquote><pre>{@code
 * # x = 10, z = 0 とする
 * 10
 * store x
 * 0
 * store z
 * 
 * # x != 0 なので、本体実行
 * if x:
 *     puts "this line is executed\n"
 * 
 * # z == 0 なので、本体は実行されない
 * if z:
 *     puts "this line is not executed\n"
 * }</pre></blockquote><p>
 * これをコマンドライン引数で渡して実行すると, 次のようになる. 
 * <p><blockquote><pre>{@code
 * $ javac Calculator.java IntCalc.java MemoCalc.java ProgramCalc.java
 * $ java ProgramCalc if.txt
 * this line is executed
 * $
 * }</pre></blockquote><p>
 * これは, 最初の if は条件が成立するので内側の puts が実行されて文字列が表示されるが, 
 * 次の if は条件が不成立となっている（指定された変数 z が 0 以外ではない）ので2つ目の
 * puts が実行されなかったという結果である.  <br />
 * 同様に, 
 * <p><blockquote><pre>{@code
 * # y = 0, x = 10 にする
 * store y
 * 10
 * store x
 * 
 * # x が 0 になるまで繰り返す
 * while x:
 * 	# ブロック内はコメントもタブが必要
 * 	# x = x - 1
 * 	load x
 * 	- 1
 * 	store x
 * 	# y = y + 20
 * 	load y
 * 	+ 20
 * 	store y
 * 	# 現在の y と x を表示。
 * 	# puts で文字列を、print で変数を表示。
 * 	puts "y="
 * 	print y
 * 	puts "x="
 * 	print x
 * }</pre></blockquote><p>
 * という内容の while.txt を実行すると次のようになる. 
 * <p><blockquote><pre>{@code
 * $ java ProgramCalc while.txt
 * y=20
 * x=9
 * y=40
 * x=8
 * y=60
 * x=7
 * y=80
 * x=6
 * y=100
 * x=5
 * y=120
 * x=4
 * y=140
 * x=3
 * y=160
 * x=2
 * y=180
 * x=1
 * y=200
 * x=0
 * $
 * }</pre></blockquote><p>
 * これは, 変数 x が 0 になるまで while の本体部分の実行が合計10回繰り返された結果である. 
 */
class ProgramCalc {
    /**
     * コマンドライン引数があれば, それをファイル名としてファイルを開き, そこから入力を取る. 
     * そうでなければ標準入力から読み込む. 
     */
    public static void main(String [] args) throws Exception {
        // コマンドリストの作成
        Memory<BigInteger> mem = new Memory<BigInteger>();
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
        comms.add(mem);
        Calculator<BigInteger> c;
        // コマンドライン引数があればファイルから、そうでなければ標準入力から入力を取る
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
