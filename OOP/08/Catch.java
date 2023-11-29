class Catch {
    public static void main(String [] args) {
        System.out.println("start");
        try {
            System.out.println("before / by zero");
            int x = 1 / (4 - 4);
            System.out.println("after  / by zero");
        } catch(Exception e) {
            System.out.println("caught: " + e);
        }
        System.out.println("out of the try-catch");
    }
}
