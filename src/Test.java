import java.util.LinkedList;

public class Test {
    public static void main(String[] args){
        LinkedList<Integer> test = new LinkedList<>();
        test.add(0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        for(int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
            test.remove(i);
            i--;
        }
    }
}
