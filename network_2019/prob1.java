/*
 * 次の行列の積に関するプログラムで使われる関数をJava言語で作成せよ。
 * 
 * このプログラムは、要素としてint型の値を持つ2つの行列の積に関する操作を行っている。
 * メソッドmatProductを作成せよ。
 * メソッドmatProductは、第一引数の行列（A）と第二引数の行列（B）の積（AB）の結果を第三引数の行列に格納する。
 * ただし、作成するメソッドはメインメソッド無いに示している行列の行と列の数や各要素の値が変わっても、正しく動作するように作成すること。
 * 実行した結果、画面表示は次のようになる。
 * 
 * ```
 * 38     32
 * 101    86
 * ```
 */

class NPPmatProduct {
    static void matProduct(int [][] a, int b[][], int c[][]){
        if(a[0].length != b.length){
            System.out.println("Check: Array size mismatch!");
            System.exit(0);
        }

        // int i,j,k,tmp;
        // for(i=0;i<a.length;i++){
        //     for(j=0;j<b[0].length;j++){
        //         tmp=0;
        //         for(k=0;k<b.length;k++){
        //             tmp+=a[i][k]*b[k][j];
        //         }
        //         c[i][j]=tmp;
        //     }
        // }
    }

    public static void main(String [] args){
        int [][] a = {{1,2,3},{4,5,6}};
        int [][] b = {{9,8},{7,6},{5,4}};
        int [][] c = new int[a.length][b[0].length];
        int i,j;

        try{
            matProduct(a, b, c);
            for(i=0;i<c.length;i++){
                for(j=0;j<c[0].length;j++){
                    System.out.print(c[i][j]+"\t");
                }
                System.out.println();
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("Check: Index out of bounds exception!");
        }
    }
}