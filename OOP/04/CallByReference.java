class CallByReference {
    // オブジェクト（配列）を受け取る -> 参照渡し
    static void swap(int [] xy) {
        // ここでの入れ替えは、渡された参照の先の配列を書き換えるので、呼び出し元にも影響する
        int t = xy[0];
        xy[0] = xy[1];
        xy[1] = t;
    }
    // 基本型を受け取る -> 値渡し
    static void inc(int x) {
        // この x は、値をコピーしたものなので、書き換えても呼び出し元には影響しない
        x = x + 1;
    }
    public static void main(String [] args) {
        int [] xy = new int[] {1, 5};
        System.out.println("xy = {" + xy[0] + ", " + xy[1] + "}");
        // 参照渡し -> 配列オブジェクトが書き換えられた結果はこちらにも影響する
        swap(xy);
        System.out.println("xy = {" + xy[0] + ", " + xy[1] + "}");
        // 値渡し -> int のコピーが作られ、そのコピーは書き換わるが、こちらの元の値は変わらない
        inc(xy[0]);
        System.out.println("xy = {" + xy[0] + ", " + xy[1] + "}");
    }
}
