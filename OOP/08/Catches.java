class Catches {
    public static int ex(int n) {
        try {
            int k = 10 / n;
            return (new int [] {0,1,2,3})[k];
        } catch(ArithmeticException e) {
            System.out.println("n must be non-zero");
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("n is too small!");
        } // other exceptions are thrown
        return -1;
    }
    public static void main(String [] args) {
        ex(0);
        ex(2);
    }
}
