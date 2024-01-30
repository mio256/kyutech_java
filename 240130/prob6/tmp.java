interface A {
    void m();

    void m(String x1);
}

interface B {
    void g(String x1);
}

class C implements B {
    public void g(String x1) {
        System.out.println("1");
    }

    public void m(int x1) {
        System.out.println("2");
    }
}

class D {
    public void m() {
        System.out.println("3");
    }
}

class E extends C {
    public void m() {
        System.out.println("4");
    }
}

class F extends E {
    public void g(String x1) {
        System.out.println("5");
    }

    public void m() {
        System.out.println("6");
    }
}

class G extends F implements A {
    public void m() {
        System.out.println("7");

    }

    public void m(String x1) {
        System.out.println("8");

    }
}

class H extends F implements A {
    public void g(String x1) {
        System.out.println("9");

    }

    public void m() {
        System.out.println("10");

    }

    public void m(String x1) {
        System.out.println("11");

    }

    public void m(int x1) {
        System.out.println("12");

    }
}

class I extends G {
    public void g(int x1) {
        System.out.println("13");

    }
}

class J extends H {
    public void g(String x1) {
        System.out.println("14");

    }
}

class K extends J {
    public void g(int x1) {
        System.out.println("15");

    }
}

class L extends K {
    public void g(String x1) {
        System.out.println("16");

    }

    public void m() {
        System.out.println("17");

    }

    public void m(int x1) {
        System.out.println("18");

    }
}

class M extends L {
    public void m() {
        System.out.println("19");
    }

    public void m(String x1) {
        System.out.println("20");
    }
}

public class tmp {
    public static void main(String[] args) {
        E e1 = new H();
        e1.m();
        // E e2 = new K();
        // e2.m("");
        System.out.println("0");
        F f1 = new G();
        f1.g("");
        // I i1 = new D();
        // i1.m(1);
        System.out.println("0");
        // I i2 = new F();
        // i2.g("");
        System.out.println("0");
        I i3 = new I();
        i3.g(1);
        I i4 = new I();
        i4.m("");
        // L l1 = new I();
        // l1.m();
        System.out.println("0");
        L l2 = new L();
        l2.m("");
        M m1 = new M();
        m1.g("");
    }
}
