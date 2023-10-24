import java.io.*;

// CapitalizePrintStream を PrintStream を継承してここに定義
class CapitalizePrintStream extends PrintStream {
    CapitalizePrintStream(OutputStream os) {
        super(os);
    }

    public void println(String s) {
        super.println(s.toUpperCase());
    }

    public void println() { //
        println("");
    }
}

class CapitalizePrintStreamCheck {
    // 動作確認用関数：PrintStream を受け取って出力する
    static void test(PrintStream ps, String msg) {
        ps.println(msg);
    }

    // 動作確認の main 関数
    public static void main(String[] args) {
        CapitalizePrintStream cps = new CapitalizePrintStream(System.out);
        test(System.out, "This is MyPrintStream."); // そのまま
        test(cps, "This is MyPrintStream."); // 大文字化
    }
}
