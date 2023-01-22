/*
 * 次のような出力になる、Java言語のプログラムを完成させ、空欄を解答用紙に書きなさい。
 * 空欄と書かれている部分は特に断りがない限りセミコロン(;)を含まない。
 * クラス AirPlane TripDefault と AirPlaneTrip のフィールド start と destination の値である文字列を変更した場合、その変更のみで対応する出力文字列が変わること。
 * また、クラス Person4 のメソッド print Allnames の省略部分には、画面出力用のメソッドである System.out.print や System.out.println を含まないこと。
 * 
 * Input number 0 (one way ticket) or 1 (roud-trip ticket).
 * 0 (<-input)
 * One Way Ticket: 
 * Fukuoka -> Tokyo
 * Regular tickets are available for 1 day!
 * Passengers :Nobuta:Akira:Syuji:Sakusya:
 * Input number 0 or 1.
 * 1 (<-input)
 * Round -Trip Ticket: 
 * Fukuoka -> Tokyo
 * Tokyo -> Fukuoka
 * Round -Trip tickets are available for 1 week!
 * Passengers :Nobuta:Akira:Syuji:Sakusya:
 * 
 * Input number 0 or 1.
 * (<-input Ctrl+D)
 * Thank you.
 */

import java.io.*;

class AirPlaneTripDefault {
    String start = "Fukuoka";

    public void showPeriod() {
        System.out.println("Regular tickets are available for 1 day!");
    }
}

class AirPlaneTrip extends AirPlaneTripDefault {
    String destination="Tokyo";
}

interface DispTicket {
    void display();

    void showPeriod();
}

class OneWay extends AirPlaneTrip implements DispTicket{
    public void display() {
        System.out.println("One Way Ticket: ");
        System.out.println(start+" -> "+destination);
    }
}

class RoundTrip extends AirPlaneTrip implements DispTicket{
    public void display() {
        System.out.println("Round -Trip Ticket: ");
        System.out.println (start+" -> "+destination);
        System.out.println(destination+" -> "+start);
    }

    public void showPeriod() {
        System.out.println("Round -Trip tickets are available for 1 week!");
    }
}

class Person4 {
    private Person4 next;

    Person4(Person4 obj) {
        next = obj;
    }

    private String name = "";

    void setName(String nameIn) {
        name = nameIn;
    }

    void printAllNames() {
        System.out.print(name+":");
        if(next != null){
            next.printAllNames();
        }
    }
}

class Ex3k {
    public static void main (String args[]) throws IOException {
        Person4 head;
        Person4 kotani4;
        Person4 akira4;
        Person4 syuji4;
        Person4 sakusya4;

        sakusya4 = new Person4(null);
        sakusya4.setName("Sakusya");

        syuji4 = new Person4(sakusya4);
        syuji4.setName("Syuji");

        akira4 = new Person4(syuji4);
        akira4.setName("Akira");

        kotani4 = new Person4(akira4);
        kotani4.setName ("Nobuta");

        head = new Person4(kotani4);

        DispTicket [] menu = new DispTicket [2];
        DispTicket obj0 = new OneWay();
        DispTicket obj1 = new RoundTrip();
        menu[0] = obj0;
        menu[1] = obj1;

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input number 0 (one way ticket) or 1 (roud-trip ticket).");
        String line;
        while ((line = br.readLine()) != null) {
            int menuNum = Integer.parseInt(line);
            menu[menuNum].display();
            menu[menuNum].showPeriod();
            System.out.print ("Passengers ");
            head.printAllNames();
            System.out.println();
            System.out.println();
            System.out.println("Input number 0 or 1.");
        }
        System.out.println("Thank you.");
    }
}