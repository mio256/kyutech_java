class ArrayOfStrings {
    static String str;
    public static void main(String [] args) {
        // （グローバル変数の場合、）未初期化は null
        if(str == null) System.out.println("str is null");
        // 文字列（String）の配列を用意
        String [] strs = new String[4];
        // 配列の要素はまだ null
        for(int i = 0; i < strs.length; i++) {
            if(strs[i] == null) {
                System.out.println("strs[" + i + "] is null");
            }
        }
        // 各要素として String オブジェクトを代入
        for(int i = 0; i < strs.length; i++) {
            strs[i] = "No. " + i;
        }
        // 表示してみる
        System.out.println("array elements:");
        for(int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        // 具体的な文字列（String）の配列を作る
        String [] strs2 = new String[]{ "No. 4", "No. 5", "No. 6" };
        // 略記を使う
        System.out.println("using for( : ) notation:");
        for(String s : strs2) {
            System.out.println(s);
        }
    }
}
