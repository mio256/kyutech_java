import java.io.*;

class Kimatsu5 {

    public static void main(String[] args) {
        String line = new String(); // lineに何が入っているのかわからず、初期化エラーが起きてしまうのでとりあえずStringのインスタンスを入れておく
        int i;
        String moji;
        int mojilen;
        
        // 入出力エラーが起きるかもしれないので、tryの中に処理を入れる
        try {
            // まず、入力を受け付けて、その文字列を返す処理
            System.out.println("Input line");

            InputStreamReader isr;
            isr = new InputStreamReader(System.in);
            BufferedReader br;
            br = new BufferedReader(isr);

            line = br.readLine();

            System.out.println("Input line=" + line);
        } catch (IOException e) {
            System.out.println("IO Exception!");
            System.exit(1);
        }

        // 用意されている文字列から入力された文字列を探す
        moji = "There was a young lady named Bright. Whose speed was much faster than light; she set out, one day in a relativeway, and returned home the previous night.";
        mojilen = moji.length(); // 文字列の長さを返すメソッド
        for (i = 0; i < mojilen - 1; i++) {
            if (moji.charAt(i) == line.charAt(0)) {
                if (moji.charAt(i + 1) == line.charAt(1)) {
                    System.out.println("found at:" + i);
                }
            }
        }
    }
}