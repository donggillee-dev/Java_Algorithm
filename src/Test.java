public class Test {
    public static void main(String[] args) {
        B obj = new B();
        System.out.println(obj.x());
        System.out.println(obj.y());
    }
}
class A{
    public String x(){return "x";}
}
class B extends A{
    public String y(){return "y";}
}
