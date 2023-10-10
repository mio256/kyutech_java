
class StringMethods {
    // 文字列 s の何文字目に p が現れるか
    // 例えば、s の先頭が p なら1文字目なので 1 が結果
    // 現れないなら -1
    int find(String s, String p) {
        if (s.indexOf(p) == -1) {
            return -1;
        } else {
            return s.indexOf(p) + 1;
        }
    }

    // s の中に p がいくつ現れるか（重複OK）
    // 例えば、"HoHoHo Ho" に "HoHo" は重複ありで 2度
    int count(String s, String p) {
        int count = 0;
        int index = s.indexOf(p);
        while (index != -1) {
            count++;
            index = s.indexOf(p, index + 1);
        }
        return count;
    }

    // s の中に p がいくつ現れるか（重複なし）
    // 例えば、"HoHoHo Ho" に "HoHo" は重複無しで 1度
    int countNOV(String s, String p) {
        int count = 0;
        int index = s.indexOf(p);
        while (index != -1) {
            count++;
            index = s.indexOf(p, index + p.length());
        }
        return count;
    }

    // s の中の、文字 b と文字 e の間の部分文字列を返す
    // b は s 中の最左、e は b 以降の最左をとる
    // 対象となる b や e が無いなら空文字列を返す
    String between(String s, char b, char e) {
        int indexB = s.indexOf(b);
        if (indexB == -1) {
            return "";
        }
        int indexE = s.indexOf(e, indexB);
        if (indexE == -1) {
            return "";
        }
        return s.substring(indexB, indexE + 1);
    }

    // 配列 ss 内の全文字列を空白 ' ' でつないだ文字列を返す
    String concat(String[] ss) {
        String result = new String();
        for (String s : ss) {
            result += s;
            if (s != ss[ss.length - 1]) {
                result += " ";
            }
        }
        return result;
    }

    // 与えられた文字列をひっくり返した文字列を返す
    String reverse(String s) {
        String result = new String();
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        // テスト用のコードはこの main に書いて
        // java StringMethods とすれば実行できる
        // 適宜自分でテストを書いて自分のコードの動作確認をすること

        StringMethods sm = new StringMethods();

        System.out.println(sm.find("Hello World", "or"));
        System.out.println(sm.count("HoHoHo Ho", "HoHo"));
        System.out.println(sm.countNOV("HoHoHo Ho", "HoHo"));
        System.out.println(sm.between("Hello World", 'e', 'o'));
        System.out.println(sm.concat(new String[] { "Hello", "World" }));
        System.out.println(sm.reverse("Hello World"));

    }
}
