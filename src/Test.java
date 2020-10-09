import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("MM/DD HH:MM:SS");
        Date D1 = f.parse("10/01 23:20:25");

        System.out.println( D1.getTime() + 30);
    }
}
