import java.io.*;

// 既存クラスの継承例
// PrintStream の機能拡張
class DebugPrintStream extends PrintStream {
    private boolean debug;

    void setDebug() { // 出力の有効化
        debug = true;
    }

    void unsetDebug() { // 出力の無効化
        debug = false;
    }

    public void println(String s) { // 出力が有効なら、[debug] をつけて出力
        if (debug) {
            super.println("[debug] " + s);
        }
    }

    public void println() { //
        println("");
    }
    // 注）他の int などを取るメソッドを使われると、普通に出力されてしまう

    // OutputStream を受け取るコンストラクタ。
    DebugPrintStream(OutputStream os) {
        super(os); // 親コンストラクタを呼び、
        debug = false; // 出力を無効にしておく
    }
}

class DebugPrintStreamCheck {
    // PrintStream を受け取り、そこに文字列を出力する
    public static void test(PrintStream ps, String msg) {
        ps.println("message = " + msg);
    }

    // 動作確認の main
    public static void main(String[] args) {
        // System.out は PrintStream なので、test メソッドに渡せる
        test(System.out, "This is System.out.");
        DebugPrintStream dps = new DebugPrintStream(System.out);
        // DebugPrintStream も PrintStream として test メソッドに渡せる
        // しかし、デフォルトでは println は何もしないので、何も出力されない。
        test(dps, "This is MyPrintStream.");
        // setDebug ののち、dps の println は出力可能となる
        dps.setDebug();
        test(dps, "This is MyPrintStream after setDebug().");
    }
}
