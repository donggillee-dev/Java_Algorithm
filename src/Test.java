import java.io.*;
import java.util.*;

import java.util.Arrays;

public class Test{
    public static void main(String[] args)  {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = new int[a.length][a[0].length];

        for(int i = 0; i < a.length; i++) {
            b[i] = Arrays.copyOf(a[i], a[i].length);
        }
        a[0][0] = 10;
        System.out.println(a[0][0] + " " + b[0][0]);
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