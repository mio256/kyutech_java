class A {
    public void g(String x1) { /* メソッド番号：1 */
        System.out.println("1");
    }

    public void h(int x1) { /* メソッド番号：2 */
        System.out.println("2");
    }

    public void m(int x1) { /* メソッド番号：3 */
        System.out.println("3");
    }
}

class B {
    public void h(String x1) { /* メソッド番号：4 */
        System.out.println("4");
    }
}

class C extends A {
    public void g(String x1) { /* メソッド番号：5 */
        System.out.println("5");
    }

    public void h(String x1) { /* メソッド番号：6 */
        System.out.println("6");
    }
}

class D extends B {
    public void g() { /* メソッド番号：7 */
        System.out.println("7");
    }
}

class E extends C {
    public void h(String x1) { /* メソッド番号：8 */
        System.out.println("8");
    }

    public void h(int x1) { /* メソッド番号：9 */
        System.out.println("9");
    }

    public void m() { /* メソッド番号：10 */
        System.out.println("10");
    }
}

class F extends D {
    public void g() { /* メソッド番号：11 */
        System.out.println("11");
    }

    public void h(String x1) { /* メソッド番号：12 */
        System.out.println("12");
    }

    public void h(int x1) { /* メソッド番号：13 */
        System.out.println("13");
    }
}

class G extends D {
    public void g(String x1) { /* メソッド番号：14 */
        System.out.println("14");
    }

    public void h(String x1) { /* メソッド番号：15 */
        System.out.println("15");
    }

    public void h(int x1) { /* メソッド番号：16 */
        System.out.println("16");
    }
}

class H extends D {
    public void g() { /* メソッド番号：17 */
        System.out.println("17");
    }

    public void g(String x1) { /* メソッド番号：18 */
        System.out.println("18");
    }

    public void h(String x1) { /* メソッド番号：19 */
        System.out.println("19");
    }

    public void m() { /* メソッド番号：20 */
        System.out.println("20");
    }
}

public class tmp {
    public static void main(String[] args) {
        // B b1 = new D();
        // b1.g("");
        System.out.println("0");
        B b2 = new D();
        b2.h("");
        B b3 = new G();
        b3.h("");
        D d1 = new D();
        d1.h("");
        // D d2 = new F();
        // d2.m(1);
        System.out.println("0");
        E e1 = new E();
        e1.g("");
        F f1 = new F();
        f1.h("");
        // G g1 = new B();
        // g1.g("");
        System.out.println("0");
        G g2 = new G();
        g2.g();
        // H h1 = new F();
        // h1.h("");
        System.out.println("0");
    }
}
