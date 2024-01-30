interface A {
    void m();

    void m(String x1);
}

interface B {
    void g(String x1);
}

class C implements B {
    public void g(String x1) { /* メソッド番号：1 */
        System.out.println("1");
    }

    public void m(String x1) { /* メソッド番号：2 */
        System.out.println("2");
    }

    public void m(int x1) { /* メソッド番号：3 */
        System.out.println("3");

    }
}

class D {
    public void g() { /* メソッド番号：4 */
        System.out.println("4");
    }

    public void g(String x1) { /* メソッド番号：5 */
        System.out.println("5");
    }

}

class E extends C implements A {
    public void g(String x1) { /* メソッド番号：8 */
        System.out.println("8");
    }

    public void m() { /* メソッド番号：9 */
        System.out.println("9");

    }

    public void m(String x1) { /* メソッド番号：10 */
        System.out.println("10");

    }
}

class F extends E {
    public void g(String x1) { /* メソッド番号：11 */
        System.out.println("11");

    }

    public void g(int x1) { /* メソッド番号：12 */
        System.out.println("12");

    }

    public void m() { /* メソッド番号：13 */
        System.out.println("13");

    }

    public void m(String x1) { /* メソッド番号：14 */
        System.out.println("14");

    }
}

class G extends F {
    public void g() { /* メソッド番号：15 */
        System.out.println("15");

    }

    public void g(int x1, int x2) { /* メソッド番号：16 */
        System.out.println("16");

    }
}

class H extends F {
    public void g() { /* メソッド番号：17 */
        System.out.println("17");

    }

    public void m() { /* メソッド番号：18 */
        System.out.println("18");

    }
}

class I extends F {
    public void g(String x1) { /* メソッド番号：19 */
        System.out.println("19");

    }

    public void g(int x1) { /* メソッド番号：20 */
        System.out.println("20");

    }

    public void g(int x1, int x2) { /* メソッド番号：21 */
        System.out.println("21");
    }

    public void m(int x1) { /* メソッド番号：22 */
        System.out.println("22");
    }
}

class J extends F {
    public void m(int x1) { /* メソッド番号：23 */
        System.out.println("23");
    }
}

class K extends F {
    public void g() { /* メソッド番号：24 */
        System.out.println("24");
    }

    public void g(int x1) { /* メソッド番号：25 */
        System.out.println("25");
    }

    public void m() { /* メソッド番号：26 */
        System.out.println("26");
    }

    public void m(int x1) { /* メソッド番号：27 */
        System.out.println("27");
    }
}

class L extends G {
    public void m(String x1) { /* メソッド番号：28 */
        System.out.println("28");
    }
}

class M extends J {
    public void g(String x1) { /* メソッド番号：29 */
        System.out.println("29");
    }

    public void g(int x1) { /* メソッド番号：30 */
        System.out.println("30");
    }

    public void m(String x1) { /* メソッド番号：31 */
        System.out.println("31");
    }
}

public class tmp {
    public static void main(String[] args) {
        D d = new D();
        d.g("");
        E e1 = new J();
        e1.m("");
        E e2 = new L();
        e2.m(1);
        // F f = new G();
        // f.g();
        System.out.println("0");
        G g = new G();
        g.m();
        H h = new H();
        h.g("");
        // I i = new I();
        // i.g();
        System.out.println("0");
        // J j1 = new E();
        // j1.m();
        System.out.println("0");
        J j2 = new J();
        j2.g(1);
        // K k = new C();
        // k.g(1);
        System.out.println("0");
    }
}
