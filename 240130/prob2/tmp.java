class A {
    public void g(String x1) { /* メソッド番号：1 */
        System.err.println("1");
    }

    public void h(String x1) { /* メソッド番号：2 */
        System.err.println("2");
    }

    public void m() { /* メソッド番号：3 */
        System.err.println("3");
    }
}

class B extends A {
    public void g(String x1) { /* メソッド番号：4 */
        System.err.println("4");
    }

    public void h(String x1) { /* メソッド番号：5 */
        System.err.println("5");
    }

    public void h(int x1) { /* メソッド番号：6 */
        System.err.println("6");
    }

    public void m() { /* メソッド番号：7 */
        System.err.println("7");
    }

    public void m(int x1) { /* メソッド番号：8 */
        System.err.println("8");
    }
}

class C extends B {
    public void g() { /* メソッド番号：9 */
        System.err.println("9");
    }

    public void m() { /* メソッド番号：10 */
        System.err.println("10");
    }
}

class D extends C {
    public void h(String x1) { /* メソッド番号：11 */
        System.err.println("11");
    }

    public void h(int x1) { /* メソッド番号：12 */
        System.err.println("12");
    }
}

class E extends D {
    public void g() { /* メソッド番号：13 */
        System.err.println("13");
    }

    public void g(String x1) { /* メソッド番号：14 */
        System.err.println("14");
    }

    public void h(int x1) { /* メソッド番号：15 */
        System.err.println("15");
    }

    public void m() { /* メソッド番号：16 */
        System.err.println("16");
    }
}

class F extends D {
    public void g() { /* メソッド番号：17 */
        System.err.println("17");
    }

    public void h(int x1) { /* メソッド番号：18 */
        System.err.println("18");
    }

    public void m() { /* メソッド番号：19 */
        System.err.println("19");
    }
}

class G extends D {
    public void m() { /* メソッド番号：20 */
        System.err.println("20");
    }

    public void m(int x1) { /* メソッド番号：21 */
        System.err.println("21");
    }
}

class H extends F {
    public void h(String x1) { /* メソッド番号：22 */
        System.err.println("22");
    }
}

public class tmp {
    public static void main(String[] args) {
        // A a1 = new A();
        // a1.g();
        System.err.println("0");
        A a2 = new G();
        a2.h("");
        // A a3 = new H();
        // a3.m(1);
        System.err.println("0");
        // E e1 = new G();
        // e1.h("");
        System.err.println("0");
        F f1 = new H();
        f1.g("");
        G g1 = new G();
        g1.g("");
        G g2 = new G();
        g2.h("");
        // H h1 = new B();
        // h1.g();
        System.err.println("0");
        H h2 = new H();
        h2.g();
        H h3 = new H();
        h3.h("");
    }
}
