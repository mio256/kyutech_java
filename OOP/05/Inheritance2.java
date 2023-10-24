/*
 * クラスの「継承」を簡単にまとめたもの2
 */

// 親クラス
class Parent2 {
    private String name;
    static int nextId = 0; // static -> クラスで共通の変数
    int id;

    Parent2(String name) {
        System.out.println("[[Parant2 Constructor]]");
        // 引数とフィールドで同じ名前の変数を使えるが、
        // この場合にはフィールドを表すのに this. が必要
        this.name = name;
        this.id = nextId++; // id がインスタンス毎に異なる
    }

    String getName() {
        return "<" + name + ">";
    }

    String getIdString() {
        return "P-" + id;
    }
}

// 子クラス
class Child2 extends Parent2 {
    Child2(String name) {
        // 引数のある親クラスのコンストラクタは、
        // 子クラスのコンストラクタの最初で明示的に呼ぶ
        // （省略すると、引数なしの親コンストラクタが自動で呼ばれる）
        super(name);
        System.out.println("[[Child2 Constructor]]");
    }

    // オーバーライド
    String getName() {
        // 親クラスのメソッドは、super. を付けて呼び出すことができる
        // (super. を付けないと、今定義しているメソッドを呼び出すので無限ループ)
        return "[" + super.getName() + "]";
    }

    // オーバーライド
    String getIdString() {
        // 親クラスのフィールドも、private でなければアクセス可能
        return "C-" + id;
    }
}

// 別の子クラス
class Child2A extends Parent2 {
    // 必ずしも親と同じ引数のコンストラクタでなくて良い
    Child2A() {
        super("Anonymous");
        System.out.println("[[Child2A Constructor]]");
    }

    // オーバーライド
    String getName() {
        return "(;;)";
    }

    // 新たなメソッドを追加しても良い
    String hello() {
        return "Hello-" + id;
    }
}

class InheritanceCheck2 {
    public static void main(String[] args) {
        System.out.println("creating Parent's instance:");
        Parent2 p = new Parent2("David");
        System.out.println("creating Child's instance:");
        Child2 c1 = new Child2("Alice");
        System.out.println("creating ChildA's instance:");
        Child2A c2 = new Child2A();

        // それぞれ、どのクラスの何が使われるのか把握しましょう
        System.out.println("name = " + p.getName());
        System.out.println("id = " + p.getIdString());

        System.out.println("name = " + c1.getName());
        System.out.println("id = " + c1.getIdString());

        System.out.println("name = " + c2.getName());
        System.out.println("id = " + c2.getIdString());
        System.out.println("hello: " + c2.hello());

        p = c2; // メソッドが増えていても当然代入できる
        System.out.println("name = " + p.getName());
        System.out.println("id = " + p.getIdString());
    }
}
