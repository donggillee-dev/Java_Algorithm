import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {

    }
}

class Phone {
    public String name;
    public char color;
    public int price;

    public int getReadDebt() {
        return 1000;
    }

    public Phone(String name) {
        this.name = name;
        System.out.println("A");
        System.out.println(name);
    }

    public Phone() {
        System.out.println("B");
        System.out.println(name);
    }
}