import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        sb.append("12345");
        sb.delete(sb.length() - 1, sb.length());

        System.out.println(sb);
    }
}