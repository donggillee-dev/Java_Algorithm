import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> L = new LinkedList<>();
        LinkedList<Integer> Child = new LinkedList<>();
        L.add(1);
        L.add(2);
        L.add(2);
        L.add(2);
        L.add(4);
        L.add(4);
        L.add(5);
        int val = 0;
        int cnt = 1;
        int idx = 1;
        for(Iterator<Integer> it = L.iterator(); it.hasNext();) {
            int cur = it.next();
            it.remove();
            if(idx == 1) {
                val = cnt;
            } else {
                if(val != cur) {
                    Child.add(val);
                    Child.add(cnt);
                    val = cur;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            idx++;
        }
        Child.add(val);
        Child.add(cnt);
        for(Iterator<Integer> it = Child.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
        System.out.println(Child.size());
        System.out.println(L.size());
    }
}