class A {
    public void g() { /* メソッド番号：1 */
        System.err.println("1");
    }

    public void g(String x1) { /* メソッド番号：2 */
        System.err.println("2");
    }

    public void h(String x1) { /* メソッド番号：3 */
        System.err.println("3");
    }
}

class B {
    public void h(String x1) { /* メソッド番号：4 */
        System.err.println("4");
    }

    public void m(int x1) { /* メソッド番号：5 */
        System.err.println("5");
    }
}

class C extends A {
    public void m(int x1) { /* メソッド番号：6 */
        System.err.println("6");
    }
}

class D extends A {
    public void h(int x1) { /* メソッド番号：7 */
        System.err.println("7");
    }
}

class E extends A {
    public void h(int x1) { /* メソッド番号：8 */
        System.err.println("8");
    }

    public void m(int x1) { /* メソッド番号：9 */
        System.err.println("9");
    }
}

class F extends E {
    public void g() { /* メソッド番号：10 */
        System.err.println("10");
    }

    public void h(String x1) { /* メソッド番号：11 */
        System.err.println("11");
    }

    public void h(int x1) { /* メソッド番号：12 */
        System.err.println("12");
    }

    public void m() { /* メソッド番号：13 */
        System.err.println("13");
    }
}

class G extends E {
    public void g() { /* メソッド番号：14 */
        System.err.println("14");
    }

    public void g(String x1) { /* メソッド番号：15 */
        System.err.println("15");
    }

    public void m(int x1) { /* メソッド番号：16 */
        System.err.println("16");
    }
}

class H extends F {
    public void g() { /* メソッド番号：17 */
        System.err.println("17");
    }

    public void g(String x1) { /* メソッド番号：18 */
        System.err.println("18");
    }

    public void m(int x1) { /* メソッド番号：19 */
        System.err.println("19");
    }
}

public class tmp1 {
    public static void main(String[] args) {
        A a1 = new A();
        a1.h("");
        A a2 = new C();
        System.err.println("0");
        A a3 = new D();
        a3.h("");
        C c1 = new C();
        c1.g();
        // C c2 = new H();
        // c2.m(1);
        System.err.println("0");
        E e1 = new E();
        e1.h("");
        // E e2 = new G();
        // e2.m();
        System.err.println("0");
        E e3 = new H();
        e3.m(1);
        // H h1 = new B();
        // h1.m();
        System.err.println("0");
        H h2 = new H();
        h2.g("");
    }
}
