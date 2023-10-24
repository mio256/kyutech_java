/*
 * クラスの「継承」を簡単にまとめたもの１
 */

// 親クラス
class Parent {
    Parent() {
        System.out.println("[[Parant Constructor]]");
    }

    String getName() {
        return "Parent";
    }

    void bar() {
        System.out.println("[[Parant bar]]");
    }

    void foo() {
        System.out.println("[[Parant foo]]");
    }
}

// 子クラス
class Child extends Parent {
    Child() {
        System.out.println("[[Child Constructor]]");
    }

    // オーバーライド
    String getName() {
        return "Child";
    }

    // オーバーライド
    void foo() {
        System.out.println("[[Child foo]]");
    }
}

// 孫クラス
class Mago extends Child {
    Mago() {
        System.out.println("[[Mago Constructor]]");
    }

    // オーバーライド
    String getName() {
        return "Mago";
    }
}

class InheritanceCheck {
    public static void main(String[] args) {
        // それぞれのコンストラクタの呼ばれ方に注意
        System.out.println("creating Parent's instance:");
        Parent p = new Parent();
        System.out.println("creating Child's instance:");
        Child c = new Child();
        System.out.println("creating Mago's instance:");
        Mago m = new Mago();

        // それぞれのクラスの変数に入った状態での実行
        // 直近にオーバーライドされた実装が使われることに注意
        System.out.println("name = " + p.getName());
        System.out.print("calling foo: ");
        p.foo();
        System.out.print("calling bar: ");
        p.bar();

        System.out.println("name = " + c.getName());
        System.out.print("calling foo: ");
        c.foo();
        System.out.print("calling bar: ");
        c.bar();

        System.out.println("name = " + m.getName());
        System.out.print("calling foo: ");
        m.foo();
        System.out.print("calling bar: ");
        m.bar();

        // 親クラス変数に、子クラスのインスタンスを入れる
        // インスタンスの持つ実装が使われることに注意
        p = c;
        System.out.println("name = " + p.getName());
        System.out.print("calling foo: ");
        p.foo();
        System.out.print("calling bar: ");
        p.bar();

        // 親クラス変数に、孫クラスのインスタンスを入れる
        // インスタンスの持つ実装が使われることに注意
        p = m;
        System.out.println("name = " + p.getName());
        System.out.print("calling foo: ");
        p.foo();
        System.out.print("calling bar: ");
        p.bar();

        // 子クラスの変数にに、孫クラスのインスタンスを入れる
        // インスタンスの持つ実装が使われることに注意
        c = m;
        System.out.println("name = " + c.getName());
        System.out.print("calling foo: ");
        c.foo();
        System.out.print("calling bar: ");
        c.bar();
    }
}
