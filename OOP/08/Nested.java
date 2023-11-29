class Nested {
    static void a() throws Exception { b(); }
    static void b() throws Exception { c(); }
    static void c() throws Exception { d(); }
    static void d() throws Exception { e(); }
    static void e() throws Exception {
        throw new Exception("at e!");
    }
    public static void main(String [] args) {
        try {
            a();
        } catch(Exception e) {
            System.out.println("caught in main");
            e.printStackTrace();
        }
    }
}
