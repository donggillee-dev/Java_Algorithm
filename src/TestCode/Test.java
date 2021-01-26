import java.io.*;
import java.util.*;



import java.util.Arrays;

public class Test{
    public static void main(String[] args)  {
       Parent p = new Child();
       System.out.println(p);
       System.out.println(p.x);
    }
}
class Parent {
    int x = 100;



    
    @Override
    public String   toString() {
        return "Parent{" +
                "x=" + x +
                '}';
    }
}

class Child extends Parent {
    int x = 200;
    @Override
    public String toString() {
        System.out.println("Test");
        return "Child{" +
                "x=" + x +
                '}';
    }
}
