
class StringMethods {
    // 文字列 s の何文字目に p が現れるか
    // 例えば、s の先頭が p なら1文字目なので 1 が結果
    // 現れないなら -1
    int find(String s, String p) {
        return -1;
    }
    // s の中に p がいくつ現れるか（重複OK）
    // 例えば、"HoHoHo Ho" に "HoHo" は重複ありで 2度
    int count(String s, String p) {
        return 0;
    }
    // s の中に p がいくつ現れるか（重複なし）
    // 例えば、"HoHoHo Ho" に "HoHo" は重複無しで 1度
    int countNOV(String s, String p) {
        return 0;
    }
    // s の中の、文字 b と文字 e の間の部分文字列を返す
    // b は s 中の最左、e は b 以降の最左をとる
    // 対象となる b や e が無いなら空文字列を返す
    String between(String s, char b, char e) {
        return "";
    }
    // 配列 ss 内の全文字列を空白 ' ' でつないだ文字列を返す
    String concat(String [] ss) {
        return "";
    }
    // 与えられた文字列をひっくり返した文字列を返す
    String reverse(String s) {
        return "";
    }
    public static void main(String [] args) {
        // テスト用のコードはこの main に書いて
        // java StringMethods とすれば実行できる
        // 適宜自分でテストを書いて自分のコードの動作確認をすること

        StringMethods sm = new StringMethods();
        
        System.out.println(sm.find("Hello World", "or"));
        System.out.println(sm.count("HoHoHo Ho", "HoHo"));
        System.out.println(sm.countNOV("HoHoHo Ho", "HoHo"));
        System.out.println(sm.between("Hello World", 'e', 'o'));
        System.out.println(sm.concat(new String [] { "Hello", "World" }));
        System.out.println(sm.reverse("Hello World"));
        
    }
}

