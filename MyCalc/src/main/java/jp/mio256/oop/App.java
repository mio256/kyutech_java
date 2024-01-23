package jp.mio256.oop;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println(
                String.format("OPENAI_KEY : %s.", System.getenv("OPENAI_KEY")));
        System.out.println(
                String.format("DEEPL_KEY : %s.", System.getenv("DEEPL_KEY")));
        System.out.println("Hello World!");
    }
}
