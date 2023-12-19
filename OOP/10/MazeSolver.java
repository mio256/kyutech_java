import java.util.*;

/**
 * DFSMazeSolver と BFSMazeSolver の solve の，差異を吸収するためのインターフェース．
 * 端的には，差異の数だけメソッドが必要であるということ．
 *
 * @param <E> 要素の型．
 */
interface Adapter<E> {
    /**
     * 新しい、空のインスタンスを準備するメソッド．
     */
    void init();

    void add(E e);

    E remove();

    boolean isEmpty();
}

/**
 * DFSMazeSolver と BFSMazeSolver の共通コード部分．
 * 差異は，Adapter<E> で吸収する．
 */
class MazeSolver {
    /**
     * DFSMazeSolver と BFSMazeSolver の solve を共通化したコード．
     * 差異の部分は Adapter<E> のメソッドに置き換えて，その具体的なコードは Adapter<E> を継承する各クラスに記述する．
     * なお，メソッドの最初の方で Adapter<Pos> の新しいインスタンスを生成しているため，引数に Adapter<Pos>
     * collection_instance を受け取るのは無駄に思うかもしれない．
     * しかし，Java ではある型の新しいインスタンスを作るためにはその型の別のインスタンスが手元にある必要がある（もしくはその型の Class
     * オブジェクトがほしい）．そのため，余計な引数を受け取る実装になっている．
     */
    public static <Pos> void solve(Maze<Pos> m, Adapter<Pos> collection) {
        // 「ある位置に、どの位置から来たか？」を記録する連想配列
        HashMap<Pos, Pos> prev = new HashMap<Pos, Pos>();
        // これからチェックすべき位置を貯める構造を内部に用意させる
        collection.init();

        // これからチェックすべき位置の集まり
        HashSet<Pos> seen = new HashSet<Pos>();
        Pos st = m.getStart(); // スタート位置
        collection.add(m.getStart()); // スタート位置を集まりに追加
        seen.add(m.getStart()); // スタート位置を集まりに入れたと記憶
        while (!collection.isEmpty()) { // 集まりに残りがある限り回る
            Pos p = collection.remove(); // 集まりの一番上を取り出す
            if (m.isGoal(p)) { // ゴールに辿り着いたか？
                // ゴールに着いたら、そこに至る経路を逆向きにたどる
                ArrayList<Pos> ps = new ArrayList<Pos>();
                for (Pos c = p; c != null; c = prev.get(c)) {
                    ps.add(c);
                }
                Collections.reverse(ps); // 経路を正しい向きに直す
                m.printAnswer(ps); // 答えを出力
                return;
            }
            List<Pos> ns = m.getNeighbors(p); // 隣の位置を取得
            for (Pos n : ns) { // 移動できる隣の位置 n の全てについて
                if (!seen.contains(n)) { // まだ集まりに入れてないなら
                    collection.add(n); // 集まりに入れて、そのうち探索
                    seen.add(n);
                    prev.put(n, p); // n への移動は p からであった、という記録
                }
            }
        }
        System.out.println("impossible");
    }
}

/**
 * DFSMazeSolver の差異部分を書き下したもの．
 * 差異吸収のためのインターフェース Adapter<E> を実装する．
 * つまり，各メソッドの中身は，DFSMazeSolver の solve のコードの一部である．
 */
class StackAdapter<E> implements Adapter<E> {
    Stack<E> st;

    StackAdapter() {
        st = null; // まだ準備できてない
    }

    public void init() {
        st = new Stack<E>(); // 空の Stack を準備
    }

    public void add(E e) {
        st.push(e); // Stack の一番上に追加
    }

    public E remove() {
        return st.pop(); // Stack の一番上を取り出す
    }

    public boolean isEmpty() {
        return st.isEmpty(); // Stack が空かどうかを返す
    }
}

/**
 * BFSMazeSolver の差異部分を書き下したもの．
 * 差異吸収のためのインターフェース Adapter<E> を実装する．
 * つまり，各メソッドの中身は，BFSMazeSolver の solve のコードの一部である．
 */
class QueueAdapter<E> implements Adapter<E> {
    LinkedList<E> que;

    QueueAdapter() {
        que = null; // 準備できてない状態
    }

    public void init() {
        que = new LinkedList<E>(); // 空の LinkedList を準備
    }

    public void add(E e) {
        que.add(e); // LinkedList の末尾に追加
    }

    public E remove() {
        return que.remove(); // LinkedList の先頭を取り出す
    }

    public boolean isEmpty() {
        return que.isEmpty(); // LinkedList が空かどうかを返す
    }
}
