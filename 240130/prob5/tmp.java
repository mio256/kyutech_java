interface A {
    void m();

    void m(String x1);
}

interface B {
    void g(String x1);
}

class C {
    public void g(int x1, int x2) { /* メソッド番号：1 */
        System.out.println("1");
    }
}

class D implements A {
    public void g(int x1) { /* メソッド番号：2 */
        System.out.println("2");
    }

    public void m() { /* メソッド番号：3 */
        System.out.println("3");
    }

    public void m(String x1) { /* メソッド番号：4 */
        System.out.println("4");
    }
}

class E extends C implements B {
    public void g() { /* メソッド番号：5 */
        System.out.println("5");
    }

    public void g(String x1) { /* メソッド番号：6 */
        System.out.println("6");
    }

    public void g(int x1) { /* メソッド番号：7 */
        System.out.println("7");
    }
}

class F extends E {
    public void m(String x1) { /* メソッド番号：8 */
        System.out.println("8");
    }

    public void m(int x1) { /* メソッド番号：9 */
        System.out.println("9");
    }
}

class G extends E {
    public void g(int x1) { /* メソッド番号：10 */
        System.out.println("10");
    }

    public void m(String x1) { /* メソッド番号：11 */
        System.out.println("11");
    }

    public void m(int x1) { /* メソッド番号：12 */
        System.out.println("12");
    }
}

class H extends F {
    public void g(int x1) { /* メソッド番号：13 */
        System.out.println("13");
    }
}

class I extends F {
    public void g(int x1) { /* メソッド番号：14 */
        System.out.println("14");
    }
}

class J extends I {
    public void g(String x1) { /* メソッド番号：15 */
        System.out.println("15");
    }
}

class K extends J implements A {
    public void m() { /* メソッド番号：16 */
        System.out.println("16");
    }
}

class L extends J implements A {
    public void g() { /* メソッド番号：17 */
        System.out.println("17");
    }

    public void g(String x1) { /* メソッド番号：18 */
        System.out.println("18");
    }

    public void m() { /* メソッド番号：19 */
        System.out.println("19");
    }
}

class M extends K {
    public void g(int x1) { /* メソッド番号：20 */
        System.out.println("20");
    }

    public void m() { /* メソッド番号：21 */
        System.out.println("21");
    }

    public void m(String x1) { /* メソッド番号：22 */
        System.out.println("22");
    }
}

public class tmp {
    public static void main(String[] args) {
        // E e1 = new D();
        // e1.m("");
        System.out.println("0");
        F f1 = new H();
        f1.g(1);
        F f2 = new K();
        f2.g();
        G g1 = new G();
        g1.m("");
        H h1 = new H();
        h1.m("");
        I i1 = new I();
        i1.m("");
        // I i2 = new K();
        // i2.m();
        System.out.println("0");
        J j1 = new J();
        j1.g(1, 1);
        // K k1 = new J();
        // k1.m();
        System.out.println("0");
        // L l1 = new G();
        // l1.g(1);
        System.out.println("0");
    }
}
